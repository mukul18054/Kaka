package com.work.kaka.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
