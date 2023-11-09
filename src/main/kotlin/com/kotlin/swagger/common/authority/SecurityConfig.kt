package com.kotlin.swagger.common.authority

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }

        // 권한에 따라 허용하는 url 설정
        // /login, /join 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
        http
            .authorizeHttpRequests { authorize ->
                authorize
//                    .requestMatchers("/api/member/join").permitAll()
//                    .requestMatchers("/api/member/login").permitAll()
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/api/v3/**", "/health", "/swagger-ui/**", "/swagger/**", "/swagger-resources/**"
                        , "/webjars/**", "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated()
            }

        // login 설정
//        http
//            .formLogin()
//            .loginPage("/login")    // GET 요청 (login form을 보여줌)
//            .loginProcessingUrl("/auth")    // POST 요청 (login 창에 입력한 데이터를 처리)
//            //.usernameParameter("email")	// login에 필요한 id 값을 email로 설정 (default는 username)
//            .passwordParameter("password")	// login에 필요한 password 값을 password(default)로 설정
//            .defaultSuccessUrl("/");	// login에 성공하면 /로 redirect

        return http.build()
    }
}