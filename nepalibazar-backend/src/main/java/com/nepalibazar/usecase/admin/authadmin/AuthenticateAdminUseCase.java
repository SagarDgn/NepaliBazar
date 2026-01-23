package com.nepalibazar.usecase.admin.authadmin;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.security.SecurityUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.AdminRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AuthenticateAdminUseCase implements UseCase<AuthenticateAdminUseCaseRequest,AuthenticateAdminUseCaseResponse> {

    private final AdminRepository adminRepository;

    @Inject
    public AuthenticateAdminUseCase(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }


    @Override
    public AuthenticateAdminUseCaseResponse execute(AuthenticateAdminUseCaseRequest request) {
       var userName= request.userName();

       return adminRepository.findByUserName(userName)
               .filter(adminEntity -> SecurityUtils.verifyPassword(request.password(),adminEntity.getPassword()))
               .map(adminEntity -> {
                   adminRepository.updateLoginStatus(adminEntity.getUserName(), true);
                   return  new AuthenticateAdminUseCaseResponse(
                       JwtUtils.generateToken(request.userName(), "ADMIN"),
                       "Success Authenticate",
                       "ADMIN",
                       adminEntity.getUserName()
                );
               }
               )
               .orElseThrow(()->new RuntimeException("Invalid crenditals"));

    }
}
