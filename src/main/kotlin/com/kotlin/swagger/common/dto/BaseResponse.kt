package com.kotlin.swagger.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.kotlin.swagger.common.annotation.ValidEnum
import com.kotlin.swagger.common.status.ResultCode
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BaseResponse<T>(
    @field:NotBlank
    @field:ValidEnum(enumClass = ResultCode::class, message = "SUCCESS, ERROR 로 값이 전달됩니다.")
    @Schema(description = "결과 코드", required = true)
    val resultCode: String = ResultCode.SUCCESS.name,

    @field:NotBlank
    @Schema(description = "결과 데이터")
    val data: T? = null,

    @field:NotBlank
    @Schema(description = "결과메세지", required = true)
    val message: String = ResultCode.SUCCESS.msg,
)
