package com.bj1901.item.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品规格参数模板，json格式。
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("tb_specification")
public class Specification {

private static final long serialVersionUID=1L;

    /**
     * 规格模板所属商品分类id
     */
    private Long categoryId;

    /**
     * 规格参数模板，json格式
     */
    private String specifications;


    public static final String CATEGORY_ID = "category_id";

    public static final String SPECIFICATIONS = "specifications";

}
