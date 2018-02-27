package io.github.yoshikawaa.spark.sample.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.github.yoshikawaa.spark.sample.app.model.Todo;
import io.github.yoshikawaa.spark.sample.app.repository.TodoRepository;
import io.github.yoshikawaa.spark.sample.core.request.Flash;
import io.github.yoshikawaa.spark.sample.core.request.RequestUtils;
import io.github.yoshikawaa.spark.sample.core.validation.ValidationUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;

public class TodoController {

    private static final TodoRepository repository = new TodoRepository();

    public static final TemplateViewRoute findAll = (req, res) -> {
        return getAll(req, res, new HashMap<>());
    };

    public static final Route create = (req, res) -> {

        Todo todo = RequestUtils.map(req, Todo.class);
        Map<String, String> errors = ValidationUtils.validate(todo);
        if (!errors.isEmpty()) {
            res.status(401);
            Map<String, Object> model = new HashMap<>();
            model.put("errors", errors);
            return getAll(req, res, model);
        }

        repository.create(todo.getTitle());
        Flash.attribute(req, "message", "created.");
        res.redirect("/todos");
        return null;
    };

    public static final Route finish = (req, res) -> {
        repository.finish(req.queryParams("id"));
        Flash.attribute(req, "message", "finished.");
        res.redirect("/todos");
        return null;
    };

    public static final Route remove = (req, res) -> {
        repository.remove(req.queryParams("id"));
        Flash.attribute(req, "message", "removed.");
        res.redirect("/todos");
        return null;
    };

    private static ModelAndView getAll(Request req, Response res, Map<String, Object> model) {
        model.put("todos", repository.findAll());
        Optional.ofNullable(req.attribute("message")).ifPresent(message -> model.put("message", message));
        return new ModelAndView(model, "todo");
    }

}
