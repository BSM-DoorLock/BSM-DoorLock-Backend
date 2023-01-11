package bssm.doorlock.global.security;

import bssm.doorlock.domain.auth.exception.UnAuthorizedException;
import bssm.doorlock.global.auth.AuthFilterExceptionHandler;
import bssm.doorlock.global.exception.GeneralErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthFilterExceptionHandler authFilterExceptionHandler;
    private final ObjectMapper objectMapper;

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (req, res, e) -> {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().write(objectMapper.writeValueAsString(new GeneralErrorResponse(new UnAuthorizedException())));
            res.getWriter().flush();
            res.getWriter().close();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable();
        http
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authFilterExceptionHandler, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}