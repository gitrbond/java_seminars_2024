package ru.mfti.atp.sem6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Какие элементы кода могут быть аннотированы
@Target(ElementType.FIELD)
//На каком уровне сохраняются эти аннотации
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
}
