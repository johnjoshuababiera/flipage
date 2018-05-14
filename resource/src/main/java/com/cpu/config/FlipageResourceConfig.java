package com.cpu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FlipageResourceConfig extends WebMvcConfigurerAdapter {
    @Value("${flipage.file.url}")
    protected String FILE_LOCATION;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploaded-files/**").addResourceLocations("file:"+FILE_LOCATION).setCachePeriod(0);
        super.addResourceHandlers(registry);
    }
}
