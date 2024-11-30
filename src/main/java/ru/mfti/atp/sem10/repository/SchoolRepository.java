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
import java.util.Optional;

@AllArgsConstructor
public class SchoolRepository {
    DataSource dataSource;

    public void save(School school) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into school (id, name) values (?, ?);");
            ps.setInt(1, school.getId());
            ps.setString(2, school.getName());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<School> getByName(String name) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from school where name = ?;");
            ps.setString(1, name);

            ResultSet result = ps.executeQuery();
            if (!result.next()) {
                return Optional.empty();
            }

            return Optional.of(new School(
                    result.getInt("id"),
                    result.getString("name")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<School> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from school");
            ResultSet result = ps.executeQuery();

            List<School> schools = new ArrayList<>();
            while (result.next()) {
                School school = new School(result.getInt("id"),
                        result.getString("name"));
                schools.add(school);
            }
            return schools;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
