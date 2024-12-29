package com.example.rp_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * REST通信に関する設定を管理するコンフィグクラスです。
 * 
 * @author nob
 */
@Configuration
public class RestConfig {

    /**
     * RestTemplateを宣言します。
     * 
     * @return
     */
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
