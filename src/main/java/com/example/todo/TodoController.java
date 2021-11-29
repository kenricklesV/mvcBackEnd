package com.example.todo;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(methods = { POST, GET, OPTIONS, DELETE, PATCH }, maxAge = 3600, allowedHeaders = { "x-requested-with",
        "origin", "content-type", "accept" }, origins = "*")
@RequestMapping("/todos")
@RestController
public class TodoController {

    // initalise repo linkage
    private final ToDoRepo toDoRepo;

    public TodoController(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }

    // get all the todos
    @GetMapping
    public List<Todo> getAll() {
        return toDoRepo.findAll();
    }

    // Delete all the data from the table in the repo via delete method
    @DeleteMapping

    public ResponseEntity<Void> deleteAll() {
        toDoRepo.deleteAll();
        return ResponseEntity.ok().build();
    }

    // Enter a new entry via method method
    @PostMapping
    public Todo create(@RequestBody @Validated Todo newToDo, HttpServletRequest httpServletRequest) {
        newToDo.setUrl(httpServletRequest.getRequestURL().toString());
        return toDoRepo.save(newToDo);
    }

    // get a single entry via get method + /id path
    @GetMapping(path = "/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id) {
        return ResponseEntity.of(toDoRepo.findById(id));
    }

    // delete a single entry via delete method + /id path
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Todo> deleteById(@PathVariable Long id) {
        final Optional<Todo> todo = toDoRepo.findById(id);
        toDoRepo.deleteById(id);
        return ResponseEntity.of(todo);
    }

    // update a single entry via patch method + /id path
    @RequestMapping(path = "/{id}")
    public ResponseEntity<Todo> updateById(@PathVariable Long id, @RequestBody @Validated Todo todo) {
        // We break it down into different parts, we handle each column on its own
        // First we find the ID itself, so we know what to edit
        Optional<Todo> previousEntry = toDoRepo.findById(id);
        // if the ID is not found, we return a 404
        if (previousEntry.isEmpty()) {
            return ResponseEntity.of(previousEntry);
        }
        // if the ID is found, we edit the data
        // We patch the data with the new data from the request
        // We start with the title
        if (todo.getTitle() != null) {
            previousEntry.get().setTitle(todo.getTitle());
        }
        // Thereafer we do the completed status
        if (todo.isCompleted() != false) {
            previousEntry.get().setCompleted(todo.isCompleted());
        }
        // Finally we do the order
        if (todo.getOrder() != null) {
            previousEntry.get().setOrder(todo.getOrder());
        }
        // Next we update the entry itself
        toDoRepo.save(previousEntry.get());
        // We return the updated entry
        return ResponseEntity.ok(previousEntry.get());
    }
}
