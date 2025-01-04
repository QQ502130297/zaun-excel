package com.temu.zaun.excel.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.stream.Collectors;

public class TypeUtil {

    public static List<Type> getGenericParamType(Class source, Class target) {
        Type directTargetInterface = findDirectTargetInterface(source, target);
        if (directTargetInterface != null) {
            return findActualTypeArguments(directTargetInterface);
        } else {
            Map<String, Type> parentTypeArgumentMap = findParentTypeArgumentMap(source);
            Type targetInterface = findTargetInterfaceRecursive(source, target);
            List<Type> actualTypeArguments = findActualTypeArguments(targetInterface);
            return (List) actualTypeArguments.stream().map((type) -> {
                if (type instanceof TypeVariable) {
                    String name = ((TypeVariable) type).getName();
                    return (Type) parentTypeArgumentMap.get(name);
                } else {
                    return type;
                }
            }).collect(Collectors.toList());
        }
    }

    public static ParameterizedType findDirectTargetInterface(Class source, Class target) {
        Type[] genericInterfaces = source.getGenericInterfaces();
        Type[] var3 = genericInterfaces;
        int var4 = genericInterfaces.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Type genericInterface = var3[var5];
            if (genericInterface instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) genericInterface).getRawType();
                if (rawType.equals(target)) {
                    return (ParameterizedType) genericInterface;
                }
            }
        }

        return null;
    }

    public static ParameterizedType findTargetInterfaceRecursive(Class source, Class target) {
        while (source != null && !source.equals(Object.class)) {
            ParameterizedType directTargetInterface = findDirectTargetInterface(source, target);
            if (directTargetInterface != null) {
                return directTargetInterface;
            }

            source = source.getSuperclass();
        }

        return null;
    }

    public static List<Type> findActualTypeArguments(Type type) {
        List<Type> list = new ArrayList();
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            Type[] var3 = actualTypeArguments;
            int var4 = actualTypeArguments.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Type actualTypeArgument = var3[var5];
                if (actualTypeArgument instanceof ParameterizedType) {
                    list.add(findActualTypeArgumentIfRawTypeIfList(actualTypeArgument));
                } else {
                    list.add(actualTypeArgument);
                }
            }
        }

        return list;
    }

    public static Type findActualTypeArgumentIfRawTypeIfList(Type type) {
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
            return List.class.isAssignableFrom((Class) rawType) ? findActualTypeArgumentIfRawTypeIfList(actualTypeArgument) : rawType;
        } else {
            return type;
        }
    }

    public static Map<String, Type> findParentTypeArgumentMap(Class source) {
        HashMap map;
        for (map = new HashMap(8); source != null && !source.equals(Object.class); source = source.getSuperclass()) {
            Type genericSuperclass = source.getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                TypeVariable[] typeParameters = source.getSuperclass().getTypeParameters();

                for (int i = 0; i < typeParameters.length; ++i) {
                    Type actualTypeArgument = actualTypeArguments[i];
                    TypeVariable typeParameter = typeParameters[i];
                    Type actualTypeArgumentIfRawType = findActualTypeArgumentIfRawTypeIfList(actualTypeArgument);
                    if (!map.containsKey(typeParameter.getName()) && actualTypeArgumentIfRawType instanceof Class) {
                        map.put(typeParameter.getName(), actualTypeArgumentIfRawType);
                    }
                }
            }
        }
        return map;
    }
}