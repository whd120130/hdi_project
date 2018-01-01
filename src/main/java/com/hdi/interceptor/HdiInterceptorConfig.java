package com.hdi.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 * @author 王慧东
 * @date 2017/12/31.
 * @version 1.0
 */
@Configuration
public class HdiInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new ChanelInterceptor()).excludePathPatterns("**/login").addPathPatterns("/**");
    }
}
