package com.nepalibazar.usecase.user.logout;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class LogoutUserUseCase {

    public final UserRepository userRepository;

    @Inject
    public LogoutUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public LogoutUserUseCaseResponse execute(String token) {
        try {
            String jwt = token.replace("Bearer", "").trim();
            String email = JwtUtils.extractEmailPhone(jwt);
            userRepository.updateLoginStatus(email, false);
            return new LogoutUserUseCaseResponse(0, "Logout successful");
        }catch (Exception e){
            return new LogoutUserUseCaseResponse(0,"Logout failure" +e.getMessage());
        }
    }
}
