package ru.mfti.atp.sem10;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationHook;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.mfti.atp.sem10.repository.PupilRepository;
import ru.mfti.atp.sem10.repository.SchoolRepository;
import ru.mfti.atp.sem10.service.PupilService;
import ru.mfti.atp.sem10.service.SchoolService;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws SQLException {
//        String jdbcURL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:pupil_school_db_create.sql';";
        // embedded database - stores data in a file
        String jdbcURL = "jdbc:h2:./plain_java_example/db_data/data";
        DataSource dataSource = createH2Pool(jdbcURL);
//        dataSource.getConnection().prepareStatement("create table school (\n" +
//                "  id INT not null primary key,\n" +
//                "  name VARCHAR(100)\n" +
//                ");\n" +
//                "\n" +
//                "create table pupil (\n" +
//                "  id INT not null primary key,\n" +
//                "  name VARCHAR(50) not null,\n" +
//                "  school_id INT not null,\n" +
//                "  foreign key (school_id) references school(id)\n" +
//                ");").execute();

//        SchoolRepository schoolRepository = new SchoolRepository(dataSource);
//        PupilRepository pupilRepository = new PupilRepository(dataSource, schoolRepository);
//
//        SchoolService schoolService = new SchoolService(schoolRepository);
//
//        PupilService pupilService = new PupilService(schoolService, pupilRepository, schoolRepository);
//
//        pupilService.create("Oleg", "L2SCH");
//        pupilService.create("Dima", "1535");

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        PupilService pupilService = context.getBean(PupilService.class);

        System.out.println("pupils=" + pupilService.all());
    }

    @Configuration
    public static class DBConfig {
        @Bean
        public DataSource dataSource() {
            String jdbcURL = "jdbc:h2:./plain_java_example/db_data/data";
            return createH2Pool(jdbcURL);
        }
    }

    private static DataSource createH2Pool(String jdbcURL) {
        return JdbcConnectionPool.create(jdbcURL, "", "");
    }
}
