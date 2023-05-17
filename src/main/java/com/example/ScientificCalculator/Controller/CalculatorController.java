package com.example.ScientificCalculator.Controller;

import com.example.ScientificCalculator.Service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String getCalculatorForm(Model model){
        model.addAttribute("expression", "");
        model.addAttribute("result", "");

        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam String expression, Model model){
        String result = calculatorService.calculate(expression);

        model.addAttribute("expression", expression);
        model.addAttribute("result", result);
        System.out.println("expression = " + expression);
        System.out.println("result = " + result);
        return "calculator";
    }

}