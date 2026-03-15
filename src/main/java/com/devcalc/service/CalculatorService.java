package com.devcalc.service;

import org.springframework.stereotype.Service;

/**
 * Service class for basic mathematical operations.
 * Provides methods for addition, subtraction, multiplication, and division.
 */
@Service
public class CalculatorService {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    public double sqrt(double x) {
        if (x < 0) {
            throw new ArithmeticException("Cannot calculate square root of a negative number");
        }
        return Math.sqrt(x);
    }
}
