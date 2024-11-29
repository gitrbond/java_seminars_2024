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

@AllArgsConstructor
@Repository
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

    public School getById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from school where id = ?;");
            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();
            if (!result.next()) {
                throw new IllegalArgumentException("school not found");
            }

            return new School(
                    result.getInt("id"),
                    result.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
