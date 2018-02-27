package io.github.yoshikawaa.spark.sample.core.exception;

import lombok.extern.slf4j.Slf4j;
import spark.ExceptionHandler;

@Slf4j
public class LoggingExceptionHandler {

    public static final ExceptionHandler<Exception> logging = (ex, req, res) -> {
        log.warn("unhandled exception.", ex);
    };
}
