package dev.branchesandgomes.config;

import dev.branchesandgomes.config.filter.TokenAuthFilter;
import dev.branchesandgomes.config.filter.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final TokenAuthFilter tokenAuthFilter;
    private final UserFilter userFilter;
    private static final String[] PUBLIC_ENDPOINTS = {
            "/auth/**",
            "/swagger-ui",
            "/api-docs",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/{tenantExternalId}/products"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        http.csrf(AbstractHttpConfigurer::disable)
                .headers(headers ->
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(userFilter, TokenAuthFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }
}