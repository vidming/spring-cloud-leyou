package com.bj1901.search.controller;

import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.vo.PageResult;
import com.bj1901.search.client.GoodsClient;
import com.bj1901.search.pojo.Goods;
import com.bj1901.search.pojo.SearchRequest;
import com.bj1901.search.repository.GoodsResposity;
import com.bj1901.search.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 22:07
 */
@RestController
public class SearchController {

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private ISearchService searchService;

    @Autowired
    private GoodsResposity goodsResposity;

    @GetMapping("/addGoodsToIndex")
    public ResponseEntity<Void> addGoodsToIndex() {

        Long page = 1L;
        Long rows = 10L;
        int size = 0;
        do {
            // 查询spu
            PageResult<SpuVo> spuPage = goodsClient.getSpuByPage("华为mate40", page, rows, true);
            List<SpuVo> spuVoList = spuPage.getItems();
            System.out.println("大小---》" + spuVoList.size());
            System.out.println("数据---》" + spuVoList);

            // 构建成goods
            List<Goods> goodsList = spuVoList.stream().map(searchService::buildGoods)
                    .collect(Collectors.toList());
            /*List<Goods> goodsList = spuVoList.stream().map(spuVo -> {
                Goods goods = searchService.buildGoods(spuVo);
                if (goods = null)
            }).collect(Collectors.toList());*/
            goodsList.removeIf(Objects::isNull);
            System.out.println("有没有数据----> " + goodsList);

            if (CollectionUtils.isEmpty(goodsList)) {
                page++;
                size = spuVoList.size();
                continue;
            }

            // 引入索引库
            goodsResposity.saveAll(goodsList);

            page++;
            size = spuVoList.size();

        }while (size == 10);

        return ResponseEntity.ok().build();
    }

    /**
     * 搜索商品
     *
     * @param request
     * @return
     */
    @PostMapping("page")
    public ResponseEntity<PageResult<Goods>> search(@RequestBody SearchRequest request) {
        PageResult<Goods> result = this.searchService.search(request);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

}
