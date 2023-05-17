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
            expression = transformExpression(expression);
            Expression e = new ExpressionBuilder(expression).build();
            double result = e.evaluate();
            return String.valueOf(result);
        } catch (Exception e){
            return "Error : " + e.getMessage();
        }
    }
    private String transformExpression(String expression){
        // "^" -> "pow()"
        while (expression.contains("^")){
            int index = expression.indexOf("^");
            int start = expression.lastIndexOf(" ", index) + 1;
            int end = expression.indexOf(" ", index);
            if(end == -1) {
                end = expression.length();
            }
            String base = expression.substring(start, index); // 밑
            String exponent = expression.substring(index + 1, end); // 지수
            String powExpression = "pow(" + base + "," + exponent + ")";
            expression = expression.replace(base + " ^ " + exponent, powExpression);

            // "√" -> "sqrt()"
            expression = expression.replace("√", "sqrt");

            // "π" -> "pi"
            expression = expression.replace("π", "pi");

            // "random" -> 난수 생성
            expression = expression.replace("random", String.valueOf(random.nextDouble()));

        }
        return expression;
    }
}