package com.in28minutes.springboot.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      // Authentication : User --> Roles
      auth.inMemoryAuthentication()
              .withUser("user")
              .password("user")
              .roles("USER")
              .and()
              .withUser("admin")
              .password("admin")
              .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorization : Role -> Access
        http
                .httpBasic()
                .and()
                    .authorizeRequests()
                    .antMatchers("students/**").hasRole("USER")
                    .antMatchers("/welcome").hasRole("USER")
                    .antMatchers("/**").hasRole("ADMIN")
                .and()
                    .csrf().disable().headers().frameOptions().disable();
    }
}
