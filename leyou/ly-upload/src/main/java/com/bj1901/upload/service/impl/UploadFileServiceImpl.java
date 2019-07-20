package com.bj1901.upload.service.impl;

import com.bj1901.leyou.custom_exception.LyException;
import com.bj1901.leyou.enums.ExceptionEnum;
import com.bj1901.upload.config.UploadFilePropertiesConfig;
import com.bj1901.upload.service.IUploadFileService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: adming
 * @Date: 2019/6/24 0024 22:05
 */
@Service
@Slf4j
@EnableConfigurationProperties(UploadFilePropertiesConfig.class)
public class UploadFileServiceImpl implements IUploadFileService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadFilePropertiesConfig prop;

    @Override
    public String upload(MultipartFile file) {
        // 文件名称的校验
        // 1. 获取文件的类型
        String contentType = file.getContentType();
        if (!prop.getAllowTypes().contains(contentType)) {
            throw new LyException(ExceptionEnum.NSUPPORTED_MEDIA_TYPE);
        }
        // 文件内容的校验
        try {
            BufferedImage content = ImageIO.read(file.getInputStream());

            // 获取文件的名的后缀
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");

            // 上传至FastDFS分布式文件系统
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);

            // 返回完整的路径
            return prop.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new LyException(ExceptionEnum.NSUPPORTED_MEDIA_TYPE);
        }
    }
}
