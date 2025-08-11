package com.nepalibazar.usecase.authuser;

import com.nepalibazar.core.security.SecurityUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AuthenticateUseCase implements UseCase<AuthenticateUseCaseRequest, AuthenticateUseCaseResponse> {
    public final UserRepository userRepository;

    @Inject
    public AuthenticateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticateUseCaseResponse execute(AuthenticateUseCaseRequest request) {
//        UserEntity userEntity= userRepository.findUserByEmailPhone(request.emailphone())
//                .orElseThrow(()->new RuntimeException("User not found"));
//
//        if(!SecurityUtils.verifyPassword(request.password(), userEntity.getPassword())){
//            throw  new RuntimeException("Invaid password");
//        }
//        return new AuthenticateUseCaseResponse();
//    }
        return null;
    }
}