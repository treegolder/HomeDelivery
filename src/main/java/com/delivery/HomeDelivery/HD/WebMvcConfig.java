package com.delivery.HomeDelivery.HD;



import com.delivery.HomeDelivery.HD.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {//注入拦截器注册对象
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login");

     /*   registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/api/teacher/**");
        registry.addInterceptor(studentInterceptor)
                .addPathPatterns("/api/student/**");*/
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/adminIndex.html").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/user/process.html").setViewName("process");
        registry.addViewController("/user/postorderlist.html").setViewName("postorderlist");
    }
}
