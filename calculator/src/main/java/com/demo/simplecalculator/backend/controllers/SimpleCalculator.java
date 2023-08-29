package com.demo.simplecalculator.backend.controllers;

import com.demo.simplecalculator.backend.models.Equation;
import com.demo.simplecalculator.backend.models.Equation.Sign;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleCalculator {
    
    public double add(int number1, int number2) {
        return Double.valueOf(number1) + Double.valueOf(number2);
    }
    
    public double subtract(int number1, int number2) {
        return Double.valueOf(number1) - Double.valueOf(number2);
    }
    
    public double multiply(int number1, int number2) {
        return Double.valueOf(number1) * Double.valueOf(number2);
    }
    
    public double divide(int number1, int number2) {
        return Double.valueOf(number1) / Double.valueOf(number2);
    }
    
}