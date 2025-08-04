package com.nepalibazar.usecase.user.search;

import com.nepalibazar.core.usecase.UseCase;
import com.nepalibazar.domain.USER_ROLE;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SearchAllUserUcResponse(
                                      Integer id,
                                      String sellerName,
                                      String emailPhone,
                                      String address,
                                      USER_ROLE userRole
) implements UseCase.Response {
}
