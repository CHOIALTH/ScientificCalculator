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
            /* performOp : 이항연산, performUnaryOp : 단항연산 / */
            else {
                switch (token) {
                    case "+":
                        performOp(values, (a, b) -> a + b);
                        break;
                    case "-":
                        performOp(values, (a, b) -> a - b);
                        break;
                    case "*":
                        performOp(values, (a, b) -> a * b);
                        break;
                    case "/":
                        performOp(values, (a, b) -> a / b);
                        break;
                    case "^":
                        performOp(values, (a, b) -> Math.pow(a, b));
                        break;
                    case "sin":
                        performUnaryOp(values, a -> Math.sin(a));
                        break;
                    case "cos":
                        performUnaryOp(values, a -> Math.cos(a));
                        break;
                    case "tan":
                        performUnaryOp(values, a -> Math.tan(a));
                        break;
                    case "abs":
                        performUnaryOp(values, a -> Math.abs(a));
                        break;
                    case "round":
                        performUnaryOp(values, a -> (double) Math.round(a));
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

    private static void performOp(Stack<Double> values, BiFunction<Double, Double, Double> operation) {
        double b = values.pop();
        double a = values.pop();
        values.push(operation.apply(a, b));
    }

    private static void performUnaryOp(Stack<Double> values, java.util.function.Function<Double, Double> operation) {
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
