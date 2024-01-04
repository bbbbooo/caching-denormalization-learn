package com.exception.exceptionstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExceptionStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionStudyApplication.class, args);
    }

}
