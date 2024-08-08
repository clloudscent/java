package com.example.crud_test.config;

import com.example.crud_test.config.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// WebMvcConfigurer 인터페이스를 구현하여 필터를 적용한다.
@Configuration
public class WebConfig implements WebMvcConfigurer {

  // add authentication filter
  @Bean
  public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean(){
    FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new AuthenticationFilter());

    // 필터를 적용할 API Path를 설정
    registrationBean.addUrlPatterns("/api/member/list");
    return registrationBean;
  }

}
