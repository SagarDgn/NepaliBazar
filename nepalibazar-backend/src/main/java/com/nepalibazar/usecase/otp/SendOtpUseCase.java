package com.nepalibazar.usecase.otp;

import com.nepalibazar.service.EmailValidationService;
import com.nepalibazar.service.OtpService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SendOtpUseCase {

    private final EmailValidationService emailValidationService;
    private final OtpService otpService;

    @Inject
    public SendOtpUseCase(EmailValidationService emailValidationService, OtpService otpService) {
        this.emailValidationService = emailValidationService;
        this.otpService = otpService;
    }

    public SendOtpUseCaseResponse execute(String email){
        if(email == null || email.isBlank()){
            return new SendOtpUseCaseResponse("Email is required.");
        }
        if(!emailValidationService.isGoogleEmail(email)){
            return new SendOtpUseCaseResponse("Account not found.Please enter vali google account.");
        }
        otpService.sendOtpToGmail(email);
        return new SendOtpUseCaseResponse("Otp sent successfully to your gmail.");
    }
}
