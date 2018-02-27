package io.github.yoshikawaa.spark.sample.core.view;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonResponseTransformer implements ResponseTransformer {

    private static final Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }

}
