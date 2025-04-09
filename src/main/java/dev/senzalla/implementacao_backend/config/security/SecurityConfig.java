package dev.senzalla.implementacao_backend.config.security;

import dev.senzalla.implementacao_backend.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Configuração de segurança da aplicação
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CorsConfig corsConfig;


    /**
     * Configura a cadeia de filtros de segurança HTTP
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        configureAuthentication(http, authenticationManager);
        configureCorsAndCsrf(http);
        configureAuthorization(http);
        configureSessionManagement(http);
        
        return http.build();
    }

    /**
     * Configura o gerenciador de autenticação
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false); // Para melhor diagnóstico
        return new ProviderManager(provider);
    }





    /**
     * Configura a autenticação com filtros JWT
     */
    private void configureAuthentication(HttpSecurity http, AuthenticationManager authenticationManager) {
        http.addFilter(new JwtAuthorizationFilter(authenticationManager, jwtService));
    }
    
    /**
     * Configura CORS e CSRF
     */
    private void configureCorsAndCsrf(HttpSecurity http) throws Exception {
        http.cors(corsConfigurer -> corsConfig.corsConfigurationSource());
        http.csrf(AbstractHttpConfigurer::disable);
    }
    
    /**
     * Configura as regras de autorização para endpoints
     */
    private void configureAuthorization(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/api-docs/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated());
    }
    
    /**
     * Configura o gerenciamento de sessão
     */
    private void configureSessionManagement(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }
} 