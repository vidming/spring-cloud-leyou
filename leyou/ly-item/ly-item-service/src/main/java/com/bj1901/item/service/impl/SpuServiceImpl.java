package com.bj1901.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj1901.item.domain.Brand;
import com.bj1901.item.domain.Spu;
import com.bj1901.item.domain.SpuDetail;
import com.bj1901.item.mapper.ICategoryMapper;
import com.bj1901.item.mapper.SpuMapper;
import com.bj1901.item.service.IBrandService;
import com.bj1901.item.service.ICategoryService;
import com.bj1901.item.service.ISpuDetailService;
import com.bj1901.item.service.ISpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import com.bj1901.leyou.vo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 服务实现类
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBrandService brandService;

    @Override
    public PageResult<SpuVo> findSpuByPage(String key, Long page, Long rows, Boolean saleable) {

        // 定义条件构造器
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        // 判断上下架
        if (saleable != null) {
            queryWrapper.eq(Spu.SALEABLE, saleable);
        }
        // 根据key字段模糊匹配
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.like(Spu.TITLE, key);
        }
        // 默认排序
        queryWrapper.orderByAsc(Spu.ID);
        // 分页查询
        IPage<Spu> spuIPage = spuMapper.selectPage(new Page<Spu>(page, rows), queryWrapper);
        List<Spu> spuList = spuIPage.getRecords();

        // 判断结果
        if (CollectionUtils.isEmpty(spuList)) {
            throw new LyException(ExceptionEnum.GOODS_NO_FOUND);
        }

        // 封装分页参数
        PageResult<SpuVo> pageResult = new PageResult<>();
        pageResult.setTotal(spuIPage.getTotal());
        pageResult.setTotalPage(spuIPage.getPages());
        pageResult.setCurrentPage(page);
        pageResult.setPageSize(rows);

        // 处理分页数据结果
        List<SpuVo> spuVos = spuList.stream().map(spu -> {
            SpuVo spuVo = new SpuVo();

            // 将查询到的数据复制到spuVo中
            BeanUtils.copyProperties(spu, spuVo);

            // 查询分类参数名称
            List<String> names = categoryService.findCnamesByCids(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            // 拼接参数名并设置到spuVo中
            spuVo.setCname(StringUtils.join(names, "/"));

            // 查询商品所属的品牌
            Brand brand = brandService.getById(spu.getBrandId());
            spuVo.setBname(brand.getName());

            return spuVo;
        }).collect(Collectors.toList());

        // 将封装好的结果封装到分页参数中
        pageResult.setItems(spuVos);

        return pageResult;
    }
}
