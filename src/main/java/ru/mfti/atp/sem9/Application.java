package ru.mfti.atp.sem9;

import com.zaxxer.hikari.HikariDataSource;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.sql.*;

public class Application {
    // in-memory database - stored in memory, cleared at restart
    // executes talk_conference_speaker_db_create.sql at start
    static String jdbcURL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:talk_conference_speaker_db_create.sql';";
    // embedded database - stores data in a file
//    String jdbcURL = "jdbc:h2:./plain_java_example/db_data/data";
    static DataSource dataSource = createH2Pool(jdbcURL);

    public static void main(String[] args) throws SQLException {
        sqlInjectExample();

        preparedStatementExample();
    }

    static void sqlInjectExample() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()
        ) {
            String query1 = "create table students(ID int primary key, name varchar(50))";
            String query2 = "insert into students (ID, name) values (1, 'Oleg')";

            statement.execute(query1);
            statement.execute(query2);

//            String name = "Oleg";
            String name = "Oleg'; drop table students;--";
            String injectedQuery = "select * from students where name = '" + name + "'";
            statement.execute(injectedQuery);

            // print names
            try {
                String query3 = "select * from students";
                ResultSet result = statement.executeQuery(query3);
                while (result.next()) {
                    System.out.println(result.getString("name"));
                }
            } catch (SQLException e) {
                System.out.println("sql injection success! exception = " + e);
            }
        }
    }

    static void preparedStatementExample() throws SQLException {
        try (Connection connection = dataSource.getConnection();
        ) {
            PreparedStatement ps1 = connection.prepareStatement(
                    "insert into speaker (ID, name) values (1, 'Oleg');"
                            + "insert into speaker (ID, name) values (2, 'Vanya');"
                            + "insert into speaker (ID, name) values (3, 'Zhenya');"
            );
            ps1.execute();

            PreparedStatement ps2 = connection.prepareStatement("select * from speaker where name = ?");
            PreparedStatement ps3 = connection.prepareStatement("select * from speaker where id = ?");
            ps2.setString(1, "Vanya");
            ps3.setInt(1, 3);

            // print names
            PreparedStatement ps4 = connection.prepareStatement("select * from speaker");
            ResultSet result = ps4.executeQuery();
            while (result.next()) {
                System.out.println(result.getString("name"));
            }
        }
    }

    // returns H2-specific connection pool
    private static DataSource createH2Pool(String jdbcURL) {
        return JdbcConnectionPool.create(jdbcURL, "", "");
    }

    // returns Hikari connection pool
    private static DataSource createHikariPool(String jdbcURL) {
        HikariDataSource hds = new HikariDataSource();
        hds.setJdbcUrl(jdbcURL);
        return hds;
    }
}
