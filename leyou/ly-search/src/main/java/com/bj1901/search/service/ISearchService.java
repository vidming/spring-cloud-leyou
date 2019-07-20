package com.bj1901.search.service;

import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.vo.PageResult;
import com.bj1901.search.pojo.Goods;
import com.bj1901.search.pojo.SearchRequest;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 16:04
 */
public interface ISearchService {

    /**
     * 构建Goods数据
     * @param spuVo ： 封装的商品数据
     * @return
     */
    public abstract Goods buildGoods(SpuVo spuVo);


    /**
     * 根据封装的请求参数进行搜索
     * @param request
     * @return
     */
    PageResult<Goods> search(SearchRequest request);
}
