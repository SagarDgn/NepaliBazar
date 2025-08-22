package com.nepalibazar.usecase.user.authuser;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.security.SecurityUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AuthenticateUserUseCase implements UseCase<AuthenticateUserUseCaseRequest, AuthenticateUserUseCaseResponse> {
    public final UserRepository userRepository;

    @Inject
    public AuthenticateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticateUserUseCaseResponse execute(AuthenticateUserUseCaseRequest request) {
        var normalizedEmail = request.emailPhone().trim().toLowerCase();

        return userRepository.findByEmailPhone(normalizedEmail)
                .filter(userEntity -> SecurityUtils.verifyPassword(request.password(), userEntity.getPassword()))
                .map(userEntity -> new AuthenticateUserUseCaseResponse(
                        JwtUtils.generateToken(userEntity.getEmailPhone()),
                        "Success Authenticate",
                        new String[]{String.valueOf(userEntity.getUserRole())},
                        userEntity.getUserName()
                ))
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }



}
