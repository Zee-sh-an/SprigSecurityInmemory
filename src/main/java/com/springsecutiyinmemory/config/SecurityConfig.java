package com.springsecutiyinmemory.config;

import lombok.Builder;
import org.hibernate.annotations.Bag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService getUserDetailService(){

        UserDetails normalUser = User
                .withUsername("zk@gmail.com")
                .password(passwordEncoder().encode("zeeshan"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(normalUser);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(getUserDetailService());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain doFilter(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf((csrf)->csrf.disable())
                .cors((cors)->cors.disable())
                .authorizeHttpRequests((auth)->auth.requestMatchers("/home/**")
                        .authenticated())
//                        .permitAll().anyRequest().authenticated())
//                        .authenticated().requestMatchers("/").permitAll())
//                .formLogin(Customizer.withDefaults());
                .httpBasic(withDefaults());
        return httpSecurity.build();
    }
}
