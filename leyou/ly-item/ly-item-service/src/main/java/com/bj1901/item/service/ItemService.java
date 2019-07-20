package com.bj1901.item.service;

import com.bj1901.item.domain.Item;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 15:28
 */
@Service("itemService")
public class ItemService {

    public Item saveItem(Item item) {
        // 随机生成一个数
        int itemId = new Random().nextInt(100);
        item.setId(itemId);
        return item;
    }

}
