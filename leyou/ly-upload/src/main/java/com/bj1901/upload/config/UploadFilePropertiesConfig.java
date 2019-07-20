package com.bj1901.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author: adming
 * @Date: 2019/6/25 0025 19:15
 */
@Data
@ConfigurationProperties(prefix = "ly.upload")
public class UploadFilePropertiesConfig {

    private String baseUrl;
    private List<String> allowTypes;

}
