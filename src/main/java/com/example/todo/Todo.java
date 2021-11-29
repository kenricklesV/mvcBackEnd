package com.example.todo;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Todo {

    // creation of the model for the table
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_seq", allocationSize = 1)
    private Long id; // auto generate ID for H2 using SEQEUNCE
    private String url;
    @Column(name = "title")
    private String title; // set title column
    @Column(name = "completed")
    private Boolean completed = Boolean.FALSE; // set completed column
    @Column(name = "\"order\"")
    private Long order; // set order column

    // initalise Todo
    public Todo() {
    }

    // get id of Todo entry
    public Long getId() {
        return id;
    }

    // get URL of Todo entry
    public String getUrl() {
        return url + "/" + id;
    }

    // get title of Todo entry
    public String getTitle() {
        return title;
    }

    // get completion status of ToDo entry
    public boolean isCompleted() {
        return completed;
    }

    // get order of Todo entry
    public Long getOrder() {
        return order;
    }

    // change the Id of the entry
    public void setId(Long id) {
        this.id = id;
    }

    // change the URL of the entry
    public void setUrl(String url) {
        this.url = url;
    }

    // change the title of the entry
    public void setTitle(String title) {
        this.title = title;
    }

    // set completed status of the entry
    public void setCompleted(Boolean completed) {
        this.completed = completed == null ? false : completed;
    }

    // set order of the entry
    public void setOrder(Long order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(url, todo.url) &&
                Objects.equals(title, todo.title) && Objects.equals(completed, todo.completed) &&
                Objects.equals(order, todo.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, title, completed, order);
    }
}
