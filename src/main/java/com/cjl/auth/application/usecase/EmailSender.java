package com.cjl.auth.application.usecase;

import com.cjl.auth.domain.User;

public interface EmailSender {
    void sendRecoveryEmail(String token, User user, String url);
}
