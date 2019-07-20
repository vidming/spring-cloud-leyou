package com.bj1901.item.web;

import com.bj1901.item.domain.Item;
import com.bj1901.item.service.ItemService;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 15:23
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> saveItem(Item item) {
        // 价格校验
        if (item.getPrice() == null) {
            throw new LyException(ExceptionEnum.ITEM_PRICE_IS_NULL);
        }
        Item successItem = itemService.saveItem(item);
        return ResponseEntity.status(HttpStatus.OK).body(successItem);
    }

}
