package org.bookStore.config;
import org.bookStore.filter.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    /**
     * 将自定义拦截器作为bean写入配置
     * @return
     */
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截处理操作的匹配路径
        //放开静态拦截
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**") //intercept all paths
                .excludePathPatterns("/index", "/", "/login")//exclude login and main path
                .excludePathPatterns("/css/**", "/img/**", "/book/**", "/script/**", "/uploads/**");//exclude static resources

    }

}