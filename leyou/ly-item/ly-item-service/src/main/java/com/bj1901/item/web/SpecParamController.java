package com.bj1901.item.web;


import com.bj1901.item.domain.SpecParam;
import com.bj1901.item.service.ISpecParamService;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author adming
 * @since 2019-07-02
 */
@Controller
@RequestMapping("/spec")
public class SpecParamController {

    @Autowired
    private ISpecParamService specParamService;

    /**
     * 根据父或者分类Id
     * @param gid
     * @param cid
     * @return
     */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> getSpecParamList(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searchFlag", required = false) Boolean searchFlag) {

        List<SpecParam> specParamList = specParamService.findSpecParamList(gid, cid, searchFlag);

        if (CollectionUtils.isEmpty(specParamList)) {
            throw new LyException(ExceptionEnum.SPEC_NOT_FOUND);
        }

        return ResponseEntity.ok(specParamList);
    }

    @PostMapping("/param")
    public ResponseEntity<Void> addSpecParam(
            @RequestBody SpecParam specParam) {

        specParamService.addSpecParam(specParam);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/param")
    public ResponseEntity<Void> updateSpecParam(
            @RequestBody SpecParam specParam) {

        specParamService.updateSpecParam(specParam);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/param/{id}")
    public ResponseEntity<Void> deleteSpecParam(
            @PathVariable(value = "id", required = true) Long id) {

        specParamService.deleteSpecParam(id);
        return ResponseEntity.ok().build();

    }

}

