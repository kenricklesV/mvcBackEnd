package com.example.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<Todo, Long> {

}
