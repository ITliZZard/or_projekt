package fer.project.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/", "/error", "/logo", "/authors", "/authors/**", "/index", "/css/**", "/js/**", "/images/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new SimpleUrlAuthenticationSuccessHandler("/index"))
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint("/error"))
                        .accessDeniedHandler(new Auth0AccessDeniedHandler("http://localhost:8090/error"))
                )
                .logout(logout -> logout
                                .logoutSuccessUrl("/index") // Redirect to home page after logout
                        // Do not specify logoutUrl pointing to Auth0's logout endpoint
                );

        return http.build();
    }
}