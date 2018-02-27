package io.github.yoshikawaa.spark.sample.core.validation;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtils {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Map<String, String> validate(T model) {
        return asMap(validator.validate(model));
    }

    public static <T> Map<String, String> validate(T model, Class<?>... groups) {
        return asMap(validator.validate(model, groups));
    }

    public static <T> Map<String, String> asMap(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .collect(toMap(v -> ((ConstraintViolation<?>) v).getPropertyPath().iterator().next().getName(),
                        ConstraintViolation::getMessage));
    }
}
