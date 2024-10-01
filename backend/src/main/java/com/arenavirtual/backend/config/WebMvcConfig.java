package com.arenavirtual.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // link provisorio para o live server do html
    	registry
    		.addMapping("/**")
    		.allowedOrigins("http://localhost:5500")
    		.allowedMethods("PUT", "GET", "DELETE", "OPTIONS", "PATCH", "POST");
    }
}
