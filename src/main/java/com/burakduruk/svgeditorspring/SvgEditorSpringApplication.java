package com.burakduruk.svgeditorspring;

import com.burakduruk.svgeditorspring.service.StorageProperties;
import com.burakduruk.svgeditorspring.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SvgEditorSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvgEditorSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }

}
