package com.nepalibazar.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class OtpService {

    private final EmailService emailService;

    @Inject
    public OtpService(EmailService emailService){
        this.emailService=emailService;
    }
    private Map<String, String> store = new ConcurrentHashMap<>();
    private Random random= new SecureRandom();

    public String generateOtp(String email){
        String otp= String.format("%06d", random.nextInt(1_000_000));
        store.put(email,otp);
        return otp;

    }
    public boolean verifyOtp(String email, String otp){
        return otp!=null && otp.equals(store.get(email));
    }

    public void invalidate(String email){
        store.remove(email);
    }
    public void sendOtpToGmail(String email) {
        String otp = generateOtp(email);
        emailService.sendOtp(email, otp);
    }


}
