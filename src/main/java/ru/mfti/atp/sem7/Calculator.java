package ru.mfti.atp.sem7;

public interface Calculator {
    int add(@Bounds(min=0, max=10) int a,
            @Bounds(min=0, max=5) int b);
}
