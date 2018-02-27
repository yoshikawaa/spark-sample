package io.github.yoshikawaa.spark.sample.core.filter;

import lombok.extern.slf4j.Slf4j;
import spark.Filter;

@Slf4j
public class LoggingRequestFilter {

    public static Filter loggingBefore = (req, res) -> {
        log.info("Route:{} Started", req.pathInfo());
    };

    public static Filter loggingAfter = (req, res) -> {
        log.info("Route:{} Completed with Status:{}", req.pathInfo(), res.status());
    };
}
