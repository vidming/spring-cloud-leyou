package com.bj1901.item.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Data
@Accessors(chain = true)
@TableName("tb_spec_param")
public class SpecParam {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品分类id
     */
    private Long cid;

    /**
     * 规格组id
     */
    private Long groupId;

    /**
     * 参数名
     */
    private String name;

    /**
     * 是否是数字类型参数，true或者false
     */
    @TableField("`numeric`")
    private Boolean numeric;

    /**
     * 数字类型参数的单位，非数字类型的可以为空
     */
    private String unit;

    /**
     * 是否是spu通用属性，true或者false
     */
    private Boolean generic;

    /**
     * 是否用户搜索过滤，true或者false
     */
    private Boolean searching;

    /**
     * 数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0
     */
    private String segments;


    public static final String ID = "id";

    public static final String CID = "cid";

    public static final String GROUP_ID = "group_id";

    public static final String NAME = "name";

    public static final String NUMERIC = "numeric";

    public static final String UNIT = "unit";

    public static final String GENERIC = "generic";

    public static final String SEARCHING = "searching";

    public static final String SEGMENTS = "segments";

}
