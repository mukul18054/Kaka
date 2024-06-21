package com.work.kaka.service;

import com.work.kaka.exception.EmailOtpServiceException;

public interface OtpService {
    String generateOtp();
    void sendOtp(String email, String otp) throws EmailOtpServiceException;
    boolean verifyOtp(String email, String enteredOtp);
}
