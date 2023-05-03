package com.cart.onebox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigurationApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationApplication.class);

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/v1");
        SpringApplication.run(ConfigurationApplication.class, args);
        LOGGER.info(" ---- ConfigurationApplication started successfully. ---- ");
    }

    /*
    @PostConstruct
    private void initDb() {
        LOGGER.info(" ---- Insert data H2 ---- ");
        String sqlStatements[] = {
                "drop table cart if exists",
                "drop table product if exists",
        };
        Arrays.asList(sqlStatements).stream().forEach(sql -> {
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        });

        LOGGER.info(" ---- Configured data H2 ---- ");
    }
    */

}
