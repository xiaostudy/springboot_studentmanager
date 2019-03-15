package com.xiaostudy.springboot_studentmanager.config;

import com.xiaostudy.springboot_studentmanager.component.LoginHandlerInterceptor;
import com.xiaostudy.springboot_studentmanager.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

// 注册拦截器
// 使用WebMvcConfigurationSupport可以来扩展SpringMVC的功能
//@EnableWebMvc   不要全面接管SpringMVC
@Configuration
public class SpringMvcConfig extends WebMvcConfigurationSupport {

    // 请求映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("userlogin");
    }

    // 过滤请求
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/webjars/**", "/css/**", "/js/**", "/index.html", "/", "/userlogin", "/login", "/md5");*/
    }

    // 静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        //super.addResourceHandlers(registry);
    }

    // 自己的区域解析器
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}