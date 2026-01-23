package com.nepalibazar.usecase.admin.getUsers;

import com.nepalibazar.convertor.UserConvertor;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.repository.UserRepository;
import com.nepalibazar.usecase.user.search.SearchAllUserUcResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class GetUsersUseCase {

    private final UserRepository userRepository;

    @Inject
    public  GetUsersUseCase(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<SearchAllUserUcResponse> execute(){
        List<UserEntity> users= userRepository.findAll();
        return users.stream()
                .map(UserConvertor::toSearchAllUserUcResponse)
                .collect(Collectors.toList());
    }
}
