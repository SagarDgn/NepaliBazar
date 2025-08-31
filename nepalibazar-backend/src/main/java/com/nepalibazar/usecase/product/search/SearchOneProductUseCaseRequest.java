package com.nepalibazar.usecase.product.search;

import com.nepalibazar.core.usecase.UseCase;

public record SearchOneProductUseCaseRequest(Integer id)
        implements UseCase.Request{
}
