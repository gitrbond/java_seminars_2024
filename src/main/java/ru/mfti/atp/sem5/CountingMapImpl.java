package ru.mfti.atp.sem5;

import java.util.HashMap;
import java.util.Map;

public class CountingMapImpl<T> implements CountingMap<T> {
    Map<T, Integer> counter = new HashMap<>();

    @Override
    public void add(T element) {
        counter.merge(element, 1, Integer::sum);
    }

    @Override
    public int get(T o) {
        return counter.getOrDefault(o, 0);
    }

    @Override
    public void copyTo(CountingMap<? super T> destination) {
        for (T key : counter.keySet()) {
            int n = counter.get(key);
            for (int i = 0; i < n; i++) {
                destination.add(key);
            }
        }
    }

    @Override
    public void copyFrom(CountingMap<? extends T> source) {
        source.toMap().forEach((key, value) -> {
            int n = value;
            for (int i = 0; i < n; i++) {
                this.add(key);
            }
        });
    }

    @Override
    public Map<T, Integer> toMap() {
        return counter;
    }
}
