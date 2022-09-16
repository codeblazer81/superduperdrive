package com.udacity.jwdnd.course1.cloudstorage.config;


import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /*@Bean
    protected AuthenticationManagerBuilder authProviderCustomizer(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
        return auth;
    }*/

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers( "/signup","/css/**", "/js/**").permitAll()
        .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/home", true);
        
        http.logout()
                .logoutUrl("/logout");

        http.authenticationProvider(this.authenticationService);

        return http.build();

    }
    /*protected HttpSecurity httpSecurityCustomizer(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/signup","/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/chat", true);

        return http;
    }*/

   


}
