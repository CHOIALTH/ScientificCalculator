package com.example.ScientificCalculator.Controller;

import com.example.ScientificCalculator.Service.CalculatorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class CalculatorController {
    private final CalculatorService calculator = new CalculatorService();

    @GetMapping("/")
    public String calculatorMain(Model model){
        model.addAttribute("expression", "");
        model.addAttribute("result", null);
        return "calculator";
    }

    @ResponseBody
    @PostMapping(value = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> calculate(@RequestBody Map<String, String> requestData) {
        String formattedExpression = requestData.get("formattedExpression");
        Map<String, Object> responseBody = new HashMap<>();
        try {
            System.out.println("Expression: " + formattedExpression);
            Double result = calculator.calculate(formattedExpression);
            responseBody.put("result", result); // 'calculationResult'를 다시 'result'로 변경해야 합니다.
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            responseBody.put("error", e.getMessage());
        }
        return ResponseEntity.ok(responseBody);
    }


}
