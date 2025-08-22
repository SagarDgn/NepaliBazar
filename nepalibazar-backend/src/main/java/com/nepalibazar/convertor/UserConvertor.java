package com.nepalibazar.convertor;

import com.nepalibazar.core.security.SecurityUtils;
import com.nepalibazar.domain.USER_ROLE;
import com.nepalibazar.entity.UserEntity;
import com.nepalibazar.usecase.user.add.AddUserUseCaseRequest;
import com.nepalibazar.usecase.user.search.SearchAllUserUcResponse;

public class UserConvertor {
    private UserConvertor(){}

    public static UserEntity toEntity(AddUserUseCaseRequest user){
        UserEntity userEntity= new UserEntity();
        userEntity.setUserName(user.userName());
        userEntity.setEmailPhone(user.emailPhone().trim().toLowerCase());
        userEntity.setPassword(SecurityUtils.hashPassword(user.password()));
        userEntity.setAddress(user.address());
        userEntity.setUserRole(USER_ROLE.valueOf("BUYER"));
        return userEntity;
    }

    public static SearchAllUserUcResponse toSearchAllUserUcResponse(UserEntity user){
        return new SearchAllUserUcResponse(
                user.getId(),
                user.getUserName(),
                user.getEmailPhone(),
                user.getAddress(),
                user.getUserRole()
        );
    }
}
