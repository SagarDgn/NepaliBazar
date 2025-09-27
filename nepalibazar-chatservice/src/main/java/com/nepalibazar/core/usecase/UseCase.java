package com.nepalibazar.core.usecase;

@FunctionalInterface
public interface UseCase <I extends UseCase.Request, O extends UseCase.Response>{

    interface Request{

    }
    interface Response{

    }

    O execut(I request);
}
