package com.kotlin.swagger.todo.entity

import jakarta.persistence.*
import java.util.*

@Entity
class Todo(
//    @Column(nullable = false, unique = true)
//    val id: Long,
    var title: String?,
    var description: String? = null,
    var completed: Boolean? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    companion object {
        fun from(todo: Todo) = Todo(
            title = todo.title,
            description = todo.description,
            completed = todo.completed
        )
    }
}