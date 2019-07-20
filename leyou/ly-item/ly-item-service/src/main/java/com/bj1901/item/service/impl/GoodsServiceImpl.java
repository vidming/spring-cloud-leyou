package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bj1901.item.domain.Sku;
import com.bj1901.item.domain.Spu;
import com.bj1901.item.domain.SpuDetail;
import com.bj1901.item.domain.Stock;
import com.bj1901.item.service.*;
import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: adming
 * @Date: 2019/7/4 0004 8:55
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private ISpuService spuService;

    @Autowired
    private ISpuDetailService spuDetailService;

    @Autowired
    private ISkuService skuService;

    @Autowired
    private IStockService stockService;

    @Override
    @Transactional
    public void addGoods(SpuVo spuVo) {
        // 添加spu
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuVo, spu);
        spu.setCreateTime(new Date()).setLastUpdateTime(spu.getCreateTime());
        System.out.println(spu);
        /*spu.setId(null).setTitle(spuVo.getTitle()).setSubTitle(spuVo.getSubTitle())
                .setCid1(spuVo.getCid1()).setCid2(spuVo.getCid2()).setCid3(spuVo.getCid3())
                .setBrandId(spuVo.getBrandId()).setLastUpdateTime();*/
        boolean saveFlag = spuService.save(spu);
        if (!saveFlag) {
            throw new LyException(ExceptionEnum.SAVE_SPU_ERROR);
        }

        // 添加商品spu详情
        System.out.println("spu有没有回显：" + spu.getId());
        SpuDetail spuDetail = spuVo.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        saveFlag = spuDetailService.save(spuDetail);
        if (!saveFlag) {
            throw new LyException(ExceptionEnum.SAVE_SPU_DETAIL_ERROR);
        }

        // 添加sku和库存
        addSkuAndStock(spuVo, spu.getId());
    }

    @Override
    @Transactional
    public void updateGoods(SpuVo spuVo) {
        // 更新SPU
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuVo, spu);
        boolean updateFlag = spuService.updateById(spu);
        if (!updateFlag) {
            throw new LyException(ExceptionEnum.UPDATE_SPU_ERROR);
        }

        // 更新商品详情
        SpuDetail spuDetail = spuVo.getSpuDetail();
        updateFlag = spuDetailService.update(
                spuDetail,
                new UpdateWrapper<SpuDetail>().eq(SpuDetail.SPU_ID, spu.getId()));
        if (!updateFlag) {
            throw new LyException(ExceptionEnum.UPDATE_SPU_DETAIL_ERROR);
        }

        // 删除SKU和Stock
        deleteSkuAndStock(spu.getId());

        // 添加sku和库存
        addSkuAndStock(spuVo, spu.getId());

    }

    @Override
    @Transactional
    public void deleteGoods(Long spuId) {
        // 删除spu
        boolean removeFlag = spuService.removeById(spuId);
        if (!removeFlag) {
            throw new LyException(ExceptionEnum.DELETE_SPU_ERROR);
        }

        // 删除商品详情
        removeFlag = spuDetailService.remove(
                new QueryWrapper<SpuDetail>().eq(SpuDetail.SPU_ID, spuId));
        if (!removeFlag) {
            throw new LyException(ExceptionEnum.DELETE_STOCK_ERROR);
        }

        // 删除sku和库存
        deleteSkuAndStock(spuId);
    }

    /**
     * 根据spuId删除sku和库存
     * @param spuId
     */
    private void deleteSkuAndStock(Long spuId) {
        List<Long> skuIds = skuService.findSkuIdsBySpuId(spuId);
        boolean removeFlag = skuService.removeByIds(skuIds);
        if (!removeFlag) {
            throw new LyException(ExceptionEnum.DELETE_SKU_ERROR);
        }
        removeFlag = stockService.remove(
                new QueryWrapper<Stock>().in(Stock.SKU_ID, skuIds));

        if (!removeFlag) {
            throw new LyException(ExceptionEnum.DELETE_STOCK_ERROR);
        }
    }

    /**
     * sku和库存的更新
     * @param spuVo ：封装的SpuVo对象
     * @param spuId ： spuId
     */
    private void addSkuAndStock(SpuVo spuVo, Long spuId) {
        Set<Sku> skus = spuVo.getSkus();
        skus.stream().forEach(sku -> {
            // 添加sku
            sku.setSpuId(spuId).setCreateTime(new Date()).setLastUpdateTime(sku.getCreateTime());
            Boolean addFlag = skuService.save(sku);

            if (!addFlag) {
                throw new LyException(ExceptionEnum.SAVE_SKU_ERROR);
            }

            // 添加库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId()).setStock(sku.getStock()).setSeckillStock(null).setSeckillTotal(null);
            addFlag = stockService.save(stock);

            if (!addFlag) {
                throw new LyException(ExceptionEnum.SAVE_STOCK_ERROR);
            }
        });
    }

}
