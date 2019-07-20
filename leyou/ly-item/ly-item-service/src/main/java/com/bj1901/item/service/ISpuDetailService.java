package com.bj1901.item.service;

import com.bj1901.item.domain.SpuDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author adming
 * @since 2019-07-04
 */
public interface ISpuDetailService extends IService<SpuDetail> {

    /**
     * 根据spuId查询商品详情
     * @param spuId
     * @return
     */
    SpuDetail findSpuDetailBySpuId(Long spuId);

}
