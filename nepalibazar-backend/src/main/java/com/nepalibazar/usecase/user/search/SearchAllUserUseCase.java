package com.nepalibazar.usecase.user.search;

import com.nepalibazar.convertor.UserConvertor;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class SearchAllUserUseCase {

    public final UserRepository userRepository;

    @Inject
    public  SearchAllUserUseCase(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<SearchAllUserUcResponse> execute(){
        List<UserEntity> users= userRepository.findAll();
        return users.stream()
                .map(UserConvertor::toSearchAllUserUcResponse)
                .collect(Collectors.toList());
    }
}
