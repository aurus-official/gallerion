package com.ar.gallerion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.ar.gallerion.user.LoginValidationService;

@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfig {

    private LoginValidationService loginValidationService;

    @Autowired
    SecurityConfig(LoginValidationService loginValidationService) {
        this.loginValidationService = loginValidationService;
    }

    @Bean
    SecurityFilterChain setupSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("POST", "/v1/user").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(setupAuthenticationProvider())
                .httpBasic(basic -> basic.securityContextRepository(new HttpSessionSecurityContextRepository()))
                .logout(logout -> logout.logoutUrl("/v1/user/logout"));
        return http.build();
    }

    AuthenticationProvider setupAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(loginValidationService);
        return provider;
    }

    @Bean
    AuthenticationManager authenticateManger(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
