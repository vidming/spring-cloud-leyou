package com.bj1901.upload.service;

import org.springframework.web.multipart.MultipartFile; /**
 * @Author: adming
 * @Date: 2019/6/24 0024 22:03
 */
public interface IUploadFileService {

    /**
     * 上传文件的业务处理
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
