package com.kotlin.swagger.todo.dto

data class Todo(
    val id: Long? = null,
    val title: String = "",
    val description: String = "",
    val completed: Boolean = false
)
