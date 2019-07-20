package com.bj1901.item.web;


import com.bj1901.item.domain.Specification;
import com.bj1901.item.service.ISpecificationService;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 商品规格参数模板，json格式。 前端控制器
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Controller
@RequestMapping("/spec/null") // TODO 暂时没有用
public class SpecificationController {

    @Autowired
    private ISpecificationService specificationService;

    @GetMapping("/groups/{cid}")
    public ResponseEntity<String> getSpecificationGroup(
            @PathVariable(value = "cid") Long cid) {
        String specifications = specificationService.findOne(cid);
        // 判断结果
        if (StringUtils.isEmpty(specifications)) {
            throw new LyException(ExceptionEnum.SPEC_NOT_FOUND);
        }
        return ResponseEntity.ok(specifications);
    }
}

