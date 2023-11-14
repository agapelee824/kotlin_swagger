package com.kotlin.swagger.todo.service

//import com.kotlin.swagger.todo.dto.Todo
import com.kotlin.swagger.common.dto.ApiResponse
import com.kotlin.swagger.todo.dto.TodoResponse
import com.kotlin.swagger.todo.entity.Todo
import com.kotlin.swagger.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
//import com.kotlin.swagger.todo.mapper.TodoMapper
import org.springframework.stereotype.Service

@Service
class TodoService(/*private val todoMapper: TodoMapper*/
                  private val todoRepository: TodoRepository,
                  private val todoService: TodoRepository
) {
//    fun getTodos(): List<Todo> = todoMapper.selectTodos()

//    fun getTodoInfo(id: Long): Todo = todoMapper.findById(id)
    @Transactional
    fun getTodoInfo(id: Long) =
//    todoRepository.findById(id)
    todoRepository.findByIdOrNull(id)?.let {
        TodoResponse.from(
            it
        )
    }

    @Transactional
    fun createTodo(request: Todo) = Todo.from(
        todoRepository.saveAndFlush(request)
    )

//    fun updateTodo(todo: Todo): Long = todoMapper.updateTodo(todo)

//    fun deleteTodo(id: Long): Long = todoMapper.deleteTodo(id)
}