package com.temu.zaun.excel.util;

import com.temu.zaun.excel.model.ColumnHeaders;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassUtil {
    public static List<Object> newInstance(List<Map<String, String>> data, List<ColumnHeaders> columnHeaders, Class<?> clazz, Map<String, Field> fieldMap) {
        List<Object> list = new ArrayList<>();
        for (Map<String, String> datum : data) {
            Object o = newInstance(datum, columnHeaders, clazz, fieldMap);
            if(o != null){
                list.add(o);
            }
        }
        return list;
    }

    public static Object newInstance(Map<String, String> data, List<ColumnHeaders> columnHeaders, Class<?> clazz, Map<String, Field> fieldMap) {
        try {
            Object o = clazz.newInstance();
            for (ColumnHeaders columnHeader : columnHeaders) {
                Field field = fieldMap.get(columnHeader.getFieldName());
                FieldUtil.setFieldValue(o, field, data.get(columnHeader.getHeadName()));
            }
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
