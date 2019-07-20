package com.bj1901.search.repository;

import com.bj1901.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 14:59
 */
public interface GoodsResposity extends ElasticsearchRepository<Goods, Long>{
}
