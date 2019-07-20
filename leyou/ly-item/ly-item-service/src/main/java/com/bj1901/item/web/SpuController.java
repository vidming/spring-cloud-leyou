package com.bj1901.item.web;


import com.bj1901.item.domain.Spu;
import com.bj1901.item.domain.SpuDetail;
import com.bj1901.item.service.ISpuDetailService;
import com.bj1901.item.service.ISpuService;
import com.bj1901.item.vo.SpuVo;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import com.bj1901.leyou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 前端控制器
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Controller
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private ISpuService spuService;

    @Autowired
    private ISpuDetailService spuDetailService;

    @GetMapping("/page")
    public ResponseEntity<PageResult<SpuVo>> getSpuByPage(
        @RequestParam(value = "key", required = false) String key,
        @RequestParam(value = "page", defaultValue = "1", required = false) Long page,
        @RequestParam(value = "rows", defaultValue = "8", required = false) Long rows,
        @RequestParam(value = "saleable", required = false) Boolean saleable) {

        PageResult<SpuVo> pageResult = spuService.findSpuByPage(key,page,rows,saleable);

        // 判断结果
        if (pageResult == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(pageResult);
    }

    /**
     * 根据spuId查详情
     * @param spuId
     * @return
     */
    @GetMapping("/detail/{spuId}")
    public ResponseEntity<SpuDetail> getSpuDetailBySpuId(
            @PathVariable(value = "spuId", required = true) Long spuId) {

        SpuDetail spuDetail = spuDetailService.findSpuDetailBySpuId(spuId);

        return ResponseEntity.ok(spuDetail);
    }

}

