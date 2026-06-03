package com.example.mail.repository;

import com.example.mail.model.EmailConfig;
import org.springframework.stereotype.Repository;

@Repository
public class EmailConfigRepositoryImpl implements EmailConfigRepository{
    private EmailConfig currentConfig = new EmailConfig(
            "English",
            25,
            false,
            "ThorKing, Asgard"
    );

    @Override
    public EmailConfig getConfig() {
        return currentConfig;
    }

    @Override
    public void updateConfig(EmailConfig emailConfig) {
        currentConfig = emailConfig;
    }
}
