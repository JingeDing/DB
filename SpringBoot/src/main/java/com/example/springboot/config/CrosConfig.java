package com.example.springboot.config;

import com.example.springboot.filter.crosFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.DispatcherType;

@SuppressWarnings("unchecked")
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowCredentials(false).allowedHeaders("*").maxAge(3600);

    }

    @Bean
    public FilterRegistrationBean replaceTokenFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new crosFilter());
        registration.addUrlPatterns("/*");
        registration.setName("crosFilter ");
        registration.setOrder(1);
        return registration;
    }
}
