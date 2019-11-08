package com.assignment.fileserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MelongationCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){
        //配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://manager.leyou.com");
        corsConfiguration.setAllowCredentials(true);//允许携带cookie
        corsConfiguration.addAllowedMethod("*");//允许所有请求方法
        corsConfiguration.addAllowedHeader("*");//允许携带任何头信息


        //配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);

        //实例
        return new CorsFilter(configurationSource);
    }
}
