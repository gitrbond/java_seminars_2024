package ru.mfti.atp.sem7;

public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        // some really slow-working method
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }
}
