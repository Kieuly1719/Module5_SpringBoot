package com.example.maytinhcanhan.controller;

import com.example.maytinhcanhan.service.CalculatorService;
import com.example.maytinhcanhan.service.ICalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    public ICalculatorService calculatorService = new CalculatorService();
    @GetMapping({"/", "/calculator"})
    public String show(){
        return "index";
    }
    @PostMapping("/calculator")
    public String calculate(@RequestParam("num1") int num1, @RequestParam("num2") int num2, @RequestParam("action") String action, Model model){
        String message = calculatorService.execute(action, num1, num2);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", message);
        return "index";
    }
}
