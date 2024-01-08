package com.baeldung.ls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.baeldung")
public class LsApp {

    public static void main(final String... args) {
        SpringApplication.run(LsApp.class, args);
    }
}
