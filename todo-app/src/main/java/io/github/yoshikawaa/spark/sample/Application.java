package io.github.yoshikawaa.spark.sample;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

import io.github.yoshikawaa.spark.sample.app.controller.HomeController;
import io.github.yoshikawaa.spark.sample.app.controller.TodoController;
import io.github.yoshikawaa.spark.sample.app.controller.TodoRestController;
import io.github.yoshikawaa.spark.sample.core.exception.LoggingExceptionHandler;
import io.github.yoshikawaa.spark.sample.core.filter.FlashFilter;
import io.github.yoshikawaa.spark.sample.core.filter.LoggingRequestFilter;
import io.github.yoshikawaa.spark.sample.core.view.JsonResponseTransformer;
import io.github.yoshikawaa.spark.sample.core.view.ModelLoggingTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import spark.ResponseTransformer;
import spark.TemplateEngine;

@Slf4j
public class Application {

    private static final TemplateEngine templateEngine = new ModelLoggingTemplateEngine();
    private static final ResponseTransformer transformer = new JsonResponseTransformer();

    public static void main(String[] args) {
        // configure application.
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // before filters.
        before("*", FlashFilter.takeover);
        before("*", LoggingRequestFilter.loggingBefore);
        // before(new BasicAuthenticationFilter("*", new AuthenticationDetails("tom", "jerry")));

        // configure view routes.
        get("/", HomeController.home, templateEngine);
        path("/todos", () -> {
            get("", TodoController.findAll, templateEngine);
            post("/create", TodoController.create);
            post("/finish", TodoController.finish);
            post("/remove", TodoController.remove);
        });

        // configure api routes.
        path("/api", () -> {
            path("/todos", () -> {
                get("", TodoRestController.findAll, transformer);
                get("/:id", TodoRestController.findAll, transformer);
                post("", TodoRestController.create, transformer);
                put("/:id", TodoRestController.finish, transformer);
                delete("/:id", TodoRestController.remove, transformer);
            });
            after("/*", (req, res) -> res.type("application/json"));
            after("/*", (req, res) -> log.info(res.body()));
        });

        // after filters.
        after("*", LoggingRequestFilter.loggingAfter);

        // exception handlers.
        exception(Exception.class, LoggingExceptionHandler.logging);
    }
}
