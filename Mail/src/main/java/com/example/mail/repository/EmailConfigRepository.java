package com.example.mail.repository;

import com.example.mail.model.EmailConfig;

public interface EmailConfigRepository {
    EmailConfig getConfig();

    void updateConfig(EmailConfig emailConfig);
}
