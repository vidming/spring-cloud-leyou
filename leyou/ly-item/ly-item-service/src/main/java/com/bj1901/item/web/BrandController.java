package com.bj1901.item.web;


import com.bj1901.item.domain.Brand;
import com.bj1901.item.service.IBrandService;
import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import com.bj1901.leyou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 前端控制器
 * </p>
 *
 * @author adming
 * @since 2019-06-28
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> findBrandByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1", required = false) Long page,
            @RequestParam(value = "rows", defaultValue = "8", required = false) Long rows,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "true", required = false) Boolean desc
    ) {
        PageResult<Brand> pageResult = brandService.queryBrandListByPage(key, page, rows, sortBy, desc);
        return ResponseEntity.ok(pageResult);
    }

    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand,
            @RequestParam(value = "cids", required = true) List<Long> cids) {

        // 判断参数
        if (brand == null || CollectionUtils.isEmpty(cids)) {
            return ResponseEntity.badRequest().build();
        }

        // 调用添加方法
        brandService.saveBrand(brand, cids);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateBrand(Brand brand,
            @RequestParam(value = "cids", required = true) List<Long> cids) {

        // 判断参数
        if (brand == null || CollectionUtils.isEmpty(cids)) {
            return ResponseEntity.badRequest().build();
        }

        // 调用添加方法
        brandService.updateBrand(brand, cids);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/bid/{bid}")
    public ResponseEntity<Void> deleteBrand(@PathVariable(value = "bid", required = true) Long bid) {

        brandService.deleteBrandByBid(bid);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> getBrandsByCid(
            @PathVariable(value = "cid", required = true) Long cid) {

        return ResponseEntity.ok(brandService.findBrandListByCid(cid));
    }

    @GetMapping("/bid/{bid}")
    public ResponseEntity<Brand> getBrandByBid(
            @PathVariable(value = "bid", required = true) Long bid) {

        Brand brand = brandService.getById(bid);

        if (brand == null) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        return ResponseEntity.ok(brand);
    }

}

