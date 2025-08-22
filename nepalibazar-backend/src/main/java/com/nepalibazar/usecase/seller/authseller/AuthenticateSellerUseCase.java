package com.nepalibazar.usecase.seller.authseller;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.security.SecurityUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.SellerRepository;
import com.nepalibazar.usecase.user.authuser.AuthenticateUserUseCaseResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AuthenticateSellerUseCase implements UseCase<AuthenticateSellerUseCaseRequest, AuthenticateSellerUseCaseResponse> {

    public final SellerRepository sellerRepository;

    @Inject
    public AuthenticateSellerUseCase(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    @Override
    public AuthenticateSellerUseCaseResponse execute(AuthenticateSellerUseCaseRequest request){
        var normalizedEmail= request.emailPhone().trim().toLowerCase();
        return sellerRepository.findByEmailPhone(normalizedEmail)
                .filter(sellerEntity-> SecurityUtils.verifyPassword(request.password(), sellerEntity.getPassword()))
                .map(sellerEntity-> new AuthenticateSellerUseCaseResponse(
                        JwtUtils.generateToken(sellerEntity.getEmailPhone()),
                        "Success Authenticate",
                        new String[] {String.valueOf(sellerEntity.getRole())},
                        sellerEntity.getSellerName()
                ))
                .orElseThrow(()->new RuntimeException("Invalid username or password"));
    }
}
