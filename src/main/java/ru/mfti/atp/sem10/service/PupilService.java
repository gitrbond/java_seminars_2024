package ru.mfti.atp.sem10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mfti.atp.sem10.model.Pupil;
import ru.mfti.atp.sem10.model.School;
import ru.mfti.atp.sem10.repository.PupilRepository;
import ru.mfti.atp.sem10.repository.SchoolRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PupilService {
    SchoolService schoolService;

    PupilRepository pupilRepository;

    SchoolRepository schoolRepository;

    // id to pupil
    Map<Integer, Pupil> pupils;
    AtomicInteger pupilId;

    @Autowired
    public PupilService(SchoolService schoolService, PupilRepository pupilRepository, SchoolRepository schoolRepository) {
        this.schoolService = schoolService;
        this.pupilRepository = pupilRepository;
        this.schoolRepository = schoolRepository;
        pupils = new HashMap<>();
        pupilId = new AtomicInteger(0);
        pupilRepository.getAll().forEach(p -> {
            pupils.put(p.getId(), p);
            pupilId.accumulateAndGet(p.getId(), Math::max);
        });
    }

    public int create(String pupilName, String schoolName) {
        Optional<School> school = schoolRepository.getAll().stream().filter(s -> s.getName().equals(schoolName)).findAny();
        if (school.isEmpty()) {
            school = Optional.of(schoolService.create(schoolName));
        }
        Pupil pupil = new Pupil(pupilId.incrementAndGet(), pupilName, school.get());
        pupilRepository.save(pupil);
        return pupilId.get();
    }

    public List<Pupil> all() {
        return pupilRepository.getAll();
    }
}
