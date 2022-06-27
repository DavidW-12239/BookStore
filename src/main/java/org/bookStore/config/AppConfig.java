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
     * @return
     */
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**") //intercept all paths
                .excludePathPatterns("/index", "/", "/login")//exclude login and main path
                .excludePathPatterns("/css/**", "/img/**", "/book/**", "/script/**", "/uploads/**");//exclude static resources

    }

}