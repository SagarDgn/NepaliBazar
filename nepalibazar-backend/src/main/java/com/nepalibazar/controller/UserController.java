package com.nepalibazar.controller;

import com.nepalibazar.core.response.RestResponse;
import com.nepalibazar.usecase.user.add.AddUserUseCaseRequest;
import com.nepalibazar.usecase.user.add.AddUserUseCase;
import com.nepalibazar.usecase.user.add.AddUserUseCaseResponse;
import com.nepalibazar.usecase.user.delete.DeleteUserUcResponse;
import com.nepalibazar.usecase.user.delete.DeleteUserUseCase;
import com.nepalibazar.usecase.otp.SendOtpUseCase;
import com.nepalibazar.usecase.otp.SendOtpUseCaseRequest;
import com.nepalibazar.usecase.otp.SendOtpUseCaseResponse;
import com.nepalibazar.usecase.user.search.SearchAllUserUcResponse;
import com.nepalibazar.usecase.user.search.SearchAllUserUseCase;
import com.nepalibazar.usecase.user.update.UpdateUserUseCase;
import com.nepalibazar.usecase.user.update.UpdateUserUseCaseRequest;
import com.nepalibazar.usecase.user.update.UpdateUserUseCaseResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/api")
public class UserController {
    public final AddUserUseCase addUserUseCase;
    public final UpdateUserUseCase updateUserUseCase;
    public final DeleteUserUseCase deleteUserUseCase;
    public final SearchAllUserUseCase searchAllUserUseCase;
    public final SendOtpUseCase sendOtpUseCase;


    @Inject
    public UserController(AddUserUseCase addUserUseCase,
                          UpdateUserUseCase updateUserUseCase,
                          DeleteUserUseCase deleteUserUseCase,
                          SearchAllUserUseCase searchAllUserUseCase,
                         SendOtpUseCase sendOtpUseCase){
        this.addUserUseCase=addUserUseCase;
        this.updateUserUseCase=updateUserUseCase;
        this.deleteUserUseCase= deleteUserUseCase;
        this.searchAllUserUseCase= searchAllUserUseCase;
        this.sendOtpUseCase=sendOtpUseCase;
    }


    @Post("/user/signup")
    public RestResponse<AddUserUseCaseResponse> postUser(@Body AddUserUseCaseRequest request) {
        try {
            AddUserUseCaseResponse response = addUserUseCase.execute(request);

            // Check for business logic failure from response.message or response.id
            if (response.code() != 0) {
                return new RestResponse<>("-1", response.message(), null);
            }

            // Success
            return RestResponse.success(response);

        } catch (Exception e) {
            return RestResponse.error("Cannot register user: " + e.getMessage());
        }
    }


    @Post("/user/sent-otp")
    public RestResponse<SendOtpUseCaseResponse> sendOtp(@Body SendOtpUseCaseRequest request){
        try{
            SendOtpUseCaseResponse response= sendOtpUseCase.execute(request.email());
            return RestResponse.success(response);
        }catch (Exception e){
            return RestResponse.error("Otp sending failed "+ e.getMessage());
        }
    }


    @Put("/user/update/{id}")
    public RestResponse<UpdateUserUseCaseResponse> updateUser(@PathVariable Integer id,
                                                              @Body UpdateUserUseCaseRequest request){
        try{
            var finalRequest = new UpdateUserUseCaseRequest(
                    id,
                    request.userName(),
                    request.emailPhone(),
                    request.password(),
                    request.address()

            );
            UpdateUserUseCaseResponse response= updateUserUseCase.execute(finalRequest);
            return RestResponse.success(response);

        }catch (Exception e){
            return RestResponse.error("Cannot update user!! "+ e.getMessage());
        }
    }

    @Delete("/user/delete/{id}")
    public RestResponse<DeleteUserUcResponse> deleteUser(@PathVariable Integer id){
        try{
            DeleteUserUcResponse response= deleteUserUseCase.execute(id);
            return RestResponse.success(response);
        }
        catch (Exception e){
            return RestResponse.error("Fail to delete User"+ e.getMessage());
        }
    }
    @Get("/users/search")
    public RestResponse<List<SearchAllUserUcResponse>> search() {
        try {
            List<SearchAllUserUcResponse> users = searchAllUserUseCase.execute();
            return new RestResponse<>("1", "Success", users);
        } catch (Exception e) {
            return new RestResponse<>("-1", "Failed to fetch users: " + e.getMessage(), null);
        }
    }
}
