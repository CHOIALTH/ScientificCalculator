package com.example.ScientificCalculator.Controller;

import com.example.ScientificCalculator.ExpressionRequest;
import com.example.ScientificCalculator.Service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class CalculatorController {
    private final CalculatorService calculatorService = new CalculatorService();

    @GetMapping("/")
    public String calculatorMain(Model model){
        model.addAttribute("expression", "");
        model.addAttribute("result", null);
        return "calculator";
    }
    @PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> calculate(@RequestBody ExpressionRequest requestData) {
        String expression = requestData.getExpression();
        Map<String, Object> response = new HashMap<>();
        try {
            double result = calculatorService.calculate(expression);
            response.put("result", result);
            System.out.println("result ok");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            System.out.println("result error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
