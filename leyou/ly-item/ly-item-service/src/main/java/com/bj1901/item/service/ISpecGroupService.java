package com.bj1901.item.service;

import com.bj1901.item.domain.SpecGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
public interface ISpecGroupService extends IService<SpecGroup> {

    /**
     * 根据分类Id查询商品规格参数
     * @param cid ： 商品分类Id
     * @return
     */
    List<SpecGroup> findOne(Long cid);

    /**
     * 添加规则组
     * @param specGroup
     */
    void addSpecGroup(SpecGroup specGroup);

    /**
     * 更新规格组
     * @param specGroup
     */
    void updateSpecGroup(SpecGroup specGroup);

    /**
     * 删除规格组
     * @param gid
     */
    void deleteSpecGroup(Long gid);
}
