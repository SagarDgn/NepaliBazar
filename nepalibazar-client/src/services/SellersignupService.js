import api from "./api";

//service to get otp from server
export const sentOtp=(payload)=>{
    return api.post("/seller/sent-otp",payload,{
        header:{"Content-type":"application/json"},
    });
};

//service for verification and signup

export const signUpSeller=(sellerData)=>{
    return api.post("/seller/signup", sellerData);
};