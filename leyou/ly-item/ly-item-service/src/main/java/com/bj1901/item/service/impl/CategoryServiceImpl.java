package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bj1901.item.domain.Category;
import com.bj1901.item.mapper.ICategoryMapper;
import com.bj1901.item.service.ICategoryService;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: adming
 * @Date: 2019/6/23 0023 15:55
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<ICategoryMapper, Category> implements ICategoryService {

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoryListByPid(Long pid) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();

        // 调用mabatis-plus的方法
        return categoryMapper.selectList(queryWrapper.eq("parent_id", pid));
    }

    @Override
    public List<Category> findCategoryListByBid(Long bid) {
        // 查询分类ID
        List<Long> cids = categoryMapper.queryCidsByBid(bid);

        // 查询所有品牌对应下的所有分类
        return categoryMapper.selectBatchIds(cids);
    }

    @Override
    public List<String> findCnamesByCids(List<Long> cids) {
        // 查询分类
        List<Category> categories = categoryMapper.selectBatchIds(cids);

        if (CollectionUtils.isEmpty(categories)) {
            throw new LyException(ExceptionEnum.RESULT_NOT_FOUND);
        }

        // 提取分类名
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());

        return names;
    }
}
