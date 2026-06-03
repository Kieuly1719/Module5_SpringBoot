package com.example.mail.controller;

import com.example.mail.model.EmailConfig;
import com.example.mail.service.EmailConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailConfigController {
    private final EmailConfigService emailConfigService;

    public EmailConfigController(EmailConfigService emailConfigService) {
        this.emailConfigService = emailConfigService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/settings";
    }

    @GetMapping("/settings")
    public String showSettingsForm(Model model) {
        model.addAttribute("emailConfig", emailConfigService.getConfig());

        model.addAttribute("languages", new String[]{
                "English", "Vietnamese", "Japanese", "Chinese"
        });

        model.addAttribute("pageSizes", new int[]{
                5, 10, 15, 25, 50, 100
        });

        return "settings";
    }

    @PostMapping("/settings")
    public String updateSettings(@ModelAttribute("emailConfig") EmailConfig emailConfig) {
        emailConfigService.updateConfig(emailConfig);
        return "redirect:/settings/result";
    }

    @GetMapping("/settings/result")
    public String showResult(Model model) {
        model.addAttribute("emailConfig", emailConfigService.getConfig());
        return "result";
    }
}
