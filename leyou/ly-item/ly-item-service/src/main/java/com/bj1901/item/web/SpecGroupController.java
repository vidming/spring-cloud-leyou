package com.bj1901.item.web;


import com.bj1901.item.domain.SpecGroup;
import com.bj1901.item.service.ISpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class SpecGroupController {

    @Autowired
    private ISpecGroupService specGroupService;

    @GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> getSpecificationGroup(
            @PathVariable(value = "cid", required = true) Long cid) {
        List<SpecGroup> specGroupList = specGroupService.findOne(cid);

        return ResponseEntity.ok(specGroupList);
    }


    @PostMapping("/group")
    public ResponseEntity<Void> addSpecGroup(
            @RequestBody SpecGroup specGroup) {

        specGroupService.addSpecGroup(specGroup);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/group")
    public ResponseEntity<Void> updateSpecGroup(
            @RequestBody SpecGroup specGroup) {

        specGroupService.updateSpecGroup(specGroup);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/group/{gid}")
    public ResponseEntity<Void> deleteSpecGroup(
            @PathVariable(value = "gid", required = true) Long gid) {

        specGroupService.deleteSpecGroup(gid);
        return ResponseEntity.ok().build();

    }

}

