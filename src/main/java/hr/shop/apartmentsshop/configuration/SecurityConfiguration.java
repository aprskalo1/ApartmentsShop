package hr.shop.apartmentsshop.configuration;

import hr.shop.apartmentsshop.listener.LoginSuccessEventListener;
import hr.shop.apartmentsshop.publisher.AuthenticationSuccessEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/h2-console/**",
                                "/apartments/create",
                                "/apartments/delete",
                                "/purchase/allPurchases",
                                "/log/loginlog"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                "paypal/payment/create",
                                "/paypal/payment/cancel",
                                "/paypal/payment/success",
                                "/purchase/myPurchases"
                        ).hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/403")
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessEventPublisher authenticationSuccessEventPublisher() {
        return new AuthenticationSuccessEventPublisher();
    }

    @Bean
    public LoginSuccessEventListener loginSuccessEventListener() {
        return new LoginSuccessEventListener();
    }
}