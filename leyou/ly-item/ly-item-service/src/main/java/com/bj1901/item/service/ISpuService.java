package com.bj1901.item.service;

import com.bj1901.item.domain.Spu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bj1901.item.domain.SpuDetail;
import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.vo.PageResult;

import java.util.List;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 服务类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
public interface ISpuService extends IService<Spu> {

    /**
     * 查询商品列表
     * @param key ： 查询的字段
     * @param page ： 当前页码
     * @param rows ： 当前显示的条数
     * @param saleable ： 是否上下架
     * @return
     */
    PageResult<SpuVo> findSpuByPage(String key, Long page, Long rows, Boolean saleable);
}
