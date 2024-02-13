package com.nocountrys13.ecoapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   @Bean
   public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
      http
           .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
           )
           .cors(cors -> cors.disable())
           .csrf(csrf -> csrf.disable())
           .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

      return http.build();
   }




}
