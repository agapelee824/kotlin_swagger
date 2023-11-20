package com.kotlin.swagger.todo.service

//import com.kotlin.swagger.todo.dto.Todo
import com.kotlin.swagger.common.dto.ApiResponse
import com.kotlin.swagger.todo.dto.TodoResponse
import com.kotlin.swagger.todo.dto.TodoUpdateResponse
import com.kotlin.swagger.todo.entity.Todo
import com.kotlin.swagger.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
//import com.kotlin.swagger.todo.mapper.TodoMapper
import org.springframework.stereotype.Service

@Service
class TodoService(/*private val todoMapper: TodoMapper*/
                  private val todoRepository: TodoRepository,
                  private val todoService: TodoRepository
) {
//    fun getTodos(): List<Todo> = todoMapper.selectTodos()
    fun getTodos(): List<Todo> = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))

    @Transactional
    fun getTodoInfo(id: Long) =
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
    @Transactional
    fun updateTodo(request: Todo): TodoUpdateResponse {
        val todo = todoRepository.findByIdOrNull(request.id)?.takeIf { request.id == it.id }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        todo.update(request)
        return TodoUpdateResponse.of(true, todo)
    }

//    fun deleteTodo(id: Long): Long = todoMapper.deleteTodo(id)
    @Transactional
    fun deleteTodo(id: Long): Boolean {
        if (!todoRepository.existsById(id))
            return false
        todoRepository.deleteById(id)
        return true
    }
}