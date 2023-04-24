package com.example.ScientificCalculator.Controller;

import com.example.ScientificCalculator.Service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CalculatorController {

    @GetMapping("/")
    public String calculatorMain(){
        return "calculator";
    }
    @PostMapping("/calculate")
    public String calculate(@RequestParam("formattedExpression") String expression, Model model) {
        String result = "";
        try {
            result = String.valueOf(CalculatorService.calculate(expression));
        } catch (Exception e) {
            result = "Error";
        }
        model.addAttribute("expression", expression);
        model.addAttribute("result", result);
        return "calculator";
    }
}
