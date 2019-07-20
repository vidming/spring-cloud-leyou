package com.bj1901.item.mapper;

import com.bj1901.item.domain.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 Mapper 接口
 * </p>
 *
 * @author adming
 * @since 2019-06-28
 */
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 添加品牌和分类
     * @param cid ： 分类Id
     * @param bid : 品牌
     * @return
     */
    @Select("insert into tb_category_brand values(#{cid},#{bid})")
    void saveBrandAndCids(@Param("cid") Long cid, @Param("bid") Long bid);

    /**
     * 删除品牌对应的商品分类
     * @param bid
     * @return
     */
    @Select("DELETE FROM tb_category_brand WHERE brand_id = #{bid};")
    Integer deleteCategoryByBid(@Param("bid") Long bid);

    /**
     * 根据分类Id查询品牌id
     * @param cid
     * @return
     */
    @Select("select brand_id from tb_category_brand where category_id = #{cid}")
    List<Long> selectBidsByCid(Long cid);

}
