package io.github.yoshikawaa.spark.sample.app.controller;

import java.util.Collections;
import java.util.Map;

import com.google.gson.Gson;

import io.github.yoshikawaa.spark.sample.app.model.Todo;
import io.github.yoshikawaa.spark.sample.app.repository.TodoRepository;
import io.github.yoshikawaa.spark.sample.core.validation.ValidationUtils;
import spark.Route;

public class TodoRestController {

    private static final TodoRepository repository = new TodoRepository();
    private static final Gson gson = new Gson();

    public static final Route findAll = (req, res) -> {
        return repository.findAll();
    };

    public static final Route findOne = (req, res) -> {
        return repository.findOne(req.params("id"));
    };

    public static final Route create = (req, res) -> {

        Todo todo = gson.fromJson(req.body(), Todo.class);
        Map<String, String> errors = ValidationUtils.validate(todo);
        if (!errors.isEmpty()) {
            res.status(401);
            return errors;
        }

        repository.create(todo.getTitle());
        return Collections.singletonMap("message", "created.");
    };

    public static final Route finish = (req, res) -> {
        repository.finish(req.params("id"));
        return Collections.singletonMap("message", "finished.");
    };

    public static final Route remove = (req, res) -> {
        repository.remove(req.params("id"));
        return Collections.singletonMap("message", "removed.");
    };
}
