package net.springbootprojects.todomanagement.service.impl;

import lombok.AllArgsConstructor;
import net.springbootprojects.todomanagement.dto.TodoDto;
import net.springbootprojects.todomanagement.entity.Todo;
import net.springbootprojects.todomanagement.exception.ResourceNotFoundException;
import net.springbootprojects.todomanagement.repository.TodoRepository;
import net.springbootprojects.todomanagement.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // convert todto into todo jpa entity
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());

        Todo todo = modelMapper.map(todoDto, Todo.class);

        //todo jpa entity

       Todo savedTodo = todoRepository.save(todo);

       // convert savedTodo jpa entity object into TodoDto object

//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodoDto.isCompleted());
//        return savedTodoDto;

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
       Todo todo = todoRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Todo not found withId: "+ id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

      List<Todo> todos =  todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).
                collect(Collectors.toList());

    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

       Todo todo = todoRepository.findById(id).
               orElseThrow(()-> new ResourceNotFoundException("Todo not found with idc: "+id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Todo not found with id : "+id));
        todoRepository.deleteById(id);

    }

    @Override
    public TodoDto completeTodo(Long id) {
       Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Not found with id : "+id));
       todo.setCompleted(Boolean.TRUE);

       Todo updateTodo = todoRepository.save(todo);

        return modelMapper.map(updateTodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Not found with id : "+id));
        todo.setCompleted(Boolean.FALSE);

        Todo updateTodo = todoRepository.save(todo);

        return modelMapper.map(updateTodo, TodoDto.class);
    }
}
