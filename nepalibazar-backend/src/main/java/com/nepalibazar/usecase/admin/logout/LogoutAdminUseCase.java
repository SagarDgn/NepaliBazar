package com.nepalibazar.usecase.admin.logout;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.repository.AdminRepository;
import com.nepalibazar.usecase.user.logout.LogoutUserUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class LogoutAdminUseCase {

    private final AdminRepository adminRepository;

    @Inject
    public LogoutAdminUseCase(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

    public LogoutAdminUseCaseResponse execute(String token) {
        try {
            String jwt = token.replace("Bearer", "").trim();
            String userName = JwtUtils.extractEmailPhone(jwt);
            adminRepository.updateLoginStatus(userName, false);
            return new LogoutAdminUseCaseResponse(0, "Logout successful");
        }catch (Exception e){
            return new LogoutAdminUseCaseResponse(0,"Logout failure" +e.getMessage());
        }
    }
}
