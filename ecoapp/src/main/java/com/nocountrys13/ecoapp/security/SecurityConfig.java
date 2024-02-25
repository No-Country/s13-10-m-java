package com.nocountrys13.ecoapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationManager authenticationManager;
    private final JwtFilter filter;

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
<<<<<<< HEAD
                        .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"api/v1/email/verify").permitAll() 
                        .requestMatchers("static/**", "templates/**").permitAll()
=======
                        .requestMatchers(HttpMethod.POST, "/auth/**", "/auth/login").permitAll()
>>>>>>> backend
                        .requestMatchers("swagger-ui/**", "swagger-ui**", "/v3/api-docs/**", "/v3/api-docs**" ).permitAll()
                        .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .authenticationManager(authenticationManager)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
