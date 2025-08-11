package com.nepalibazar.usecase.user.add;

import com.nepalibazar.convertor.UserConvertor;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.service.EmailService;
import com.nepalibazar.service.EmailValidationService;
import com.nepalibazar.service.OtpService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AddUserUseCase implements UseCase<AddUserUseCaseRequest, AddUserUseCaseResponse> {
    public final UserRepository userRepository;
    public final EmailValidationService emailValidationService;
    public final EmailService emailService;
    public final OtpService otpService;

    @Inject
    public AddUserUseCase(UserRepository userRepository,
                          EmailValidationService emailValidationService,
                          EmailService emailService,
                          OtpService otpService){
        this.userRepository=userRepository;
        this.emailValidationService=emailValidationService;
        this.emailService= emailService;
        this.otpService=otpService;
    }

    @Override
    public AddUserUseCaseResponse execute(AddUserUseCaseRequest request) {

        if (request.emailPhone() == null || request.emailPhone().isBlank()) {
            return new AddUserUseCaseResponse(-1,null, "Email is required");
        }

        if (!emailValidationService.isGoogleEmail(request.emailPhone())) {
            return new AddUserUseCaseResponse(-1,null, "Invalid Google account");
        }

        if(request.otp()==null || request.otp().isBlank()){
            return new AddUserUseCaseResponse(-1,null,"Otp is needed for registration.");
        }

        if (!otpService.verifyOtp(request.emailPhone(), request.otp())) {
            return new AddUserUseCaseResponse(-1,null, "Invalid OTP.");
        }
        otpService.invalidate(request.emailPhone());
        try {
            var userEntity = UserConvertor.toEntity(request);
            var saved = userRepository.save(userEntity);
            return new AddUserUseCaseResponse(0,  null,"User added successfully");
        } catch (Exception e) {
            throw new RuntimeException("Cannot register a user: " + e.getMessage(), e);
        }
    }

}
