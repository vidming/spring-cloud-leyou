package com.bj1901.item.service;

import com.bj1901.item.domain.SpecParam;
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
public interface ISpecParamService extends IService<SpecParam> {

    /**
     * 根据组id查找商品规格参数
     *
     * @param gid: 分类组Id
     * @param cid：分类Id
     * @param searchFlag : 是否是搜索字段
     * @return
     */
    List<SpecParam> findSpecParamList(Long gid, Long cid, Boolean searchFlag);

    /**
     * 添加规格组
     * @param specParam
     */
    void addSpecParam(SpecParam specParam);

    /**
     * 更新规格参数
     * @param specParam
     */
    void updateSpecParam(SpecParam specParam);

    /**
     * 更加Id删除规格参数
     * @param id
     */
    void deleteSpecParam(Long id);
}
