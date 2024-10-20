package ru.mfti.atp.sem7.bonus.hw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BoundsValidationHandler implements InvocationHandler {
    Object delegate;

    public BoundsValidationHandler(Object delegate) {
        this.delegate = delegate;
    }

    // creates a proxied object
    public static <T> T validateBounds(T object) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                object.getClass().getInterfaces(),
                new BoundsValidationHandler(object));
    }

    @Override
    // proxy catches all method calls
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        /*
            write your code here
        */
        return method.invoke(delegate, args);
    }
}
