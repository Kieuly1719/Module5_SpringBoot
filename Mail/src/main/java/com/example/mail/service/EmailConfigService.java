package com.example.mail.service;

import com.example.mail.model.EmailConfig;

public interface EmailConfigService {
    EmailConfig getConfig();

    void updateConfig(EmailConfig emailConfig);
}
