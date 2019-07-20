package com.bj1901.item.api;

import com.bj1901.item.domain.Brand;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 14:16
 */
@RequestMapping("/brand")
public interface BrandApi{

    @GetMapping("/bid/{bid}")
    public Brand getBrandByBid(
            @PathVariable(value = "bid", required = true) Long bid);

}
