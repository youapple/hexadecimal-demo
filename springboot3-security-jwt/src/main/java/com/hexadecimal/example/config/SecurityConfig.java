package com.hexadecimal.example.config;

import com.hexadecimal.example.jwt.filter.JwtAuthenticationFilter;
import com.hexadecimal.example.jwt.handler.JwtAccessDeniedHandler;
import com.hexadecimal.example.jwt.handler.JwtLogoutSuccessHandler;
import com.hexadecimal.example.jwt.handler.LoginFailureHandler;
import com.hexadecimal.example.jwt.handler.LoginSuccessHandler;
import com.hexadecimal.example.jwt.point.JwtAuthenticationEntryPoint;
import com.hexadecimal.example.jwt.userdetails.AccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private static final String[] URL_WHITELIST = {"/user/login", "/favicon.ico"};

    @Autowired
    private AccountUserDetailsService accountUserDetailsService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     @Bean public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
     }
     **/

    /**
     * @return 身份校验机制、身份验证提供程序
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // 创建一个用户认证提供者
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // 设置用户相关信息，可以从数据库中读取、或者缓存、或者配置文件
        authProvider.setUserDetailsService(accountUserDetailsService);
        // 设置加密机制，用于对用户进行身份验证
        //authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * 基于用户名和密码或使用用户名和密码进行身份验证
     *
     * @param config
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用csrf(防止跨站请求伪造攻击)
                .csrf(csrf -> csrf.disable())
                // 登录操作
                .formLogin(form -> form.successHandler(loginSuccessHandler).failureHandler(loginFailureHandler))
                // 登出操作
                .logout(logout -> logout.logoutSuccessHandler(jwtLogoutSuccessHandler))
                // 使用无状态session，即不使用session缓存数据
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 设置白名单
                .authorizeHttpRequests(auth -> auth.requestMatchers(URL_WHITELIST).permitAll().anyRequest().authenticated())
                // 异常处理器
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler))
                // 添加jwt过滤器
                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
