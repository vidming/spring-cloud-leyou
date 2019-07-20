package com.bj1901.search.service.impl;

import com.bj1901.item.domain.*;
import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.utils.JsonUtils;
import com.bj1901.leyou.vo.PageResult;
import com.bj1901.search.client.GoodsClient;
import com.bj1901.search.client.SpecClient;
import com.bj1901.search.pojo.Goods;
import com.bj1901.search.pojo.SearchRequest;
import com.bj1901.search.pojo.SkuBo;
import com.bj1901.search.repository.GoodsResposity;
import com.bj1901.search.service.ISearchService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 16:03
 */
@Service
@Slf4j
public class SearchServiceImpl implements ISearchService {
    
    @Autowired
    private SpecClient specClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private GoodsResposity goodsResposity;

    @Override
    public Goods buildGoods(SpuVo spuVo) {
        // 获取spuid
        Long spuId = spuVo.getId();

        String allCname = StringUtils.replace(spuVo.getCname(), "/", " ");

        // 拼接搜索字段
        String all = spuVo.getTitle() + allCname + spuVo.getBname();

        // 查询sku
        List<Sku> skuList = goodsClient.getSkuListBySpuId(spuId);

        // 获取价格
        Set<Long> priceSet = skuList.stream().map(Sku::getPrice).collect(Collectors.toSet());

        // 处理sku:提取想要字段
        List<SkuBo> skuBoList = skuList.stream().map(sku -> {
            SkuBo skuBo = new SkuBo();
            BeanUtils.copyProperties(sku, skuBo);
            return skuBo;
        }).collect(Collectors.toList());

        // 根据三级分类的Id查询规格参数
        List<SpecParam> specParamList = specClient.getSpecParamList(null,spuVo.getCid3(),true);

        // 获取商品详情
        SpuDetail spuDetail = goodsClient.getSpuDetailBySpuId(spuId);
        if (spuDetail == null) return null;
        // 获取商品通用规格
        Map<Long, String> genericSpec = JsonUtils.parseMap(spuDetail.getGenericSpec(), Long.class, String.class);
        // 获取商品特有规格
        Map<Long, List<String>> specialSpec = JsonUtils.nativeRead(spuDetail.getSpecialSpec(),
                new TypeReference<Map<Long, List<String>>>() {});

        // 定义存储规格参数的值
        Map<String, Object> specs = new HashMap<>();
        for (SpecParam specParam : specParamList) {
            // 获取参数名称当做key
            String key = specParam.getName();
            // 判断是否是通用属性
            Object value = "";
            if (specParam.getGeneric()) {
                value = genericSpec.get(specParam.getId());
                // 判断是否是数值
                if (specParam.getNumeric()) {
                    value = chooseSegment(value.toString(), specParam);
                }
            } else {
                value = specialSpec.get(specParam.getId());
            }
            // 存入map
            specs.put(key, value);
        }

        // 封装成Goods并返回
        Goods goods = new Goods();
        goods.setId(spuId).setAll(all).setBrandId(spuVo.getBrandId())
            .setCid1(spuVo.getCid1()).setCid2(spuVo.getCid2()).setCid3(spuVo.getCid3())
            .setCreateTime(spuVo.getCreateTime()).setPrice(priceSet)
            .setSubTitle(spuVo.getSubTitle()).setSkus(JsonUtils.serialize(skuBoList))
            .setSpecs(specs);

        return goods;
    }

    @Override
    public PageResult<Goods> search(SearchRequest request) {
        String key = request.getKey();
        // 判断是否有搜索条件，如果没有，直接返回null。不允许搜索全部商品
        if (StringUtils.isBlank(key)) {
            return null;
        }

        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 1、对key进行全文检索查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("all", key).operator(Operator.AND));

        // 2、通过sourceFilter设置返回的结果字段,我们只需要id、skus、subTitle
        queryBuilder.withSourceFilter(new FetchSourceFilter(
                new String[]{"id","skus","subTitle"}, null));

        // 3、分页
        // 准备分页参数
        int page = request.getPage();
        int size = request.getSize();
        queryBuilder.withPageable(PageRequest.of(page - 1, size));

        // 4、查询，获取结果
        Page<Goods> pageInfo = this.goodsResposity.search(queryBuilder.build());

        // 封装查询的结果
        PageResult<Goods> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotalElements());
        pageResult.setTotalPage(Long.valueOf(pageInfo.getTotalPages()));
        pageResult.setItems(pageInfo.getContent());

        return pageResult;
    }

    /**
     * 将数值类型的字段进行切成段
     * @param value
     * @param specParam
     * @return
     */
    private String chooseSegment(String value, SpecParam specParam) {
        double val = Double.valueOf(value);
        String result = "其他";
        // 保存数值段
        for (String segment : specParam.getSegments().split(",")) {
            String[] segs = segment.split("-");
            // 获取数值范围
            double begin = Double.valueOf(segs[0]);
            double end = Double.MAX_VALUE;
            if (segs.length == 2) {
                end = Double.valueOf(segs[1]);
            }
            // 判断范围
            if (val >= begin && val < end) {
                if (segs.length == 1) {
                    result = segs[0] + specParam.getUnit() + "以上";
                }else if (begin == 0) {
                    result = segs[1] + specParam.getUnit() + "以下";
                }else {
                    result = segment + specParam.getUnit();
                }
                break;
            }
        }

        return result;
    }

}
