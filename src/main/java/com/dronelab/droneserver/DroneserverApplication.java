package com.dronelab.droneserver;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DroneserverApplication {


    public static void main(String[] args) {
        var logger = LoggerFactory.getLogger(DroneserverApplication.class);
        SpringApplication.run(DroneserverApplication.class, args);
        logger.info("Application started");
    }

}
