package ru.mfti.atp.sem10.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.mfti.atp.sem10.model.Pupil;
import ru.mfti.atp.sem10.model.School;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository

public class PupilRepository {
    DataSource dataSource;

    SchoolRepository schoolRepository;

    public void save(Pupil pupil) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into pupil (id, name, school_id) values (?, ?, ?);");
            ps.setInt(1, pupil.getId());
            ps.setString(2, pupil.getName());
            ps.setInt(3, pupil.getSchool().getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pupil> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from pupil");
            ResultSet result = ps.executeQuery();

            List<Pupil> pupils = new ArrayList<>();
            while (result.next()) {
                Pupil pupil = new Pupil(result.getInt("id"),
                        result.getString("name"),
                        schoolRepository.getById(result.getInt("school_id")));
                pupils.add(pupil);
            }
            return pupils;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
