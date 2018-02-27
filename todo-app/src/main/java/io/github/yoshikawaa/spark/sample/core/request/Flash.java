package io.github.yoshikawaa.spark.sample.core.request;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Session;

public class Flash {

    private static final String FLASH = "flash";

    public static void attribute(Request request, String name, Object value) {
        flash(request).put(name, value);
    }
    
    public static Map<String, Object> flash(Request request) {
        Session session = request.session();
        Map<String, Object> flash = session.attribute(FLASH);
        if (session.attribute(FLASH) == null) {
            flash = new HashMap<>();
            session.attribute(FLASH, flash);
        }
        return flash;
    }
}
