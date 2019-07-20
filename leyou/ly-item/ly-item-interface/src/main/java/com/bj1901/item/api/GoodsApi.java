package com.bj1901.item.api;

import com.bj1901.item.domain.Sku;
import com.bj1901.item.domain.SpuDetail;
import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.vo.PageResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 14:16
 */
public interface GoodsApi {

    @GetMapping("/sku/list")
    public List<Sku> getSkuListBySpuId(
            @RequestParam(value = "id", required = true) Long id);

    @GetMapping("/spu/detail/{spuId}")
    public SpuDetail getSpuDetailBySpuId(
            @PathVariable(value = "spuId", required = true) Long spuId);

    @GetMapping("/spu/page")
    public PageResult<SpuVo> getSpuByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1", required = false) Long page,
            @RequestParam(value = "rows", defaultValue = "8", required = false) Long rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable);

}
