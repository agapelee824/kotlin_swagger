package com.kotlin.swagger.todo.entity

import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder
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

    fun update(newTodo: Todo) {
//        this.password = newMember.newPassword
//            ?.takeIf { it.isNotBlank() }
//            ?.let { encoder.encode(it) }
//            ?: this.password
        this.title = newTodo.title
        this.description = newTodo.description
        this.completed =  newTodo.completed
    }
}