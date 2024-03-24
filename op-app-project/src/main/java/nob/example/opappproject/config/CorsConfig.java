package nob.example.opappproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nob.example.opappproject.constants.UrlConst;

/**
 * RP Webからの疎通を許可するためのコンフィグクラスです。
 * 
 * @author nob
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // APIのエンドポイント 一部APIのみ許可する場合は "/sample/**" などとする
                        .allowedOrigins(UrlConst.RP_WEB_ORIGIN) // Reactアプリのオリジン
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
