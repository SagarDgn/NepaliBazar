package com.nepalibazar.usecase.seller.add;

import com.nepalibazar.convertor.SellerConvertor;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.SellerRepository;
import com.nepalibazar.service.EmailService;
import com.nepalibazar.service.EmailValidationService;
import com.nepalibazar.service.OtpService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AddSellerUseCase implements UseCase<AddSellerUseCaseRequest, AddSellerUseCaseResponse> {

    public final SellerRepository sellerRepository;
    public final EmailValidationService emailValidationService;
    public final EmailService emailService;
    public final OtpService otpService;

    @Inject
    public AddSellerUseCase(SellerRepository sellerRepository,
                            EmailValidationService emailValidationService,
                            EmailService emailService,
                            OtpService otpService){
        this.sellerRepository=sellerRepository;
        this.emailValidationService= emailValidationService;
        this.emailService= emailService;
        this.otpService= otpService;
    }

    public AddSellerUseCaseResponse execute(AddSellerUseCaseRequest request){


        if(request.emailPhone()==null || request.emailPhone().isBlank()){
            return new AddSellerUseCaseResponse(-1, null, "Email is required");
        }
        if(!emailValidationService.isGoogleEmail(request.emailPhone())){
            return new AddSellerUseCaseResponse(-1, null, "Invalid Gmail");
        }
        if(request.otp()==null || request.otp().isBlank()){
            return new AddSellerUseCaseResponse(-1, null, "OTP is needed for registration.");
        }
        if(!otpService.verifyOtp(request.emailPhone(), request.otp())){
            return new AddSellerUseCaseResponse(-1, null,"Invalid OTP.");
        }
        try{
            var sellerEntity= SellerConvertor.toEntity(request);
            var saved= sellerRepository.save(sellerEntity);
            return new  AddSellerUseCaseResponse(0, null, "Successfull registration for seller");
        }catch (Exception e){
            throw new RuntimeException("Fail to register as seller "+e.getMessage(),e);
        }
    }
}
