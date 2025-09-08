package com.detech.gsrt.utils;

import java.lang.reflect.Field;
import java.util.Objects;

public class EntitieUtils {

    public static <T> void updateIfDifferent(T old, T change) throws IllegalAccessException {

        if (old != null && change != null && old.getClass().equals(change.getClass())) {
            for (Field field : old.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object oldvalue = field.get(old);
                Object changevalue = field.get(change);
                if (changevalue != null) {
                    if (!Objects.equals(oldvalue, changevalue)) {
                        field.set(old, changevalue);
                    }
                }
            }
        }
    }

}
