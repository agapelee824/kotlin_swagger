package com.kotlin.swagger.todo.controller

import com.kotlin.swagger.common.configuration.LoggerDelegator
import com.kotlin.swagger.common.dto.ApiResponse
import com.kotlin.swagger.common.dto.BaseResponse
//import com.kotlin.swagger.todo.dto.Todo
import com.kotlin.swagger.todo.entity.Todo
import com.kotlin.swagger.todo.service.TodoService
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/todo")
@RestController
@Tag(name = "예제 API", description = "Swagger 테스트용 API")
class TodoController(private val todoService: TodoService) {
    val log by LoggerDelegator()

    @Operation(summary = "Todo 목록", description = "Todo 목록을 출력한다.")
    @GetMapping("/list")
    fun getTodos(): List<Todo> = todoService.getTodos()

    @Operation(summary = "Todo 정보", description = "파라미터로 받은 id의 Todo 정보를 출력한다.")
    @Parameter(name = "id", description = "Todo의 id")
    @GetMapping("/info/{id}")
    fun getTodoInfo(@PathVariable id: Long) = ApiResponse.success(todoService.getTodoInfo(id))

    @Operation(summary = "Todo 추가", description = "Todo 등록")
    @PostMapping("/add")
    fun createTodo(@RequestBody todo:Todo): ResponseEntity<Any> {
        val id = todoService.createTodo(todo)
        return ResponseEntity
            .ok()
            .body(true)
    }

    @Operation(summary = "Todo 수정", description = "Todo 수정")
    @PutMapping("/update")
    fun updateTodo(@RequestBody todo:Todo) =
        ApiResponse.success(todoService.updateTodo(todo))

    @Operation(summary = "Todo 삭제", description = "Todo 삭제")
    @DeleteMapping("/delete/{id}")
    fun deleteTodo(@PathVariable id: Long) =
        ApiResponse.success(todoService.deleteTodo(id))

    @Hidden
    @GetMapping("/ignore")
    fun ignore() = "무시되는 API"
}