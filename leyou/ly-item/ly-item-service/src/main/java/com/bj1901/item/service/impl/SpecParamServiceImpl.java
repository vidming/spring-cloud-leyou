package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bj1901.item.domain.SpecParam;
import com.bj1901.item.mapper.SpecParamMapper;
import com.bj1901.item.service.ISpecParamService;
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
public class SpecParamServiceImpl extends ServiceImpl<SpecParamMapper, SpecParam> implements ISpecParamService {

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SpecParam> findSpecParamList(Long gid, Long cid, Boolean searchFlag) {

        // 定义条件构造器
        QueryWrapper<SpecParam> queryWrapper = new QueryWrapper<>();

        // 构造条件
        if (gid != null && gid.longValue() >= 0) {
            queryWrapper.eq(SpecParam.GROUP_ID, gid);
        }
        if (cid != null && cid.longValue() >= 0) {
            queryWrapper.eq(SpecParam.CID, cid);
        }
        if (searchFlag != null && searchFlag) {
            queryWrapper.eq(SpecParam.SEARCHING, searchFlag);
        }

        // 执行查询
        List<SpecParam> specParamList = specParamMapper.selectList(queryWrapper);

        // 判断结果
        if (CollectionUtils.isEmpty(specParamList)) {
            throw new LyException(ExceptionEnum.SPEC_NOT_FOUND);
        }
        return specParamList;
    }

    @Override
    public void addSpecParam(SpecParam specParam) {
        int count = specParamMapper.insert(specParam);

        if (count < 1)
            throw new LyException(ExceptionEnum.SAVE_SPEC_PARAM_ERROR);
    }

    @Override
    public void updateSpecParam(SpecParam specParam) {
        int count = specParamMapper.updateById(specParam);

        if (count < 1)
            throw new LyException(ExceptionEnum.UPDATE_SPEC_PARAM_ERROR);
    }

    @Override
    public void deleteSpecParam(Long id) {
        int count = specParamMapper.deleteById(id);

        if (count < 1)
            throw new LyException(ExceptionEnum.DELETE_SPEC_PARAM_ERROR);
    }
}
