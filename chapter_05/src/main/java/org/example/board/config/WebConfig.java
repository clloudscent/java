package org.example.board.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.board.config.filter.AuthenticationFilter;
import org.example.board.config.filter.LoginFilter;
import org.example.board.service.BoardService;
import org.example.board.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //WebMvcConfigurer
    private final BoardService boardService;
    private final UserService userService;
    private final ObjectMapper mapper;

    public WebConfig(BoardService boardService, UserService userService, ObjectMapper mapper) {
        this.boardService = boardService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> setAuthenticationFilterBean(){
        FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthenticationFilter(mapper));
        filterRegistrationBean.setOrder(0);

        filterRegistrationBean.addUrlPatterns("/api/board/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> setLoginFailCountFilter(){
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter(userService,mapper));

        filterRegistrationBean.addUrlPatterns("/api/user/authenticate");
        return filterRegistrationBean;
    }
}
