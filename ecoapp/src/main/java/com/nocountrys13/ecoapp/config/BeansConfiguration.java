package com.nocountrys13.ecoapp.config;

import com.nocountrys13.ecoapp.repositories.UsuarioRepository;
import com.nocountrys13.ecoapp.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {

   private final UsuarioRepository repository;

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public UserDetailsService userDetailsService() {
      return email -> Optional
           .of(repository.findByEmail(email))
           .map(user -> new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(Role.USER.name())))
           )
           .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
   }

   @Bean
   public AuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userDetailsService());
      authenticationProvider.setPasswordEncoder(passwordEncoder());
      return authenticationProvider;
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
   }

   @Bean
   public CorsConfigurationSource corsConfigurationSource() {

      var corsConfiguration = new CorsConfiguration();

      corsConfiguration.setAllowedOrigins(List.of("*"));
      corsConfiguration.setAllowedHeaders(List.of("*"));
      corsConfiguration.setAllowedMethods(List.of("*"));

      var source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", corsConfiguration);

      return source;
   }


}
