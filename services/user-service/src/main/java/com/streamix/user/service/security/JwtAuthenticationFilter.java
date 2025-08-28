package com.streamix.user.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = jwtUtil.extractTokenFromHeader(authHeader);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                if (!jwtUtil.isTokenExpired(token) && jwtUtil.isValidToken(token)) {
                    String username = jwtUtil.getUsernameFromToken(token);
                    Collection<? extends GrantedAuthority> authorities = extractAuthoritiesFromToken(token);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, token, authorities);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    log.debug("Authentication successful for user: {} with authorities: {}", username, authorities);
                }
            } catch (Exception e) {
                log.debug("Authentication failed: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> extractAuthoritiesFromToken(String token) {
        try {
            Object authoritiesClaim = jwtUtil.getClaimFromToken(token, "authorities");

            if (authoritiesClaim instanceof List<?> authoritiesList) {
                return authoritiesList.stream()
                        .filter(Map.class::isInstance)
                        .map(Map.class::cast)
                        .filter(map -> map.containsKey("authority"))
                        .map(map -> new SimpleGrantedAuthority(map.get("authority").toString()))
                        .toList();
            }

            return List.of();
        } catch (Exception e) {
            log.debug("Failed to extract authorities from token: {}", e.getMessage());
            return List.of();
        }
    }
} 