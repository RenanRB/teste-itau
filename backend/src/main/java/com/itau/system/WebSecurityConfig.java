package com.itau.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final AdminServerProperties adminServer;

    public WebSecurityConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = 
          new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.getContextPath() + "/admin");
        http.authorizeRequests().anyRequest().permitAll()  
        .and().csrf().disable();
//        http
//        .authorizeRequests()
//            .antMatchers("/api/users/**").permitAll()
//            .antMatchers(this.adminServer.getContextPath() + "/assets/**").permitAll()
//            .antMatchers(this.adminServer.getContextPath() + "/login").permitAll()
//            .antMatchers(this.adminServer.getContextPath() + "/instances", this.adminServer.getContextPath() + "/instances/*").permitAll()
//            .anyRequest().authenticated()
//            .and()
//        .formLogin()
//            .loginPage(this.adminServer.getContextPath() + "/login")
//            .successHandler(successHandler)
//            .and()
//        .logout()
//            .logoutUrl(this.adminServer.getContextPath() + "/logout")
//            .and()
//        .httpBasic()
//            .and()
//        .csrf()
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .ignoringAntMatchers("/api/users/**")
//            .ignoringRequestMatchers(
//                new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/**"))
//            .and()
//        .rememberMe()
//            .key(UUID.randomUUID().toString())
//            .tokenValiditySeconds(1209600)
//            .and()
//        .csrf().disable();

        
        return http.build();
    }
}