package ru.mfti.atp.sem10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfti.atp.sem10.model.Pupil;
import ru.mfti.atp.sem10.model.School;
import ru.mfti.atp.sem10.repository.PupilRepository;
import ru.mfti.atp.sem10.repository.SchoolRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolService {
    SchoolRepository schoolRepository;

    // school name to school
    Map<String, School> schools;
    int schoolId = 0;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
        schools = new HashMap<>();
        // read schools from db on start
        schoolRepository.getAll().forEach(s -> {
            schools.put(s.getName(), s);
            schoolId = Math.max(schoolId, s.getId());
        });
    }

    public School create(String schoolname) {
        if (schools.containsKey(schoolname)) {
            return schools.get(schoolname);
        }
        School school = new School(++schoolId, schoolname);
        schoolRepository.save(school);
        schools.put(schoolname, school);
        return school;
    }

    public List<School> all() {
        return new ArrayList<>(schools.values());
    }
}
