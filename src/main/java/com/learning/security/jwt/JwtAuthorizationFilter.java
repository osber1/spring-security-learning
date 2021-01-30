package com.learning.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.learning.security.security.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;
    private JwtConfig config;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsService userDetailsService,
                                  JwtConfig config) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.config = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String authHeader = request.getHeader(config.getAuthorizationHeader());

        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith(config.getTokenPrefix())) {
            chain.doFilter(request, response);
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(authHeader);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String authHeader) {

        if (authHeader != null) {
            String usernameFromJwt = JWT.require(Algorithm.HMAC512(config.getSecret()))
                    .build()
                    .verify(authHeader.replace(config.getTokenPrefix(), ""))
                    .getSubject();

            if (usernameFromJwt != null) {
                return new UsernamePasswordAuthenticationToken(usernameFromJwt,
                        null,
                        userDetailsService.loadUserByUsername(usernameFromJwt).getAuthorities());
            }
        }
        return null;
    }
}
