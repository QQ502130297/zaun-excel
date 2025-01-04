package com.temu.zaun.excel.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldUtil {
    public static void setFieldValue(Object obj, Field field, String value) throws IllegalAccessException {
        if(field == null){
            return;
        }
        field.setAccessible(true);
        Class<?> fieldType = field.getType();
        if (fieldType == int.class || fieldType == Integer.class) {
            field.set(obj, Integer.parseInt(value));
        } else if (fieldType == long.class || fieldType == Long.class) {
            field.set(obj, Long.parseLong(value));
        } else if (fieldType == float.class || fieldType == Float.class) {
            field.set(obj, Float.parseFloat(value));
        } else if (fieldType == double.class || fieldType == Double.class) {
            field.set(obj, Double.parseDouble(value));
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            field.set(obj, Boolean.parseBoolean(value));
        } else if (fieldType == byte.class || fieldType == Byte.class) {
            field.set(obj, Byte.parseByte(value));
        } else if (fieldType == short.class || fieldType == Short.class) {
            field.set(obj, Short.parseShort(value));
        } else if (fieldType == char.class || fieldType == Character.class) {
            if (value.length() == 1) {
                field.set(obj, value.charAt(0));
            } else {
                throw new IllegalArgumentException("String length must be 1 for char type.");
            }
        } else if (fieldType == String.class) {
            field.set(obj, value);
        }
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        getAllFieldsRecursive(clazz, fields);
        return fields;
    }

    private static void getAllFieldsRecursive(Class<?> clazz, List<Field> fields) {
        if (clazz == null) {
            return;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        fields.addAll(Arrays.asList(declaredFields));
        getAllFieldsRecursive(clazz.getSuperclass(), fields);
    }

}
