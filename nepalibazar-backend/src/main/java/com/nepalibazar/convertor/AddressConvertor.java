package com.nepalibazar.convertor;

import com.nepalibazar.entity.AddressEntity;
import com.nepalibazar.usecase.order.place.PlaceOrderUseCaseRequest;

public class AddressConvertor {
    private AddressConvertor(){}

    public static AddressEntity toEntity(PlaceOrderUseCaseRequest address){
        AddressEntity addressEntity= new AddressEntity();
        addressEntity.setCountry(address.country());
        addressEntity.setState(address.state());
        addressEntity.setCity(address.city());
        addressEntity.setStreet(address.street());
        addressEntity.setPinCode(address.pinCode());
        addressEntity.setPhoneNumber(address.phoneNumber());

        return addressEntity;
    }
}
