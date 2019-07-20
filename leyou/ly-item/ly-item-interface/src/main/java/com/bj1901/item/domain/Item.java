package com.bj1901.item.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 15:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Integer id;
    private String name;
    private Double price;

}
