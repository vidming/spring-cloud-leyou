package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj1901.item.domain.Brand;
import com.bj1901.item.mapper.BrandMapper;
import com.bj1901.item.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import com.bj1901.leyou.vo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务实现类
 * </p>
 *
 * @author adming
 * @since 2019-06-28
 */
@Service
@Transactional
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResult<Brand> queryBrandListByPage(String key, Long page, Long rows, String sortBy, Boolean desc) {
        // 定义条件构造器
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();

        // 排序(desc如果为true： 表示降序，否则升序，默认降序)
        if (desc) {
            queryWrapper.orderByDesc(sortBy);
        } else {
            queryWrapper.orderByAsc(sortBy);
        }

        // 过滤
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.like(Brand.NAME, key).or().like(Brand.LETTER, key);
        }

        // 分页查询
        Page<Brand> brandPage = new Page<>(page, rows);
        IPage<Brand> brandIPage = brandMapper.selectPage(brandPage, queryWrapper);

        // 判断结果
        if (brandIPage == null) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        PageResult<Brand> pageResult = new PageResult<>();
        // 设置总记录数
        pageResult.setTotal(brandIPage.getTotal());
        // 要显示的数据
        pageResult.setItems(brandIPage.getRecords());

        return pageResult;
    }

    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        // 添加品牌
        brand.setId(null);
        int count = brandMapper.insert(brand);

        if (count < 1)
            throw new LyException(ExceptionEnum.SAVE_BRAND_ERROR);

        // 添加品牌所属分类
        for (Long cid : cids) {
            brandMapper.saveBrandAndCids(cid, brand.getId());
        }
    }

    @Override
    public void updateBrand(Brand brand, List<Long> cids) {
        // 更新
        int count = brandMapper.updateById(brand);

        if (count < 1)
            throw new LyException(ExceptionEnum.UPDATE_BRAND_ERROR);

        // 删除品牌的分类
        Integer c = brandMapper.deleteCategoryByBid(brand.getId());

        // 添加品牌所属分类
        for (Long cid : cids) {
            brandMapper.saveBrandAndCids(cid, brand.getId());
        }
    }

    @Override
    public void deleteBrandByBid(Long bid) {
        // 删除品牌
        int count = brandMapper.deleteById(bid);

        if (count < 1) {
            throw new LyException(ExceptionEnum.DELETE_BRAND_ERROR);
        }

        // 删除品牌相应的分类
        Integer integer = brandMapper.deleteCategoryByBid(bid);
        System.out.println(integer);
    }

    @Override
    public List<Brand> findBrandListByCid(Long cid) {

        // 查询品牌Id
        List<Long> bids = brandMapper.selectBidsByCid(cid);
        if (CollectionUtils.isEmpty(bids)) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        List<Brand> brandList = brandMapper.selectBatchIds(bids);

        // 判断结果
        if (CollectionUtils.isEmpty(brandList)) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return brandList;
    }
}
