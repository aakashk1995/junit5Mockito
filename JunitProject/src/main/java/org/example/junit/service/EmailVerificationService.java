package org.example.junit.service;


import org.example.junit.model.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}
