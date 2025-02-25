package ru.mfti.atp.sem10.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mfti.atp.sem10.model.School;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class SchoolRepository {
    @Autowired
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

    public School getById(int id) {
        return getAll().stream().filter(school -> school.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("school not found"));
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
