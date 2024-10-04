package ru.mfti.atp.sem5;

import java.util.Map;

public interface CountingMap<T> {
    void add(T o);

    int get(T o);

    void copyTo(CountingMap<? super T> destination);

    void copyFrom(CountingMap<? extends T> source);

    Map<T, Integer> toMap();
}
