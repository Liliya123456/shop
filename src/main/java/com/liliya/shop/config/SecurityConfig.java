package com.liliya.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {
    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                //права доступа
                .antMatchers("/api/orders/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
                .antMatchers("/api/**").permitAll()
               .anyRequest().permitAll()
                .and().formLogin().disable()
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable();
        return http.build();
    }


}
