package com.learning.security.security;


import com.learning.security.jwt.JwtAuthenticationFilter;
import com.learning.security.jwt.JwtAuthorizationFilter;
import com.learning.security.jwt.JwtConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfig config;
    private final BCryptPasswordEncoder encoder;
    private final UserDetailsService userDetailsService;
    private final BooksWsAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/books/{id}").access("hasRole('USER') or hasRole('ADMIN') and hasAuthority('GET_BOOK')")
                .antMatchers("/api/books").access("hasRole('ADMIN') and hasAuthority('MANAGE_BOOK')")
                .anyRequest().authenticated()
                .and().addFilter(new JwtAuthenticationFilter(authenticationManager(), config))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService, config))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }
}
