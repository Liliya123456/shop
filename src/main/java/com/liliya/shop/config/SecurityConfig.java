package com.liliya.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration

public class SecurityConfig {

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {
        http

                .authorizeRequests(this::configureAuthorization)
                .formLogin(this::configureLogin)
                .logout(this::configureLogout)
                .exceptionHandling().authenticationEntryPoint(defaultEntryPoint()).and()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                .cors().disable()
                .csrf().disable();

        return http.build();
    }

    private void configureAuthorization(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
                //права доступа
                .antMatchers(HttpMethod.POST, "/login", "/logout").permitAll()
                .antMatchers(HttpMethod.GET, "/api/item/**", "/api/category/**", "/api/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/new").permitAll()
                .antMatchers("/api/item/**", "/api/category/**", "/api/order/**", "api/user/**").hasRole("ADMIN")
                .antMatchers("/api/cart/**").authenticated()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and();
    }


    private void configureLogin(FormLoginConfigurer<HttpSecurity> config) {
        config
                .loginPage("/login")
                .successHandler(noopAuthenticationSuccessHandler())
                .failureHandler(noopAuthenticationFailureHandler());
    }

    private void configureLogout(LogoutConfigurer<HttpSecurity> config) {
        config
                .logoutUrl(null)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST", false))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT));
    }

    @Bean
    public AuthenticationEntryPoint defaultEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Bean
    public AuthenticationFailureHandler noopAuthenticationFailureHandler() {
        return new AuthenticationEntryPointFailureHandler(defaultEntryPoint());
    }

    @Bean
    public AuthenticationSuccessHandler noopAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
