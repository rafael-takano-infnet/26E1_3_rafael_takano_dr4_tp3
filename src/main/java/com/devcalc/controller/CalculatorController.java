package com.devcalc.controller;

import com.devcalc.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for calculator operations.
 * Provides HTTP endpoints for basic mathematical operations.
 *
 * @author Rafael Takano
 * @version 1.0.0
 */
@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * Addition endpoint.
     *
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    @GetMapping("/add")
    public ResponseEntity<Double> add(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculatorService.add(a, b);
        return ResponseEntity.ok(result);
    }

    /**
     * Subtraction endpoint.
     *
     * @param a first number
     * @param b second number
     * @return difference of a and b
     */
    @GetMapping("/subtract")
    public ResponseEntity<Double> subtract(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculatorService.subtract(a, b);
        return ResponseEntity.ok(result);
    }

    /**
     * Multiplication endpoint.
     *
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    @GetMapping("/multiply")
    public ResponseEntity<Double> multiply(
            @RequestParam double a,
            @RequestParam double b) {
        double result = calculatorService.multiply(a, b);
        return ResponseEntity.ok(result);
    }

    /**
     * Division endpoint.
     *
     * @param a dividend
     * @param b divisor
     * @return quotient of a and b
     */
    @GetMapping("/divide")
    public ResponseEntity<?> divide(
            @RequestParam double a,
            @RequestParam double b) {
        try {
            double result = calculatorService.divide(a, b);
            return ResponseEntity.ok(result);
        } catch (ArithmeticException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Square root endpoint.
     *
     * @param x number to evaluate
     * @return square root of x
     */
    @GetMapping("/sqrt")
    public ResponseEntity<?> sqrt(@RequestParam double x) {
        try {
            double result = calculatorService.sqrt(x);
            return ResponseEntity.ok(result);
        } catch (ArithmeticException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }
}
