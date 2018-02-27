package io.github.yoshikawaa.spark.sample.core.request;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.eclipse.jetty.util.MultiMap;

import spark.Request;

public class RequestUtils {

    private static final Mapper mapper = DozerBeanMapperBuilder.create().build();

    public static <T> T map(Request request, Class<T> modelClass) {
        MultiMap<String> queryMap = new MultiMap<>();
        request.queryMap().toMap().entrySet().forEach(query -> {
            queryMap.addValues(query.getKey(), query.getValue());
        });
        return mapper.map(queryMap, modelClass);
    }

}
