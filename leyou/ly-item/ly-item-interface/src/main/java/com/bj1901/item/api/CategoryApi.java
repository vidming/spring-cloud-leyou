package com.bj1901.item.api;

import com.bj1901.item.domain.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: adming
 * @Date: 2019/7/8 0008 14:16
 */
@RequestMapping("/category")
public interface CategoryApi {

    @GetMapping("/bid/{bid}")
    public List<Category> findCategory(
            @PathVariable(value = "bid", required = true) Long bid);

    @GetMapping("/list/ids")
    List<String> findCnamesByCids(List<Long> cids);
}
