package com.bj1901.item.web;

import com.bj1901.item.domain.Category;
import com.bj1901.item.service.ICategoryService;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: adming
 * @Date: 2019/6/23 0023 16:00
 * CrossOrigin为了解决跨域问题
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> findByParentId(
            @RequestParam(value = "pid", defaultValue = "0") Long pid) {

        // 判断传递的值
        if (pid == null && pid.longValue() < 0) {
            // 使用spring提供的响应400
            //return ResponseEntity.badRequest().build();

            // 使用自定义的异常
            throw new LyException(ExceptionEnum.PARAM_IS_NULL_OR_LT0);
        }

        // 执行获取商品分类的查询操作
        List<Category> categoryList = categoryService.findCategoryListByPid(pid);

        // 判断结果
        if (CollectionUtils.isEmpty(categoryList)) {
            // 使用spring提供的响应404
            //return ResponseEntity.notFound().build();

            // 使用自定义异常
            throw new LyException(ExceptionEnum.RESULT_NOT_FOUND);
        }

        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/bid/{bid}")
    public ResponseEntity<List<Category>> findCategory(
            @PathVariable(value = "bid", required = true) Long bid) {

        // 调用service方法
        List<Category> categories = categoryService.findCategoryListByBid(bid);

        // 检验categories
        if (CollectionUtils.isEmpty(categories)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/list/ids")
    public ResponseEntity<List<String>> findCnamesByCids(List<Long> cids) {

        if (CollectionUtils.isEmpty(cids)) {
            ResponseEntity.badRequest();
        }

        List<String> cnames = categoryService.findCnamesByCids(cids);

        return ResponseEntity.ok(cnames);
    }

}
