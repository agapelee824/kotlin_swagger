package com.kotlin.swagger.member.controller

import com.kotlin.swagger.common.configuration.LoggerDelegator
import com.kotlin.swagger.common.dto.ApiResponse
import com.kotlin.swagger.common.dto.BaseResponse
import com.kotlin.swagger.member.dto.Member
import com.kotlin.swagger.member.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*
import java.util.*


@RequestMapping("/api/member")
@RestController
@Tag(name = "회원 API", description = "회원 테스트용 API")
class MemberController(private val memberService: MemberService) {
    val log by LoggerDelegator()

    @Operation(summary = "회원 가입", description = "회원 가입")
    @Parameter(name = "id", description = "회원의 id")
    @PostMapping("/signUp")
    fun signUp(@RequestBody member: Member): ResponseEntity<Any> {
        val id = memberService.createMember(member)
        return ResponseEntity
            .ok()
            .body(true)
    }

    @Operation(summary = "로그인", description = "로그인")
    //@PostMapping("/auth")
    @PostMapping("/login")
    // 로그인되지 않은 상태이면 로그인 페이지를, 로그인된 상태이면 home 페이지를 보여줌
//    fun loginPage(@RequestBody member: Member): ResponseEntity<Any>  {
//        val authentication: Authentication = SecurityContextHolder.getContext().authentication
//        //return if (authentication is AnonymousAuthenticationToken) "loginPage" else "redirect:/" // to vue.js
//        return ResponseEntity
//            .ok()
//            .body(true)
//    }
    fun signIn(@RequestBody member: Member): BaseResponse<Any> {
        val response = memberService.login(member)
        return BaseResponse(data = response)
    }

//    @Operation(summary = "로그아웃", description = "로그아웃")
//    @PostMapping("/logout")
//    fun logOut() {
//
//    }

    @Operation(summary = "내 정보 보기", description = "내 정보 보기")
    @GetMapping("/info")
    fun searchMyInfo(@AuthenticationPrincipal user: User) = ApiResponse.success(memberService.getMemberInfo(user.username))
}