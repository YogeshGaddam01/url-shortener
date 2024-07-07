package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
        log.trace("Trace level logging is present");
        log.debug("Debug level logging is present");
        log.info("Info level logging is present");
        log.warn("Warn level logging is present");
        log.error("Error level logging is present");
    }
}
