package com.bj1901.item.domain;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-07-04
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("tb_spu_detail")
public class SpuDetail {

private static final long serialVersionUID=1L;

    private Long spuId;

    /**
     * 商品描述信息
     */
    private String description;

    /**
     * 全部规格参数数据
     */
    private String genericSpec;

    /**
     * 特有规格参数名称及模板
     */
    private String specialSpec;

    /**
     * 包装清单
     */
    private String packingList;

    /**
     * 售后服务
     */
    private String afterService;


    public static final String SPU_ID = "spu_id";

    public static final String DESCRIPTION = "description";

    public static final String GENERIC_SPEC = "generic_spec";

    public static final String SPECIAL_SPEC = "special_spec";

    public static final String PACKING_LIST = "packing_list";

    public static final String AFTER_SERVICE = "after_service";


}
