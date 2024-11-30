package ru.mfti.atp.sem10.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfti.atp.sem10.model.Pupil;
import ru.mfti.atp.sem10.model.School;
import ru.mfti.atp.sem10.repository.PupilRepository;
import ru.mfti.atp.sem10.repository.SchoolRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PupilService {
    PupilRepository pupilRepository;
    SchoolRepository schoolRepository;

    // pupil id to pupil
    Map<Integer, Pupil> pupils;
    int pupilId = 0;

    public PupilService(PupilRepository pupilRepository, SchoolRepository schoolRepository) {
        this.pupilRepository = pupilRepository;
        this.schoolRepository = schoolRepository;
        pupils = new HashMap<>();
        // read pupils from db on start
        pupilRepository.getAll().forEach(p -> {
            pupils.put(p.getId(), p);
            pupilId = Math.max(pupilId, p.getId());
        });
    }

    public int create(String pupilName, String schoolName) {
//        School school = schoo
//        if ()
        schoolRepository.save(new School(1, ""));
        Pupil pupil = new Pupil(++pupilId, pupilName, new School(1, ""));
        pupilRepository.save(pupil);
        return pupilId;
    }

    public List<Pupil> pupils() {
        return pupilRepository.getAll();
    }
}
