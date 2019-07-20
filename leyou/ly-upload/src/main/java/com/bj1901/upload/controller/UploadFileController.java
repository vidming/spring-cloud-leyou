package com.bj1901.upload.controller;

import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import com.bj1901.upload.service.IUploadFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: adming
 * @Date: 2019/6/24 0024 22:04
 */
@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @Autowired
    private IUploadFileService uploadFileService;

    @PostMapping("/image")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        // 调用文件上传的方法
        String url = uploadFileService.upload(file);

        // 判断返回值
        if (StringUtils.isEmpty(url)) {
            throw new LyException(ExceptionEnum.FILE_UPLOAD_FAILURE);
        }

        System.out.println(url);
    return ResponseEntity.ok(url);
    }

}
