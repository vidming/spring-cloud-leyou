package com.bj1901.item.mapper;

import com.bj1901.item.domain.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 Mapper 接口
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 根据spuid查询所有的skuids
     * @param spuId
     * @return
     */
    @Select("select id from tb_sku where spu_id = #{spuId}")
    List<Long> querySkuIdsBySpuId(Long spuId);

    /**
     * 查询sku和库存
     * @param spuId
     * @return
     */
    @Select("select s.*,st.stock from tb_sku s inner join tb_stock st on s.id = st.sku_id where s.spu_id = #{spuId}")
    List<Sku> querySkuListBySpuId(Long spuId);
}
