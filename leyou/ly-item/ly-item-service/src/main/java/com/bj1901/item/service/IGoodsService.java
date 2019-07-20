package com.bj1901.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bj1901.item.vo.SpuVo; /**
 * @Author: adming
 * @Date: 2019/7/4 0004 8:55
 */
public interface IGoodsService {

    /**
     * 添加商品
     * @param spuVo
     */
    void addGoods(SpuVo spuVo);

    /**
     * 更新商品
     * @param spuVo
     */
    void updateGoods(SpuVo spuVo);

    /**
     * 删除商品
     * @param spuId
     */
    void deleteGoods(Long spuId);
}
