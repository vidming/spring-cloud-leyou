package com.bj1901.item.web;


import com.bj1901.item.domain.Sku;
import com.bj1901.item.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 前端控制器
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Controller
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private ISkuService skuService;

    @GetMapping("/list")
    public ResponseEntity<List<Sku>> getSkuListBySpuId(
            @RequestParam(value = "id", required = true) Long id) {

        List<Sku> skuList =  skuService.findSkuListBySpuId(id);

        return ResponseEntity.ok(skuList);
    }


}

