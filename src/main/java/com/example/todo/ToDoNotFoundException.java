package com.example.todo;

public class ToDoNotFoundException extends RuntimeException {

    ToDoNotFoundException(Long id) {
        super("Could not find ToDo " + id);
    }
}