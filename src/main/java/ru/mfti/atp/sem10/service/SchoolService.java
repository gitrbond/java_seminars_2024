package ru.mfti.atp.sem10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfti.atp.sem10.model.Pupil;
import ru.mfti.atp.sem10.model.School;
import ru.mfti.atp.sem10.repository.PupilRepository;
import ru.mfti.atp.sem10.repository.SchoolRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;

    Map<String, School> schools;
    int schoolId = 0;

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
