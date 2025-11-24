package com.mateo.spotify.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.application.images.path}")
    private String imagePath;
    @Value("${spring.application.audio.path}")
    private String audioPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file: " + imagePath);
        registry.addResourceHandler("/audio/**")
                .addResourceLocations("file: " + audioPath);
    }
}
