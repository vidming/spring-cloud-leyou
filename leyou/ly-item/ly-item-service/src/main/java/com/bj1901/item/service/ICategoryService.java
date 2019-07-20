package com.bj1901.item.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bj1901.item.domain.Category;

import java.util.List;

/**
 * @Author: adming
 * @Date: 2019/6/23 0023 15:55
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 根据父id查询商品分类
     * @return
     */
    List<Category> findCategoryListByPid(Long pid);

    /**
     * 根据品牌Id查询品牌的分类
     * @param bid
     * @return
     */
    List<Category> findCategoryListByBid(Long bid);

    /**
     * 更加分类Id查询分类名称
     * @param cids ： 分类Id
     * @return
     */
    List<String> findCnamesByCids(List<Long> cids);
}
