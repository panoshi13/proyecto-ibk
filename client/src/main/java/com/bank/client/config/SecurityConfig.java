package com.bank.client.config;

/*
import com.bank.client.service.ClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        //@formatter:off
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
                .pathMatchers("/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/webjars/**")
                .permitAll()
                .anyExchange().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .build();
        //@formatter:on
    }

    @Bean
    ReactiveAuthenticationManager authenticationManager(ClienteService clienteService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager((ReactiveUserDetailsService) clienteService);
    }
}

 */