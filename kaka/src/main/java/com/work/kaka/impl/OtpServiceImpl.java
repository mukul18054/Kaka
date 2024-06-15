package com.work.kaka.impl;

import com.work.kaka.service.EmailService;
import com.work.kaka.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private EmailService emailService;

    private static final int OTP_LENGTH = 6;
    private SecureRandom random = new SecureRandom();

    @Override
    public String generateOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    @Override
    public void sendOtp(String email, String otp) {
        String subject = "Your OTP Code";
        String text = "Your OTP code is: " + otp;
        emailService.sendSimpleMessage(email, subject, text);
    }
}
