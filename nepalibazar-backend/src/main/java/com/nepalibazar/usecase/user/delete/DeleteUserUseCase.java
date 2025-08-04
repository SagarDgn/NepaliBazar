package com.nepalibazar.usecase.user.delete;

import com.nepalibazar.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DeleteUserUseCase {
    public final UserRepository userRepository;

    @Inject
    public DeleteUserUseCase(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public DeleteUserUcResponse execute(Integer id){

            if(!userRepository.existsById(id)){
                throw new RuntimeException("User not found");
            }
            else{
                userRepository.deleteById(id);
                return new DeleteUserUcResponse(id, "User deleted successfully");
            }
    }
}
