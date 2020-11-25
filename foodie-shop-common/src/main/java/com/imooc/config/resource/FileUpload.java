package com.imooc.config.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author TryAgain404
 * @mail TryAgain500@163.com
 * @date 2020-11-18 11:07
 */
@Data
@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:file-upload-dev.properties")
public class FileUpload {
    private String imageUserFaceLocation;
    private String imageServerUrl;
}
