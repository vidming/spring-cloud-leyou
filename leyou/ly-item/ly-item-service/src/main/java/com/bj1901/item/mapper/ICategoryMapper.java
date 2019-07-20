package com.bj1901.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj1901.item.domain.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: adming
 * @Date: 2019/6/23 0023 15:56
 */
public interface ICategoryMapper extends BaseMapper<Category> {

    /**
     * 根据品牌ID查询分类ID
     * @param bid
     * @return
     */
    @Select("select category_id from tb_category_brand where brand_id=#{bid}")
    List<Long> queryCidsByBid(@Param("bid") Long bid);
}
