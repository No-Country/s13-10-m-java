package com.nocountrys13.ecoapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService service;
    private final JwtProvider jwtProvider;
    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwt;

        try {

            if (authorization != null && authorization.startsWith("Bearer")) {

                jwt = authorization.substring(7);

                if (!jwtProvider.extractExpiration(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {

                    var userDetails = service.loadUserByUsername(jwtProvider.extractEmail(jwt));

                    var authRequest = new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.getPassword(), userDetails.getAuthorities()
                    );
                    authRequest.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authRequest);
                }

            }
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
