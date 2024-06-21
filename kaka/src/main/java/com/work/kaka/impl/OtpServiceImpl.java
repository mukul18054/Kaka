package com.work.kaka.impl;

import com.work.kaka.service.EmailService;
import com.work.kaka.service.OtpService;
import com.work.kaka.exception.EmailOtpServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private EmailService emailService;

    private static final int OTP_LENGTH = 6;
    private SecureRandom random = new SecureRandom();
    private Map<String, String> otpMap = new HashMap<>();

    @Override
    public String generateOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    @Override
    public void sendOtp(String email, String otp) throws EmailOtpServiceException {
        String subject = "Your OTP Code";
        String text = "Your OTP code is: " + otp;
        try {
            otpMap.get("id").charAt(0);
            otpMap.put(email, otp); // Store OTP temporarily (replace with a more robust solution)
            emailService.sendSimpleMessage(email, subject, text);
        } catch (Exception e) {
            // Handle exception during email sending (e.g., network issues)
            throw new EmailOtpServiceException("Failed to send OTP due to: " + e.getMessage());
        }
    }

    @Override
    public boolean verifyOtp(String email, String enteredOtp) {
        try {
            String storedOtp = otpMap.get(email);
            if (storedOtp != null && storedOtp.equals(enteredOtp)) {
                otpMap.remove(email); // Remove OTP after successful verification
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            // Handle case where email is not found in the map
            return false;
        }
    }
}
