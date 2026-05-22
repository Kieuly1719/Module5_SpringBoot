package com.example.maytinhcanhan.service;

public class CalculatorService implements ICalculatorService{

    @Override
    public String execute(String action, int num1, int num2) {
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
        return message;
    }
}
