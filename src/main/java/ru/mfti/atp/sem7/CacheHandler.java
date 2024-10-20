package ru.mfti.atp.sem7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

// this is called dynamic proxy
public class CacheHandler implements InvocationHandler {
    Map<List<Object>, Object> cache = new HashMap<>();

    Object delegate;

    public CacheHandler(Object delegate) {
        this.delegate = delegate;
    }

    // creates a proxied object
    public static <T> T cache(T object) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                object.getClass().getInterfaces(),
                new CacheHandler(object));
    }

    @Override
    // proxy catches all method calls
    public Object invoke(Object proxy, Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(List.of(args));

        return cache.computeIfAbsent(key, k -> {
            try {
                return method.invoke(delegate, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
