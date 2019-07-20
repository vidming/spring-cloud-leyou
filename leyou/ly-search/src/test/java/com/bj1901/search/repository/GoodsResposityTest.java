package com.bj1901.search.repository;

import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.vo.PageResult;
import com.bj1901.search.client.GoodsClient;
import com.bj1901.search.pojo.Goods;
import com.bj1901.search.service.ISearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 15:02
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class GoodsResposityTest {

    @Autowired
    private GoodsResposity goodsResposity;

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private ISearchService searchService;

    @Test
    public void testCreateIndex() {
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
    }

    @Test
    public void loadData() {
        Long page = 1L;
        Long rows = 100L;
        int size = 0;
        do {
            // 查询spu
            PageResult<SpuVo> spuPage = goodsClient.getSpuByPage(null, page, rows, true);
            List<SpuVo> spuVoList = spuPage.getItems();

            // 构建成goods
            List<Goods> goodsList = spuVoList.stream().map(searchService::buildGoods)
                    .collect(Collectors.toList());

            // 引入索引库
            goodsResposity.saveAll(goodsList);

            page++;
            size = spuVoList.size();

        }while (size == 100);
    }

}