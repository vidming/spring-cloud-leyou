package com.bj1901.leyou.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: adming
 * @Date: 2019/6/22 0022 15:46
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    ITEM_PRICE_IS_NULL(400, "价格不能为空"),
    PARAM_IS_NULL_OR_LT0(400, "参数不能为空或小于等于零"),
    RESULT_NOT_FOUND(404, "没有查询到商品分类"),
    FILE_UPLOAD_FAILURE(500, "文件上传失败"),
    NSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),
    BRAND_NOT_FOUND(404, "暂时无品牌"),
    SAVE_BRAND_ERROR(500, "品牌添加失败"),
    UPDATE_BRAND_ERROR(500, "品牌更新失败"),
    SAVE_BID_AND_CID_ERROR(500, "品牌和分类添加失败"),
    DELETE_BRAND_ERROR(500, "商品删除失败"),
    SPEC_NOT_FOUND(404, "暂无该类商品的规格参数"),
    SAVE_SPEC_GROUP_ERROR(500, "商品规格组添加失败"),
    UPDATE_SPEC_GROUP_ERROR(500, "商品规格组更新失败"),
    DELETE_SPEC_GROUP_ERROR(500, "商品规格组删除失败"),
    SAVE_SPEC_PARAM_ERROR(500, "商品规格参数添加失败"),
    UPDATE_SPEC_PARAM_ERROR(500, "商品规格参数更新失败"),
    DELETE_SPEC_PARAM_ERROR(500, "商品规格参数删除失败"),
    GOODS_NO_FOUND(400, "商品没有查到"),
    SAVE_SPU_ERROR(500, "商品SPU添加失败"),
    UPDATE_SPU_ERROR(500, "商品SPU更新失败"),
    DELETE_SPU_ERROR(500, "商品SPU删除失败"),
    SAVE_SPU_DETAIL_ERROR(500, "商品详情添加失败"),
    UPDATE_SPU_DETAIL_ERROR(500, "商品详情更新失败"),
    DELETE_SPU_DETAIL_ERROR(500, "商品详情更新失败"),
    SPU_DETAIL_NOT_FOUND(400, "没有该商品的详情"),
    SAVE_SKU_ERROR(500, "商品SKU添加失败"),
    UPDATE_SKU_ERROR(500, "商品SKU更新失败"),
    DELETE_SKU_ERROR(500, "商品SKU删除失败"),
    SKU_NOT_FOUND(500, "商品SKU没有查到"),
    SAVE_STOCK_ERROR(500, "商品库存添加失败"),
    DELETE_STOCK_ERROR(500, "商品库存删除失败"),
    ;
    private Integer code; // 异常状态码
    private String msg; // 异常信息

}
