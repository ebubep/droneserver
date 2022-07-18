package com.dronelab.droneserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DroneserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneserverApplication.class, args);
                System.out.println("""
                                   here 
                        y'all
                                   """);
	}

}
