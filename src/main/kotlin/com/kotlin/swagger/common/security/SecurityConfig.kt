package com.kotlin.swagger.common.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
class SecurityConfig(private val jwtAuthenticationFilter: JwtAuthenticationFilter)
{
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .cors().disable()

        // 권한에 따라 허용하는 url 설정
        // /login.. 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/signUp").permitAll()
                    .requestMatchers("/api/member/login").permitAll()
//                    .requestMatchers("/auth").permitAll()
//                    .requestMatchers("/api/**").permitAll()
//                    .requestMatchers("/api/v3/**", "/health", "/swagger-ui/**", "/swagger/**", "/swagger-resources/**", "/webjars/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers("/", "/swagger-ui/**", "/v3/**").permitAll()
                    .anyRequest().authenticated()
            }

        // login 설정
        http
            .formLogin()
            .loginPage("/login")    // GET 요청 (login form을 보여줌)
//            .loginProcessingUrl("/auth")    // POST 요청 (login 창에 입력한 데이터를 처리)
            .usernameParameter("loginId")	// login에 필요한 id 값을 email로 설정 (default는 username)
            .passwordParameter("password")	// login에 필요한 password 값을 password(default)로 설정
            .defaultSuccessUrl("/index");	// login에 성공하면 /로 redirect

        // logout 설정
        http
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");	// logout에 성공하면 /로 redirect

        http
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) } // 세션을 사용하지 않으므로 STATELESS

        http
            .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)	// 추가

        return http.build()
    }
}