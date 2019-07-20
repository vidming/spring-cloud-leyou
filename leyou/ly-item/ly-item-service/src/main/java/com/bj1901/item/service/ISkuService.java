package com.bj1901.item.service;

import com.bj1901.item.domain.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 服务类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
public interface ISkuService extends IService<Sku> {

    /**
     * 根据spuId查询Sku列表
     * @param id
     * @return
     */
    List<Sku> findSkuListBySpuId(Long id);

    /**
     * 根据spuId查询所有匹配的sku的Id
     * @return
     */
    List<Long> findSkuIdsBySpuId(Long spuId);
}
