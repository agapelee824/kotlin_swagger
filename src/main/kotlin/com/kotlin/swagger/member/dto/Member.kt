package com.kotlin.swagger.member.dto

import com.kotlin.swagger.common.enum.MemberType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 DTO")
data class Member(
    @Schema(description = "id(PK)")
    val id: Long? = null,
    val loginId: String? = "",
    val password: String? = "",
    val name: String? = "",
    val birthDate: String? = "",
    val gender: String? = "",
    val email: String? = "",
)

data class SignInResponse(
    @Schema(description = "회원 이름", example = "콜라곰")
    val name: String?,
    //@Schema(description = "회원 유형", example = "USER")
    //val type: MemberType,
    val token: String
)