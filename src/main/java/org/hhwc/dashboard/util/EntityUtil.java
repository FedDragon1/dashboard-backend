package org.hhwc.dashboard.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;


public class EntityUtil {
    public static boolean allNull(Object target) {
        return getFieldStream(target).allMatch(Objects::isNull);
    }

    public static boolean anyNull(Object target) {
        return getFieldStream(target).anyMatch(Objects::isNull);
    }

    private static Object getFieldValue(Field field, Object target) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Object> getFieldStream(Object target) {
        return Arrays.stream(target.getClass().getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .map(f -> getFieldValue(f, target));
    }
}
