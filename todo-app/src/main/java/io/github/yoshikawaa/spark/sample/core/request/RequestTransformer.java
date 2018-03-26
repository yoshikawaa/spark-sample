package io.github.yoshikawaa.spark.sample.core.request;

import java.util.HashMap;
import java.util.Map;

import org.atteo.evo.inflector.English;
import org.modelmapper.ModelMapper;

import spark.Request;

public class RequestTransformer {

    private static final ModelMapper mapper = new ModelMapper();

    public static <T> T map(Request request, Class<T> modelClass) {
        Map<String, Object> queryMap = new HashMap<>();
        request.queryMap().toMap().entrySet().forEach(query -> {
            queryMap.put(query.getKey(), query.getValue()[0]);
            queryMap.put(English.plural(query.getKey()), query.getValue());
        });
        return mapper.map(queryMap, modelClass);
    }

}
