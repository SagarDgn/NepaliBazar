package com.nepalibazar.usecase.seller.authseller;

import com.nepalibazar.core.usecase.UseCase;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthenticateSellerUseCaseResponse( String token,
                                                 String successAuthenticate, String[] permission,
                                                 String sellerName)
        implements UseCase.Response {
}
