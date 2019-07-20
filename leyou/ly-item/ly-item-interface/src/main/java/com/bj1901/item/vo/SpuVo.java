package com.bj1901.item.vo;

import com.bj1901.item.domain.Sku;
import com.bj1901.item.domain.Spu;
import com.bj1901.item.domain.SpuDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @Author: adming
 * @Date: 2019/7/3 0003 13:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SpuVo extends Spu{

    /**
     * 分类名称
     */
    private String cname;

    /**
     * 品牌名称
     */
    private String bname;

    /**
     * 特有属性skus列表
     */
    private Set<Sku> skus;

    /**
     * 商品的详情
     */
    private SpuDetail spuDetail;
}
