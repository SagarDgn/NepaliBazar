//package com.nepaliBazar.controller;
//
//import com.nepaliBazar.core.response.RestResponse;
//import com.nepaliBazar.usecase.user.delete.DeleteUserUcResponse;
//import com.nepaliBazar.usecase.user.delete.DeleteUserUseCase;
//import com.nepaliBazar.usecase.user.search.SearchAllUserUcResponse;
//import com.nepaliBazar.usecase.user.search.SearchAllUserUseCase;
//import com.nepaliBazar.usecase.user.update.UpdateUserUseCase;
//import com.nepaliBazar.usecase.user.update.UpdateUserUseCaseRequest;
//import com.nepaliBazar.usecase.user.update.UpdateUserUseCaseResponse;
//import io.micronaut.http.annotation.*;
//import jakarta.inject.Inject;
//
//import java.util.List;
//
//@Controller("/api1")
//public class AdminController {
//
//    public final SearchAllUserUseCase searchAllUserUseCase;
//    private final UpdateUserUseCase updateUserUseCase;
//    public final DeleteUserUseCase deleteUserUseCase;
//
//    @Inject
//    public AdminController(SearchAllUserUseCase searchAllUserUseCase,
//                           UpdateUserUseCase updateUserUseCase,
//                           DeleteUserUseCase deleteUserUseCase){
//        this.searchAllUserUseCase=searchAllUserUseCase;
//        this.updateUserUseCase= updateUserUseCase;
//        this.deleteUserUseCase=deleteUserUseCase;
//    }
//    @Get("/users/search")
//    public RestResponse<List<SearchAllUserUcResponse>> search() {
//        try {
//            List<SearchAllUserUcResponse> users = searchAllUserUseCase.execute();
//            return new RestResponse<>("1", "Success", users);
//        } catch (Exception e) {
//            return new RestResponse<>("-1", "Failed to fetch users: " + e.getMessage(), null);
//        }
//    }
//
//    @Put("/user/update/{id}")
//    public RestResponse<UpdateUserUseCaseResponse> updateUser(@PathVariable Integer id,
//                                                              @Body UpdateUserUseCaseRequest request){
//        try{
//            var finalRequest = new UpdateUserUseCaseRequest(
//                    id,
//                    request.userName(),
//                    request.emailPhone(),
//                    request.password(),
//                    request.address()
//
//            );
//            UpdateUserUseCaseResponse response= updateUserUseCase.execute(finalRequest);
//            return RestResponse.success(response);
//
//        }catch (Exception e){
//            return RestResponse.error("Cannot update user!! "+ e.getMessage());
//        }
//    }
//
//    @Delete("/user/delete/{id}")
//    public RestResponse<DeleteUserUcResponse> deleteUser(@PathVariable Integer id){
//        try{
//            DeleteUserUcResponse response= deleteUserUseCase.execute(id);
//            return RestResponse.success(response);
//        }
//        catch (Exception e){
//            return RestResponse.error("Fail to delete User"+ e.getMessage());
//        }
//    }
//}
