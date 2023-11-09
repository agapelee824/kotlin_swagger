package com.kotlin.swagger.member.dto

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
