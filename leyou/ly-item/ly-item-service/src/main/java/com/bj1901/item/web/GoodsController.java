package com.bj1901.item.web;

import com.bj1901.item.service.IGoodsService;
import com.bj1901.item.vo.SpuVo;
import jdk.management.resource.internal.ResourceNatives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: adming
 * @Date: 2019/7/3 0003 19:23
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @PostMapping
    public ResponseEntity<Void> addGoods(@RequestBody(required = true) SpuVo spuVo) {

        // 调用方法
        goodsService.addGoods(spuVo);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateGoods(@RequestBody(required = true) SpuVo spuVo) {

        // 调用方法
        goodsService.updateGoods(spuVo);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/spuid/{spuId}")
    public ResponseEntity<Void> deleteGoods(
            @PathVariable(value = "spuId", required = true) Long spuId) {

        goodsService.deleteGoods(spuId);
        return ResponseEntity.ok().build();
    }

}
