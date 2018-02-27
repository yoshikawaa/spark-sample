package io.github.yoshikawaa.spark.sample;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Application {

    public static void main(String[] args) {
        // configure application.
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // configure before filters.

        // configure routes.
        get("/", (req, res) -> "hello world!");
        get("/exception", (req, res) -> { throw new Exception("hoge"); });

        // configure after filters.

        // configure exception handlers.
    }
}
