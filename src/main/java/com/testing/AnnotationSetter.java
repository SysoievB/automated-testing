package com.testing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class AnnotationSetter {

    public Object setAutowiredToConstructor(Object bean) throws Exception {
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Parameter[] constructorParameters = clazz.getDeclaredConstructor().getParameters();
        List<Object> constructorArgs = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(MyAutowired.class)) {
                field.setAccessible(true);
                for (Parameter parameter : constructorParameters) {
                    if (parameter.getType().isAssignableFrom(field.getType())) {
                        constructorArgs.add(field.get(bean));
                        break;
                    }
                }
            }
        }

        Constructor<?> dynamicConstructor = clazz.getDeclaredConstructor(getParameterTypes(constructorArgs));
        return dynamicConstructor.newInstance(constructorArgs.toArray());
    }

    private Class<?>[] getParameterTypes(List<Object> constructorArgs) {
        Class<?>[] parameterTypes = new Class<?>[constructorArgs.size()];
        for (int i = 0; i < constructorArgs.size(); i++) {
            parameterTypes[i] = constructorArgs.get(i).getClass();
        }
        return parameterTypes;
    }
}
