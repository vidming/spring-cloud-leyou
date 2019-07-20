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
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("tb_spu")
public class Spu {

private static final long serialVersionUID=1L;

    /**
     * spu id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 1级类目id
     */
    private Long cid1;

    /**
     * 2级类目id
     */
    private Long cid2;

    /**
     * 3级类目id
     */
    private Long cid3;

    /**
     * 商品所属品牌id
     */
    private Long brandId;

    /**
     * 是否上架，0下架，1上架
     */
    private Boolean saleable;

    /**
     * 是否有效，0已删除，1有效
     */
    private Boolean valid;

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


    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String SUB_TITLE = "sub_title";

    public static final String CID1 = "cid1";

    public static final String CID2 = "cid2";

    public static final String CID3 = "cid3";

    public static final String BRAND_ID = "brand_id";

    public static final String SALEABLE = "saleable";

    public static final String VALID = "valid";

    public static final String CREATE_TIME = "create_time";

    public static final String LAST_UPDATE_TIME = "last_update_time";

}
