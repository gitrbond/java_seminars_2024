package ru.mfti.atp.sem7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Какие элементы кода могут быть аннотированы
@Target({ElementType.FIELD, ElementType.PARAMETER})
//На каком уровне сохраняются эти аннотации
@Retention(RetentionPolicy.RUNTIME)
public @interface Bounds {
    int min();

    int max();
}

