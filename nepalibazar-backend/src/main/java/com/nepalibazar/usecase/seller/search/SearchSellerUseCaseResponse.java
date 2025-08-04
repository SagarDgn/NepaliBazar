package com.nepalibazar.usecase.seller.search;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.domain.USER_ROLE;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SearchSellerUseCaseResponse(Integer id,
                                          String sellerName,
                                          String emailPhone,
                                          String location,
                                          USER_ROLE role,
                                          String rating,
                                          String category) implements UseCase.Response {
}
