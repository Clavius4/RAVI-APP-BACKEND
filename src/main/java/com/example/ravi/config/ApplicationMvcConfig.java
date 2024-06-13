package com.example.ravi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;

@Configuration
public class ApplicationMvcConfig implements WebMvcConfigurer {


    @Autowired
    ServletContext servletContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/status-medias/**")
                .addResourceLocations("file:status-medias/");
    }
// @Override
// public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
// registry.addResourceHandler("/uploads/**")
// .addResourceLocations("file:uploads/")
// .addResourceLocations("file:uploads/profile/");
// }

}
