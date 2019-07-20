package com.bj1901.item.service;

import com.bj1901.item.domain.Specification;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品规格参数模板，json格式。 服务类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
public interface ISpecificationService extends IService<Specification> {

    /**
     * 根据分类Id查询商品规格参数
     * @param cid ： 商品分类Id
     * @return
     */
    String findOne(Long cid);
}
