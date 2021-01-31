package com.learning.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtConfig config;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtConfig config) {
        super(authenticationManager);
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
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(config.getSecret()))
                    .build()
                    .verify(authHeader.replace(config.getTokenPrefix(), ""));

            if (decodedJWT != null) {
                String usernameFromJwt = decodedJWT.getSubject();

                if (usernameFromJwt != null) {
                    Set<SimpleGrantedAuthority> authorities = Stream.of(decodedJWT.getClaim("roles")
                            .asString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toSet());

                    return new UsernamePasswordAuthenticationToken(usernameFromJwt,
                            null,
                            authorities);
                }
            }
        }
        return null;
    }
}
