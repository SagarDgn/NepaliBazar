package com.nepalibazar.usecase.user.authgoogle;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.nepalibazar.core.security.JwtUtils;
import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.domain.USER_ROLE;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Collections;
import java.util.Optional;

@Singleton
public class AuthenticateGoogleUserUseCase implements UseCase<AuthenticateGoogleUserUseCaseRequest,AuthenticateGoogleUserUseCaseResponse> {

    public final UserRepository userRepository;

    @Inject
    public AuthenticateGoogleUserUseCase(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public AuthenticateGoogleUserUseCaseResponse execute(AuthenticateGoogleUserUseCaseRequest request) {
        try{
            var verifier= new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance()
            )
                    .setAudience(Collections.singletonList(request.clientId()))
                    .build();

            GoogleIdToken idToken= verifier.verify(request.token());
            if(idToken==null){
                return new AuthenticateGoogleUserUseCaseResponse(null,"Exception occured",null,null);
            }
            GoogleIdToken.Payload payload= idToken.getPayload();
            String email= payload.getEmail();
            String name= (String) payload.get("name");

            Optional<UserEntity> userEntity= userRepository.findByEmailPhone(email);

            UserEntity user= userEntity.orElseGet(()->{
                UserEntity newUser= new UserEntity();
                newUser.setEmailPhone(email);
                newUser.setUserName(name);
                newUser.setPassword("GOOGLE_LOGIN");
                newUser.setUserRole(USER_ROLE.valueOf("BUYER"));
                return userRepository.save(newUser);
            });

            userRepository.updateLoginStatus(user.getEmailPhone(),true);
            String jwt= JwtUtils.generateToken(user.getEmailPhone(), String.valueOf(user.getUserRole()));
            return new AuthenticateGoogleUserUseCaseResponse(
                    jwt,
                    "Google authenticatication success",
                    new String[]{String.valueOf(user.getUserRole())},
                    user.getUserName()
            );
        }catch (Exception e){
            e.printStackTrace();
            return  new AuthenticateGoogleUserUseCaseResponse(null,"Exception occured",null,null);
        }
    }
}
