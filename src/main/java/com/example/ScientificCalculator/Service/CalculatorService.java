package com.example.ScientificCalculator.Service;

import java.util.Stack;
import java.util.function.BiFunction;

public class CalculatorService {
    public static double calculate(String expression) throws Exception {
        // Stack 자료구조를 사용하여, String 타입의 데이터를 담을 수 있는 스택인 tokens을 생성하는 역할을 합니다.
        Stack<String> tokens = new Stack<>();
        String[] splittedTokens = expression.split("\\s+");

        for (String token : splittedTokens) {
            tokens.push(token);
        }

        Stack<Double> values = new Stack<>();

        while (!tokens.isEmpty()) {
            String token = tokens.pop();

            if (isNumeric(token)) {
                values.push(Double.parseDouble(token));
            }
            /* performOperation : 이항연산, performUnaryOperation : 단항연산 / */
            else {
                switch (token) {
                    case "+":
                        performOperation(values, (a, b) -> a + b);
                        break;
                    case "-":
                        performOperation(values, (a, b) -> a - b);
                        break;
                    case "*":
                        performOperation(values, (a, b) -> a * b);
                        break;
                    case "/":
                        performOperation(values, (a, b) -> a / b);
                        break;
                    case "^":
                        performOperation(values, (a, b) -> Math.pow(a, b));
                        break;
                    case "sin":
                        performUnaryOperation(values, a -> Math.sin(a));
                        break;
                    case "cos":
                        performUnaryOperation(values, a -> Math.cos(a));
                        break;
                    case "tan":
                        performUnaryOperation(values, a -> Math.tan(a));
                        break;
                    case "abs":
                        performUnaryOperation(values, a -> Math.abs(a));
                        break;
                    case "round":
                        performUnaryOperation(values, a -> (double) Math.round(a));
                        break;
                    default:
                        throw new Exception("Invalid token: " + token);
                }
            }
        }

        if (values.size() != 1) {
            throw new Exception("Invalid expression");
        }

        return values.pop();
    }

    private static void performOperation(Stack<Double> values, BiFunction<Double, Double, Double> operation) {
        double b = values.pop();
        double a = values.pop();
        values.push(operation.apply(a, b));
    }

    private static void performUnaryOperation(Stack<Double> values, java.util.function.Function<Double, Double> operation) {
        double a = values.pop();
        values.push(operation.apply(a));
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
