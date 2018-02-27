package io.github.yoshikawaa.spark.sample.core.view;

import static java.util.stream.Collectors.joining;

import java.util.Locale;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

@Slf4j
public class ModelLoggingTemplateEngine extends ThymeleafTemplateEngine {

    @Override
    public String render(ModelAndView modelAndView, Locale locale) {
        String view = super.render(modelAndView, locale);
        loggingModel(modelAndView, locale);
        return view;
    }

    private void loggingModel(ModelAndView modelAndView, Locale locale) {
        if (log.isDebugEnabled()) {
            Map<?, ?> modelMap = (Map<?, ?>) modelAndView.getModel();
            log.debug(modelMap.isEmpty() ? "model is empty."
                    : modelMap.entrySet()
                            .stream()
                            .map(e -> String.join("=", String.valueOf(e.getKey()), String.valueOf(e.getValue())))
                            .collect(joining(", ")));
        }
    }
}
