package com.example.ScientificCalculator.Service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CalculatorService {
    private final Random random = new Random();
    public String calculate(String expression){
        try {
            expression = trans
        } catch (Exception e){

        }
    }
    private String transformExpressin(String expression){
        // "^" -> "pow()"
        while (expression.contains("^")){
            int index = expression.indexOf("^");
            int start = expression.lastIndexOf(" ", index) + 1;
            int end = expression.indexOf(" ", index);
            if(end == -1) {
                end = expression.length();
            }
            String base = expression.substring(start, index);
            String exponent = expression.substring(index + 1, end);
            String
        }
    }
}