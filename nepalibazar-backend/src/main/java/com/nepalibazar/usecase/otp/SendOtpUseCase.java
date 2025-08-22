package com.nepalibazar.usecase.otp;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.service.EmailValidationService;
import com.nepalibazar.service.OtpService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class SendOtpUseCase  {

    private final EmailValidationService emailValidationService;
    private final OtpService otpService;
    private final UserRepository userRepository;

    @Inject
    public SendOtpUseCase(EmailValidationService emailValidationService,
                          OtpService otpService,
                          UserRepository userRepository) {
        this.emailValidationService = emailValidationService;
        this.otpService = otpService;
        this.userRepository = userRepository;
    }



    public SendOtpUseCaseResponse execute(String email){
        if(email == null || email.isBlank()){
            return new SendOtpUseCaseResponse(-1,"Email is required.");
        }
        if(!emailValidationService.isGoogleEmail(email)){
            return new SendOtpUseCaseResponse(-1,"Account not found.Please enter vali google account.");
        }
//        var normalizedEmail = email.trim().toLowerCase();
//        if (userRepository.findByEmailPhone(normalizedEmail).isPresent()) {
//            return new SendOtpUseCaseResponse(-1, "Gmail already registered.");
//        }
        otpService.sendOtpToGmail(email);
        return new SendOtpUseCaseResponse(0,"Otp sent successfully to your gmail.");
    }


}
