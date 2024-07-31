package com.jabran.canopee.config;

import com.jabran.canopee.converters.HeadManagerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private HeadManagerConverter headManagerConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(headManagerConverter);
    }
}
