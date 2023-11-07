package org.zerock.apiserver.cofig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.apiserver.formatter.LocalDateFormatter;

@Configuration
@Slf4j
public class CustomWebServletConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        log.info("=========================");
        log.info("addFormatter called........");
        registry.addFormatter(new LocalDateFormatter());
    }

    // CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .maxAge(500)
                .allowedMethods("GET", "POST", "DELETE", "HEAD", "OPTIONS")
                .allowedOrigins("*");
    }
}
