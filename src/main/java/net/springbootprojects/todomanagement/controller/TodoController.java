package net.springbootprojects.todomanagement.controller;

import lombok.AllArgsConstructor;
import net.springbootprojects.todomanagement.dto.TodoDto;
import net.springbootprojects.todomanagement.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/todos")
public class TodoController {

    private TodoService todoService;

    //build addTodoRestApi
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
    TodoDto savedTodo = todoService.addTodo(todoDto);
    return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //build get todo rest api

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    //build getall todos rest api
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
       List<TodoDto> todos = todoService.getAllTodos();
       return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PutMapping("{id}")
    //build update todo rest api
    public  ResponseEntity<TodoDto> updateTodo
    (@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId){
      TodoDto updatedTodo =  todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    //build delete todo rest api using id

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("todo deleted successfuly");
    }

    //Build complete todo rest api
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){

      TodoDto updatedTodo =  todoService.completeTodo(todoId);

      return ResponseEntity.ok(updatedTodo);

    }

    //build incopmlete todo rest api
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId){
       TodoDto updatedTodo = todoService.incompleteTodo(todoId);
       return ResponseEntity.ok(updatedTodo);
    }

}
