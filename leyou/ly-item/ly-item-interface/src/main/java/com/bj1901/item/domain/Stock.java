package com.bj1901.item.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 库存表，代表库存，秒杀库存等信息
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("tb_stock")
public class Stock {

private static final long serialVersionUID=1L;

    /**
     * 库存对应的商品sku id
     */
    private Long skuId;

    /**
     * 可秒杀库存
     */
    private Integer seckillStock;

    /**
     * 秒杀总数量
     */
    private Integer seckillTotal;

    /**
     * 库存数量
     */
    private Integer stock;


    public static final String SKU_ID = "sku_id";

    public static final String SECKILL_STOCK = "seckill_stock";

    public static final String SECKILL_TOTAL = "seckill_total";

    public static final String STOCK = "stock";

}
