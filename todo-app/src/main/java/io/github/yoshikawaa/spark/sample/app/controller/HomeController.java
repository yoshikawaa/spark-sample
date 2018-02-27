package io.github.yoshikawaa.spark.sample.app.controller;

import java.util.Collections;

import spark.ModelAndView;
import spark.TemplateViewRoute;

public class HomeController {

    public static TemplateViewRoute home = (req, res) -> {
        return new ModelAndView(Collections.emptyMap(), "home");
    };
}
