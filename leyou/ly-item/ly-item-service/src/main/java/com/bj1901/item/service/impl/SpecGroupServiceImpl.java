package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bj1901.item.domain.SpecGroup;
import com.bj1901.item.mapper.SpecGroupMapper;
import com.bj1901.item.service.ISpecGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Service
@Transactional
public class SpecGroupServiceImpl extends ServiceImpl<SpecGroupMapper, SpecGroup> implements ISpecGroupService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SpecGroup> findOne(Long cid) {
        // 条件构造器
        QueryWrapper<SpecGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SpecGroup.CID, cid);

        // 执行查询
        List<SpecGroup> specGroupList = specGroupMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(specGroupList)) {
            throw new LyException(ExceptionEnum.SPEC_NOT_FOUND);
        }
        return specGroupList;
    }

    @Override
    public void addSpecGroup(SpecGroup specGroup) {
        int count = specGroupMapper.insert(specGroup);

        if (count < 1)
            throw new LyException(ExceptionEnum.SAVE_SPEC_GROUP_ERROR);
    }

    @Override
    public void updateSpecGroup(SpecGroup specGroup) {
        int count = specGroupMapper.updateById(specGroup);

        if (count < 1)
            throw new LyException(ExceptionEnum.UPDATE_SPEC_GROUP_ERROR);
    }

    @Override
    public void deleteSpecGroup(Long gid) {
        int count = specGroupMapper.deleteById(gid);

        if (count < 1)
            throw new LyException(ExceptionEnum.DELETE_SPEC_GROUP_ERROR);
    }

}
