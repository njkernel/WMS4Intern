package com.connext.wms.config;

import com.connext.wms.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.PrintWriter;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**", "/user/login").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("admin")
                .antMatchers("/inRepertory/**").hasAnyAuthority("InRepertory","admin")
                .antMatchers("/outRepoOrderController/**").hasAnyAuthority("OutRepertory","admin")
                .antMatchers("/exception/**").hasAnyAuthority("OutRepertory","admin")
                .antMatchers("/goods/**").hasAnyAuthority("manage","admin")
                .antMatchers("/goodsRepertory/**").hasAnyAuthority("manage","admin")
                .antMatchers("/expressCompany/**").permitAll()
                .anyRequest().authenticated()
                .and().rememberMe()
                .and()
                .formLogin()
                .loginPage("/user/login").successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"state\":\"Access\"}");
                    out.flush();
                    out.close();
                }).failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"state\":\"" + "账号或密码错误" + "\"}");
                    out.flush();
                    out.close();
                }).permitAll()
                .and()
                .logout()
                .logoutUrl("/user/outLogin")
                .logoutSuccessUrl("/user/login")
                .and()
                .rememberMe()
                .and()
                .headers().frameOptions().disable();
    }

}
