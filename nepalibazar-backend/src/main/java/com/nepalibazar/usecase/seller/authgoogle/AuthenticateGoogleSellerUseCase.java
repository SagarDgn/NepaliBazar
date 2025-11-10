package com.nepalibazar.usecase.seller.authgoogle;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.domain.USER_ROLE;
import com.nepalibazar.entity.SellerEntity;
import com.nepalibazar.repository.SellerRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Collections;
import java.util.Optional;

@Singleton
public class AuthenticateGoogleSellerUseCase implements UseCase<AuthenticateGoogleSellerUseCaseRequest, AuthenticateGoogleSellerUseCaseResponse> {

    public final SellerRepository sellerRepository;

    @Inject
    public AuthenticateGoogleSellerUseCase(SellerRepository sellerRepository){
        this.sellerRepository=sellerRepository;
    }

    @Override
    public AuthenticateGoogleSellerUseCaseResponse execute(AuthenticateGoogleSellerUseCaseRequest request) {
        try{
            var verifier= new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance()
            )
                    .setAudience(Collections.singletonList(request.clientId()))
                    .build();

            GoogleIdToken idToken= verifier.verify(request.token());
            if(idToken==null){
                return new AuthenticateGoogleSellerUseCaseResponse(null,"Exception occured",null,null);
            }
            GoogleIdToken.Payload payload= idToken.getPayload();
            String email= payload.getEmail();
            String name= (String) payload.get("name");

            Optional<SellerEntity> sellerEntity= sellerRepository.findByEmailPhone(email);

            SellerEntity seller= sellerEntity.orElseGet(()->{
                SellerEntity newSeller= new SellerEntity();
                newSeller.setEmailPhone(email);
                newSeller.setSellerName(name);
                newSeller.setPassword("GOOGLE_LOGIN");
                newSeller.setRole(USER_ROLE.valueOf("SELLER"));
                return sellerRepository.save(newSeller);
            });

            sellerRepository.updateLoginStatus(seller.getEmailPhone(),true);
            String jwt= JwtUtils.generateToken(seller.getEmailPhone(), String.valueOf(seller.getRole()));
            return new AuthenticateGoogleSellerUseCaseResponse(
                    jwt,
                    "Google authenticatication success",
                    new String[]{String.valueOf(seller.getRole())},
                    seller.getSellerName()
            );
        }catch (Exception e){
            e.printStackTrace();
            return  new AuthenticateGoogleSellerUseCaseResponse(null,"Exception occured",null,null);
        }
    }
}
