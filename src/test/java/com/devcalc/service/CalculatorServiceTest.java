package com.devcalc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CalculatorService.
 * Tests all mathematical operations including error handling.
 */
class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testAdd() {
        // Test basic addition
        assertEquals(15.0, calculatorService.add(10, 5));
        assertEquals(0.0, calculatorService.add(-5, 5));
        assertEquals(-10.0, calculatorService.add(-5, -5));
        assertEquals(7.5, calculatorService.add(5.5, 2.0));
    }

    @Test
    void testSubtract() {
        // Test basic subtraction
        assertEquals(5.0, calculatorService.subtract(10, 5));
        assertEquals(-10.0, calculatorService.subtract(-5, 5));
        assertEquals(0.0, calculatorService.subtract(5, 5));
        assertEquals(3.5, calculatorService.subtract(5.5, 2.0));
    }

    @Test
    void testMultiply() {
        // Test basic multiplication
        assertEquals(50.0, calculatorService.multiply(10, 5));
        assertEquals(-25.0, calculatorService.multiply(-5, 5));
        assertEquals(25.0, calculatorService.multiply(-5, -5));
        assertEquals(0.0, calculatorService.multiply(5, 0));
        assertEquals(11.0, calculatorService.multiply(5.5, 2.0));
    }

    @Test
    void testDivide() {
        // Test basic division
        assertEquals(2.0, calculatorService.divide(10, 5));
        assertEquals(-1.0, calculatorService.divide(-5, 5));
        assertEquals(1.0, calculatorService.divide(-5, -5));
        assertEquals(2.75, calculatorService.divide(5.5, 2.0));
    }

    @Test
    void testDivideByZero() {
        // Test division by zero throws ArithmeticException
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculatorService.divide(10, 0)
        );
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    void testSqrt() {
        assertEquals(4.0, calculatorService.sqrt(16));
        assertEquals(0.0, calculatorService.sqrt(0));
        assertEquals(1.5, calculatorService.sqrt(2.25));
    }

    @Test
    void testSqrtNegativeNumber() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculatorService.sqrt(-1)
        );
        assertEquals("Cannot calculate square root of a negative number", exception.getMessage());
    }
}
