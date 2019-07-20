package com.bj1901.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 18:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuBo {

    private Long id;
    private String title;
    private Long price;
    private String images;

}
