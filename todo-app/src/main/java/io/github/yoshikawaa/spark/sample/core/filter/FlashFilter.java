package io.github.yoshikawaa.spark.sample.core.filter;

import java.util.Map;

import io.github.yoshikawaa.spark.sample.core.request.Flash;
import spark.Filter;

public class FlashFilter {

    public static Filter takeover = (req, res) -> {
        Map<String, Object> flash = Flash.flash(req);
        flash.entrySet().forEach(f -> req.attribute(f.getKey(), f.getValue()));
        flash.clear();
    };

}
