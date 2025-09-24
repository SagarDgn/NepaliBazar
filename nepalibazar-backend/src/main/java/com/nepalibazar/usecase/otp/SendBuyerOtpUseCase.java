package com.nepalibazar.usecase.otp;

import com.nepalibazar.repository.SellerRepository;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.service.EmailValidationService;
import com.nepalibazar.service.OtpService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SendBuyerOtpUseCase {

    private final EmailValidationService emailValidationService;
    private final OtpService otpService;
    private final UserRepository userRepository;


    @Inject
    public SendBuyerOtpUseCase(EmailValidationService emailValidationService,
                               OtpService otpService,
                               UserRepository userRepository
                               ) {
        this.emailValidationService = emailValidationService;
        this.otpService = otpService;
        this.userRepository = userRepository;

    }



    public SendOtpUseCaseResponse execute(String emailPhone){
        if(emailPhone == null || emailPhone.isBlank()){
            return new SendOtpUseCaseResponse(-1,"Email is required.");
        }
        if(!emailValidationService.isGoogleEmail(emailPhone)){
            return new SendOtpUseCaseResponse(-1,"Account not found.Please enter vali google account.");
        }
        var normalizedEmail= emailPhone.trim().toLowerCase();
        if(userRepository.findByEmailPhone(normalizedEmail).isPresent()){
            return new SendOtpUseCaseResponse(-1,"Email is already in use.");
        }

        otpService.sendOtpToGmail(emailPhone);
        return new SendOtpUseCaseResponse(0,"Otp sent successfully to your gmail.");
    }


}
