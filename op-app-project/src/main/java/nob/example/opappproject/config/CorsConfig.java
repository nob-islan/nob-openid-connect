package nob.example.opappproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nob.example.opappproject.constants.UrlConst;

/**
 * OP Webからの疎通を許可するためのコンフィグクラスです。
 * 
 * @author nob
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * オリジン間リソース共有を許可します。
     * 
     * @return
     */
    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(UrlConst.OP_WEB_ORIGIN)
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
