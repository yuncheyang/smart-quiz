package com.yunche.common.config.security;

import com.yunche.common.interceptor.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * security配置类
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 跨域配置，放在最前面
                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration corsConfig = new CorsConfiguration();
                            corsConfig.addAllowedOriginPattern("*"); // 允许所有来源
                            corsConfig.setAllowCredentials(true); // 允许发送凭证（如 cookies）
                            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT")); // 允许的 HTTP 方法
                            corsConfig.setAllowedHeaders(List.of("*")); // 允许所有请求头
                            corsConfig.setMaxAge(3600L); // 跨域允许时间，单位秒

                            return corsConfig;
                        })
                )
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF
                // 设置会话管理策略，不使用 Session 来存储 SecurityContext（无状态认证）
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // 放行 Swagger 文档相关路径
                        .requestMatchers("/user/doLogin", "/user/register").permitAll()
                        // 其他请求需要认证
                        .anyRequest().authenticated()
                );

        // 添加 JWT 校验过滤器到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // 结束配置
    }

    // 配置 PasswordEncoder（密码加密方式）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置 AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
