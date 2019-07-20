package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bj1901.item.domain.SpuDetail;
import com.bj1901.item.mapper.SpuDetailMapper;
import com.bj1901.item.service.ISpuDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author adming
 * @since 2019-07-04
 */
@Service
public class SpuDetailServiceImpl extends ServiceImpl<SpuDetailMapper, SpuDetail> implements ISpuDetailService {

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Override
    public SpuDetail findSpuDetailBySpuId(Long spuId) {

        SpuDetail spuDetail = spuDetailMapper.selectOne(
                new QueryWrapper<SpuDetail>().eq(SpuDetail.SPU_ID, spuId));
        if (spuDetail == null) {
            return null;
            //throw new LyException(ExceptionEnum.SPU_DETAIL_NOT_FOUND);
        }
        return spuDetail;
    }
}
