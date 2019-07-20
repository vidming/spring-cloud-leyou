package com.bj1901.item.service;

import com.bj1901.item.domain.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bj1901.leyou.vo.PageResult;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务类
 * </p>
 *
 * @author adming
 * @since 2019-06-28
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 分页查询的业务逻辑
     * @param key ： 搜索字段
     * @param page ： 当前页
     * @param rows ： 每页显示的条数
     * @param sortBy ： 排序字段
     * @param desc : 是否排序
     * @return
     */
    PageResult<Brand> queryBrandListByPage(String key, Long page, Long rows, String sortBy, Boolean desc);

    /**
     * 添加品牌
     * @param brand ： 添加的品牌
     * @param cids ： 品牌所属的分类
     * @return
     */
    void saveBrand(Brand brand, List<Long> cids);

    /**
     * 更新品牌
     * @param brand
     * @param cids
     */
    void updateBrand(Brand brand, List<Long> cids);

    /**
     * 删除品牌
     * @param bid
     */
    void deleteBrandByBid(Long bid);

    /**
     * 根据分类id查询品牌列表
     * @param cid
     * @return
     */
    List<Brand> findBrandListByCid(Long cid);
}
