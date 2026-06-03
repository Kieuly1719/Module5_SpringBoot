package com.example.mail.service;

import com.example.mail.model.EmailConfig;
import com.example.mail.repository.EmailConfigRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailConfigServiceImpl implements EmailConfigService{
    private final EmailConfigRepository emailConfigRepository;

    public EmailConfigServiceImpl(EmailConfigRepository emailConfigRepository) {
        this.emailConfigRepository = emailConfigRepository;
    }

    @Override
    public EmailConfig getConfig() {
        return emailConfigRepository.getConfig();
    }

    @Override
    public void updateConfig(EmailConfig emailConfig) {
        emailConfigRepository.updateConfig(emailConfig);
    }
}
