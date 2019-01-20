package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData (EmployeeRepo repo) {
        return args -> {

            repo.save(new Employee(
                    "John Snow", new Date(), new Date()));

            log.info("Employee found with findAll():");
            log.info("------------------------------");
            for (Employee employee : repo.findAll()) {
                log.info(employee.toString());
            }
            log.info("");

            Employee employee = repo.findById(1).get();
            log.info("Employee found with findOne(1):");
            log.info("--------------------------------");
            log.info(employee.toString());
            log.info("");

            log.info("Employee found with findByNameStartsWithIgnoreCase('John Snow'):");
            log.info("--------------------------------------------");
            for (Employee john : repo
                    .findByNameStartsWithIgnoreCase("John Snow")) {
                log.info(john.toString());
            }
            log.info("");
        };
    }
}
