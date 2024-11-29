package ru.mfti.atp.sem10;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mfti.atp.sem10.service.PupilService;
import ru.mfti.atp.sem10.service.SchoolService;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        String jdbcURL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:pupil_school_db_create.sql';";
//        // embedded database - stores data in a file
////    String jdbcURL = "jdbc:h2:./plain_java_example/db_data/data";
//        DataSource dataSource = createH2Pool(jdbcURL);

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        System.out.println("ololo");

        SchoolService schoolService = context.getBean(SchoolService.class);
        PupilService pupilService = context.getBean(PupilService.class);

        pupilService.create("ol", "1");
        pupilService.create("di", "2");
        System.out.println(pupilService.pupils());

        context.close();
    }

    @Configuration
    public static class DBConfig {
        String jdbcURL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:pupil_school_db_create.sql';";

        @Bean
        public DataSource dataSource() {
            return createH2Pool(jdbcURL);
        }

        private static DataSource createH2Pool(String jdbcURL) {
            System.out.println("pool===");
            return JdbcConnectionPool.create(jdbcURL, "", "");
        }
    }
}
