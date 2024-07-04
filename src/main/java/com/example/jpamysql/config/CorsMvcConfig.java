package com.example.jpamysql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        //모든 경로("/**")에 대해 CORS 설정을 적용합니다.
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000"); // "http://localhost:3000" 출처에서의 요청만 허용합니다
    }
}