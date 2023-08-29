package com.demo.simplecalculator.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SimpleCalculator.class})
public class SimpleCalculatorTest {
    
    @Autowired
    SimpleCalculator simpleCalculator;
    
    @Test
    void executeAddEquation() {
        int number1 = 10;
        int number2 = 20;
        
        double result = this.simpleCalculator.add(number1, number2);
        
        assertEquals(result, 30);
    }
    
    @Test
    void executeSubtractEquation() {
        int number1 = 10;
        int number2 = 20;
        
        double result = this.simpleCalculator.subtract(number1, number2);
        
        assertEquals(result, -10);
    }
    
    @Test
    void executeMultiplyEquation() {
        int number1 = 10;
        int number2 = 20;
        
        double result = this.simpleCalculator.multiply(number1, number2);
        
        assertEquals(result, 200);
    }
    
    @Test
    void executeDivideEquation() {
        int number1 = 10;
        int number2 = 20;
        
        double result = this.simpleCalculator.divide(number1, number2);
        
        assertEquals(result, 0.5);
    }
    
    @Test
    void executeDivideEquationWithZeroDivisorForPositiveInfinite() {
        int number1 = 10;
        int number2 = 0;
        
        double result = this.simpleCalculator.divide(number1, number2);
        
        assertEquals(result, Double.POSITIVE_INFINITY);
    }
    
    @Test
    void executeDivideEquationWithZeroDivisorForNegativeInfinite() {
        int number1 = -10;
        int number2 = 0;
        
        double result = this.simpleCalculator.divide(number1, number2);
        
        assertEquals(result, Double.NEGATIVE_INFINITY);
    }
    
    @Test
    void executeDivideEquationWithZeroDivisorForNotANumber() {
        int number1 = 0;
        int number2 = 0;
        
        double result = this.simpleCalculator.divide(number1, number2);
        
        assertEquals(result, Double.NaN);
    }
    
}
