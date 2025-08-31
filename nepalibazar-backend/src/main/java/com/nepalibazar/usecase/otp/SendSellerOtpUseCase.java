package com.nepalibazar.usecase.otp;


import com.nepalibazar.repository.SellerRepository;
import com.nepalibazar.service.EmailValidationService;
import com.nepalibazar.service.OtpService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SendSellerOtpUseCase {

    private final EmailValidationService emailValidationService;
    private final OtpService otpService;
    private final SellerRepository sellerRepository;


    @Inject
    public SendSellerOtpUseCase(EmailValidationService emailValidationService,
                               OtpService otpService,
                               SellerRepository sellerRepository
    ) {
        this.emailValidationService = emailValidationService;
        this.otpService = otpService;
        this.sellerRepository = sellerRepository;

    }



    public SendOtpUseCaseResponse execute(String email){
        if(email == null || email.isBlank()){
            return new SendOtpUseCaseResponse(-1,"Email is required.");
        }
        if(!emailValidationService.isGoogleEmail(email)){
            return new SendOtpUseCaseResponse(-1,"Account not found.Please enter vali google account.");
        }
        var normalizedEmail= email.trim().toLowerCase();
        if(sellerRepository.findByEmailPhone(normalizedEmail).isPresent()){
            System.out.println("Inside email check");
            return new SendOtpUseCaseResponse(-1,"Email is already in use.");
        }

        otpService.sendOtpToGmail(email);
        return new SendOtpUseCaseResponse(0,"Otp sent successfully to your gmail.");
    }


}

