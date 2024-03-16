package com.challenge.totvscrud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("Access-Control-Allow-Origin", "Accept", "Accept-Ranges", "Content-Type", "Cache-Control",
                        "Content-Length", "Origin", "Range", "X-HTTP-Method-Override", "X-Requested-With", "Authorization", "Credential",
                        "X-XSRF-TOKEN", "Page-Number", "Page-Size");
    }
}