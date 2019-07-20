package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bj1901.item.domain.Specification;
import com.bj1901.item.mapper.SpecificationMapper;
import com.bj1901.item.service.ISpecificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规格参数模板，json格式。 服务实现类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public String findOne(Long cid) {
        // 条件构造器
        QueryWrapper<Specification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Specification.CATEGORY_ID, cid);

        // 执行查询
        Specification spec = specificationMapper.selectOne(queryWrapper);

        if (spec == null) {
            throw new LyException(ExceptionEnum.SPEC_NOT_FOUND);
        }
        return spec.getSpecifications();
    }
}
