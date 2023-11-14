package com.kotlin.swagger.todo.repository

import com.kotlin.swagger.todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TodoRepository: JpaRepository<Todo, Long> {

}