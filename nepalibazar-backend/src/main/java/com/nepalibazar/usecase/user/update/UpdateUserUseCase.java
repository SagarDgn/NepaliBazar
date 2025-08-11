package com.nepalibazar.usecase.user.update;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class UpdateUserUseCase implements UseCase<UpdateUserUseCaseRequest, UpdateUserUseCaseResponse> {
    public final UserRepository userRepository;

    @Inject
    public UpdateUserUseCase(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UpdateUserUseCaseResponse execute(UpdateUserUseCaseRequest request){
        var existingUser= userRepository.findById(request.id())
                .orElseThrow(()->new RuntimeException("User not found with: "+ request.id()));

        existingUser.setUserName(request.userName());
        existingUser.setEmailPhone(request.emailPhone());
        existingUser.setPassword(request.password());
        existingUser.setAddress(request.address());

        var updatedUser= userRepository.update(existingUser);
        return new UpdateUserUseCaseResponse(updatedUser.getId(), "User updated Successfully");
    }
}
