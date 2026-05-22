package com.example.maytinhcanhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping({"/", "/calculator"})
    public String show(){
        return "index";
    }
    @PostMapping("/calculator")
    public String calculate(@RequestParam("num1") int num1, @RequestParam("num2") int num2, @RequestParam("action") String action, Model model){
        double res = 0;
        String message = "";

        switch (action) {
            case "add":
                res = num1 + num2;
                message = "Result Addition: " + res;
                break;
            case "sub":
                res = num1 - num2;
                message = "Result Subtraction: " + res;
                break;
            case "mul":
                res = num1 * num2;
                message = "Result Multiplication: " + res;
                break;
            case "div":
                if (num2 == 0) {
                    message = "Lỗi: Không thể chia cho 0!";
                } else {
                    res = num1 / num2;
                    message = "Result Division: " + res;
                }
                break;
        }
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("result", message);
        return "index";
    }
}
