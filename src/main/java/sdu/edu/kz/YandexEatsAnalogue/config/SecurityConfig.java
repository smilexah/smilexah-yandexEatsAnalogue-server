package sdu.edu.kz.YandexEatsAnalogue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] WHITELIST = {
            "/error",
            "/registration",
            "/registration/**",
            "/login",
            "/logout",
            "/",
            "/restaurants",
            "/restaurants/**",
            "/menu-items",
            "/menu-items/**",
            "/orders",
            "/orders/**",
            "/deliveries",
            "/deliveries/**",
            "/order-items",
            "/order-items/**",
            "/details",
            "/details/**",
            "/ratings",
            "/ratings/**",
            "/users",
            "/users/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITELIST).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/partner/**").hasAnyRole("PARTNER", "ADMIN")
                        .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/restaurants", true)
                        .failureUrl("/login?error=true"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"));

        http.csrf(AbstractHttpConfigurer::disable)
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
}