package com.burakduruk.svgeditorspring;

import com.burakduruk.svgeditorspring.service.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SvgEditorSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvgEditorSpringApplication.class, args);
    }

}
