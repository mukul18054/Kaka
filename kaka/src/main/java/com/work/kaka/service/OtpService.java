package com.work.kaka.service;

public interface OtpService {
    String generateOtp();
    void sendOtp(String email, String otp);
    boolean verifyOtp(String email, String enteredOtp);
}
