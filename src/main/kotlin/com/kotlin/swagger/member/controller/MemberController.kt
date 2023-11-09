package com.kotlin.swagger.member.controller

import com.kotlin.swagger.common.configuration.LoggerDelegator
import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.service.MemberService
import com.kotlin.swagger.todo.dto.Todo
import com.kotlin.swagger.todo.service.TodoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/member")
@RestController
@Tag(name = "회원 API", description = "회원 테스트용 API")
class MemberController(private val memberService: MemberService) {
    val log by LoggerDelegator()

    @Operation(summary = "회원 가입", description = "회원 가입")
    @Parameter(name = "id", description = "회원의 id")
    @PostMapping("/add")
    fun signUp(@RequestBody member: Member): ResponseEntity<Any> {
        val id = memberService.createMember(member)
        return ResponseEntity
            .ok()
            .body(true)
    }

    @Operation(summary = "로그인", description = "토큰 발행")
    fun login(loginDto: LoginDto): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}