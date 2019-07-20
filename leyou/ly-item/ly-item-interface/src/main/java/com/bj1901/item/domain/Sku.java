package com.bj1901.item.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("tb_sku")
public class Sku {

private static final long serialVersionUID=1L;

    /**
     * sku id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * spu id
     */
    private Long spuId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品的图片，多个图片以‘,’分割
     */
    private String images;

    /**
     * 销售价格，单位为分
     */
    private Long price;

    /**
     * 特有规格属性在spu属性模板中的对应下标组合
     */
    private String indexes;

    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     */
    private String ownSpec;

    /**
     * 是否有效，0无效，1有效
     */
    private Boolean enable;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date lastUpdateTime;

    /**
     * 商品库存
     */
    @TableField(exist = false)
    private Integer stock;


    public static final String ID = "id";

    public static final String SPU_ID = "spu_id";

    public static final String TITLE = "title";

    public static final String IMAGES = "images";

    public static final String PRICE = "price";

    public static final String INDEXES = "indexes";

    public static final String OWN_SPEC = "own_spec";

    public static final String ENABLE = "enable";

    public static final String CREATE_TIME = "create_time";

    public static final String LAST_UPDATE_TIME = "last_update_time";

}
