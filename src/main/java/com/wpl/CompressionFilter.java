package com.wpl;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ziplet.filter.compression.CompressingFilter;

@Configuration
public class CompressionFilter {

	@Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CompressingFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("debug", "true");
        registration.addInitParameter("compressionThreshold", "0");
        registration.setName("CompressingFilter");
        return registration;
	}
}
