package ru.mfti.atp.sem7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorDecorator implements Calculator {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    Calculator delegate;

    public CalculatorDecorator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public int add(int a, int b) {
        List<Integer> key = List.of(a, b);
        return cache.computeIfAbsent(key, (args) -> delegate.add(args.get(0) , args.get(1)));
    }
}
