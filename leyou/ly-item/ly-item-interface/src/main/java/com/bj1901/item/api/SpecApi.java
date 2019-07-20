package com.bj1901.item.api;

import com.bj1901.item.domain.SpecParam;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 18:40
 */
@RequestMapping("/spec")
public interface SpecApi {

    @GetMapping("/params")
    public List<SpecParam> getSpecParamList(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searchFlag", required = false) Boolean searchFlag);

}
