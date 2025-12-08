package com.ClassExchange.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Jws<Claims> jws = jwtService.validate(token);
                Claims claims = jws.getBody();
                String subject = claims.getSubject();
                Object rolesObj = claims.get("roles");
                Object campusObj = claims.get("campus_id");
                List<String> roles = null;
                if (rolesObj instanceof List<?> rawList) {
                    roles = rawList.stream()
                                   .filter(String.class::isInstance)
                                   .map(String.class::cast)
                                   .toList();
                }
                List<SimpleGrantedAuthority> authorities = roles != null ? roles.stream().map(SimpleGrantedAuthority::new).toList() : List.of();
                String campusId = campusObj != null ? campusObj.toString() : null;
                AuthenticatedUser principal = new AuthenticatedUser(subject, campusId, roles != null ? roles : java.util.Collections.emptyList());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
