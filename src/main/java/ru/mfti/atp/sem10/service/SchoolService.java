package ru.mfti.atp.sem10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mfti.atp.sem10.model.School;
import ru.mfti.atp.sem10.repository.SchoolRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SchoolService {
    SchoolRepository schoolRepository;

    Map<String, School> schools;
    int schoolId = 0;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
        schools = new HashMap<>();
    }

    public School create(String schoolname) {
        if (schools.containsKey(schoolname)) {
            return schools.get(schoolname);
        }
        School school = new School(schoolId++, schoolname);
        schoolRepository.save(school);
        schools.put(schoolname, school);
        return school;
    }

    public List<School> all() {
        return new ArrayList<>(schools.values());
    }
}
