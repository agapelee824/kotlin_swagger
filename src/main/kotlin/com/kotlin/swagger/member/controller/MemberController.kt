package com.kotlin.swagger.member.controller

import com.kotlin.swagger.common.configuration.LoggerDelegator
import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/member")
@RestController
@Tag(name = "회원 API", description = "회원 테스트용 API")
class MemberController(private val memberService: MemberService) {
    val log by LoggerDelegator()


    @Operation(summary = "회원 가입", description = "회원 가입")
    @Parameter(name = "id", description = "회원의 id")
    @PostMapping("/join")
    fun signUp(@RequestBody member: Member): ResponseEntity<Any> {
        val id = memberService.createMember(member)
        return ResponseEntity
            .ok()
            .body(true)
    }

//    @Operation(summary = "로그인", description = "로그인")
//    @GetMapping("/login")
//    fun login(@RequestBody member: Member): String  {
//        //m.addAttribute("member", memberService.selectMemeberById(2));
//        //return "index"
//
//        val authentication: Authentication = SecurityContextHolder.getContext().authentication
//        return if (authentication is AnonymousAuthenticationToken) "loginPage" else "redirect:/"
//    }
}