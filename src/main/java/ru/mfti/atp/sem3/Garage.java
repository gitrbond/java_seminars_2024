package ru.mfti.atp.sem3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.function.Predicate;

@AllArgsConstructor
@RequiredArgsConstructor
public class Garage {
    @Getter
    private List<Car> cars = new ArrayList<>();
    Predicate<Car> predicate = car -> !car.getColor().equals("black");

    Deque<Action> undoHistory = new ArrayDeque<>();

    void add(Car car) throws GarageException {
        if (predicate.test(car)) {
            undoHistory.push(garage -> garage.cars.remove(car));
            cars.add(car);
        } else {
            throw new GarageException("access denied!");
//            System.out.println("cant add car" + car);
        }
    }

    void undo() {
        undoHistory.pop().act(this);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "cars=" + cars +
                '}';
    }
}
