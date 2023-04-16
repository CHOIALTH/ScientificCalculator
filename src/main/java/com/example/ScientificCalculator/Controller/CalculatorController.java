package com.example.ScientificCalculator.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
public class CalculatorController {
    // 계산기 메인 호출
    @GetMapping("/")
    public String calculatorMain(){
            return "calculator";
        }

    @PostMapping("/calculate")
    public String calculate(@RequestParam String display, @RequestParam String operator, Model model) {
        double result = 0;
        String[] operands = display.split("\\" + operator);

        if (operands.length == 2) {
            double operand1 = Double.parseDouble(operands[0]);
            double operand2 = Double.parseDouble(operands[1]);

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
                case "^":
                    result = Math.pow(operand1, operand2);
                    break;
            }
        }

        model.addAttribute("result", formatResult(result));

        return "calculator";
    }
    private String formatResult(double result) {
        // 지수표기법으로 출력되는 것 방지
        DecimalFormat df = new DecimalFormat("#.##########");
        if (result % 1 == 0) {
            return String.format("%.0f", result);
        } else {
            return df.format(result);
        }
    }

}
