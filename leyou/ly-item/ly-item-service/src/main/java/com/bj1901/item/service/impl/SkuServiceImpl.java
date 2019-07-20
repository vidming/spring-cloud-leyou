package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bj1901.item.domain.Sku;
import com.bj1901.item.mapper.SkuMapper;
import com.bj1901.item.service.ISkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 服务实现类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public List<Sku> findSkuListBySpuId(Long id) {
        // 执行查询方法
        List<Sku> skuList = skuMapper.querySkuListBySpuId(id);
        // 检验结果
        if (CollectionUtils.isEmpty(skuList)) {
            throw new LyException(ExceptionEnum.SKU_NOT_FOUND);
        }

        return skuList;
    }

    @Override
    public List<Long> findSkuIdsBySpuId(Long spuId) {
        // 查询所有的skuId
        List<Long> skuIds = skuMapper.querySkuIdsBySpuId(spuId);
        if (CollectionUtils.isEmpty(skuIds)) {
            throw new LyException(ExceptionEnum.SKU_NOT_FOUND);
        }
        return skuIds;
    }
}
