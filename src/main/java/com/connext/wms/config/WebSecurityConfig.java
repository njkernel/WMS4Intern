package com.connext.wms.config;

import com.connext.wms.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0 */
// @Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private AccessDeniedHandler accessDeniedHandler;
  @Autowired
  private UserDetailServiceImpl userDetailService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    /*auth.inMemoryAuthentication()
        .withUser("admin")
        .password(new BCryptPasswordEncoder().encode("123456"))
        .roles("admin");*/
    auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
    http.authorizeRequests()
            .antMatchers("/toRegister").hasAnyRole("SUPER_ADMIN")
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .and()
            .logout()
            .logoutUrl("/outLogin").permitAll();
  }
}
