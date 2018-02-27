package io.github.yoshikawaa.spark.sample.app.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import io.github.yoshikawaa.spark.sample.app.model.Todo;
import spark.utils.Assert;

public class TodoRepository {

    private static final List<Todo> todos = new ArrayList<>();
    private static final AtomicInteger ids = new AtomicInteger(1);

    public List<Todo> findAll() {
        return todos;
    }

    public Optional<Todo> findOne(String id) {
        Assert.hasLength(id, "id is empty.");
        return todos.stream().filter(todo -> Objects.equals(todo.getId(), id)).findFirst();
    }

    public void create(String title) {
        todos.add(new Todo(String.valueOf(ids.getAndIncrement()), title, LocalDateTime.now(), false));
    }

    public void finish(String id) {
        findOne(id).ifPresent(todo -> todo.setFinished(true));
    }

    public void remove(String id) {
        findOne(id).ifPresent(todo -> todos.remove(todo));
    }

}
