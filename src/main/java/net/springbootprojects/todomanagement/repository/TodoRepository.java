package net.springbootprojects.todomanagement.repository;

import net.springbootprojects.todomanagement.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository<Todo, Long> {

}
