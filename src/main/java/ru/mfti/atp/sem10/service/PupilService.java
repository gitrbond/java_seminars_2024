package ru.mfti.atp.sem10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfti.atp.sem10.model.Pupil;
import ru.mfti.atp.sem10.model.School;
import ru.mfti.atp.sem10.repository.PupilRepository;
import ru.mfti.atp.sem10.repository.SchoolRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PupilService {
    @Autowired
    PupilRepository pupilRepository;

    @Autowired
    SchoolRepository schoolRepository;
    Map<Integer, Pupil> pupils;
    int pupilId;

    public PupilService(PupilRepository pupilRepository) {
        this.pupilRepository = pupilRepository;
        pupils = new HashMap<>();
        pupilRepository.getAll().forEach(p -> pupils.put(p.getId(), p));
    }

    public int create(String pupilName, String schoolName) {
//        School school = schoo
//        if ()
            schoolRepository.save(new School(1, ""));
        Pupil pupil = new Pupil(pupilId++, pupilName, new School(1, ""));
        pupilRepository.save(pupil);
        return pupilId;
    }

    public List<Pupil> pupils() {
        return pupilRepository.getAll();
    }
}
