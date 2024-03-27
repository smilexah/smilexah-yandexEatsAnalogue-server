//package sdu.edu.kz.YandexEatsAnalogue.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
////    private static final String[] WHITELIST = {
////            "/",
////    };
//
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login")
//                                .permitAll()
//                )
//                .logout(logout ->
//                        logout.permitAll()
//                );
//
//        http.headers(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }
//}