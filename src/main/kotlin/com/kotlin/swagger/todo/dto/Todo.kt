package com.kotlin.swagger.todo.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "할일 DTO")
data class Todo(
    @Schema(description = "id(PK)")
    val id: Long? = null,
    @Schema(description = "제목")
    val title: String = "",
    @Schema(description = "설명")
    val description: String = "",
    @Schema(description = "완료여부")
    val completed: Boolean = false
)
