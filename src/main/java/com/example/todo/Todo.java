package com.example.todo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {
    @Column(name = "id")
    private @Id @GeneratedValue Long id;
    private String title;
    private Boolean completed;
    private Integer placing;

    public Todo() {
    }

    public Todo(String title) {
        this.title = title;
    }

    public Todo(Long id, String title, Boolean completed, Integer placing) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.placing = placing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Todo todo = (Todo) o;

        if (id != todo.id)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.completed, this.placing);
    }

    @Override
    public String toString() {
        return "Todo{" + "id=" + this.id + ", title='" + this.title + '\'' + ", completed=" + this.completed
                + ", placing=" + this.placing + '}';
    }

    public boolean isCompleted() {
        return nonNull(completed, false);
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPlacing() {
        return nonNull(placing, 0);
    }

    public void setPlacing(Integer placing) {
        this.placing = placing;
    }

    public Todo merge(Todo newTodo) {
        return new Todo(id, nonNull(newTodo.title, title), nonNull(newTodo.completed, completed),
                nonNull(newTodo.placing, placing));
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private <T> T nonNull(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }
}
