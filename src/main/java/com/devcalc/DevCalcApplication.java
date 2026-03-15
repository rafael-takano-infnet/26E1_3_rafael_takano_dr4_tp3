package com.devcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for DevCalc API.
 * Spring Boot application that provides REST endpoints for mathematical operations.
 *
 * @author Rafael Takano
 * @version 1.0.0
 */
@SpringBootApplication
public class DevCalcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevCalcApplication.class, args);
    }
}
