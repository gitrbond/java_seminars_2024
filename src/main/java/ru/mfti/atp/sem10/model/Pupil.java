package ru.mfti.atp.sem10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
public class Pupil {
    int id;
    String name;
    School school;
}
