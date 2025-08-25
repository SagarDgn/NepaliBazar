package com.nepalibazar.usecase.seller.logout;

import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.SellerRepository;
import com.nepalibazar.usecase.user.logout.LogoutUserUseCase;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class LogoutSellerUseCase {

    public final SellerRepository sellerRepository;

    @Inject
    public LogoutSellerUseCase(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }


    public LogoutSellerUseCaseResponse execute(String token) {
        try{
            String jwt= token.replace("Bearer", "").trim();
            String emailPhone= JwtUtils.extractEmailPhone(jwt);
            sellerRepository.updateLoginStatus(emailPhone,false);
            return new  LogoutSellerUseCaseResponse(0, "Logout successful");
        }catch(Exception e){
            return  new LogoutSellerUseCaseResponse(-1, "Logout failed"+e.getMessage());
        }

    }
}
