package com.example.board.config;

import com.example.board.config.filter.AuthenticationFilter;
import com.example.board.config.filter.FilterPractice;
import com.example.board.config.filter.LoggingFilter;
import com.example.board.config.filter.ViewCountFilter;
import com.example.board.service.BoardService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final BoardService boardService;

    public WebConfig(BoardService boardService) {
        this.boardService = boardService;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> setAuthenticationFilterBean(){
        FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.setOrder(0);

        filterRegistrationBean.addUrlPatterns("/api/board/*");
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean<LoggingFilter> setLoggingFilterBean(){
        FilterRegistrationBean<LoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoggingFilter());
        filterRegistrationBean.setOrder(1);

        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<FilterPractice> setFilterBean(){
        FilterRegistrationBean<FilterPractice> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new FilterPractice());
        filterRegistrationBean.setOrder(2);

        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<ViewCountFilter> setViewCountFilter(){
        FilterRegistrationBean<ViewCountFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ViewCountFilter(boardService));
        filterRegistrationBean.setOrder(3);

        filterRegistrationBean.addUrlPatterns("/api/board/*");
        return filterRegistrationBean;
    }

}
