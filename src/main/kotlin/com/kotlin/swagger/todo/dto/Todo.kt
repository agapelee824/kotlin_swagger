package com.kotlin.swagger.todo.dto

import com.kotlin.swagger.todo.entity.Todo
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

@Schema(description = "할일 DTO")
data class TodoResponse(
    @Schema(description = "id(PK)")
    val id: Long? = null,
    @Schema(description = "제목")
    val title: String? = "",
    @Schema(description = "설명")
    val description: String? = "",
    @Schema(description = "완료여부")
    val completed: Boolean? = false
) {
    companion object {
        fun from(todo: Todo) = TodoResponse(
            id = todo.id!!,
            title = todo.title,
            description = todo.description,
            completed = todo.completed
        )
    }
}
