import axios from "axios";

export type Role="SELLER";

export interface SignupRequest{
    sellerName: string;
    emailPhone: string;
    password: string;
    location: string;
    productCategory: string;
    role: Role;
    otp?: string;
}

export interface RestResponse<T>{
    code: string;
    message:string;
    data: T;
}

const API_BASE_URL="http://localhost:8080/api/seller";

export const sendOtp = async(email: string): Promise<RestResponse<any>> =>{
    const response= await axios.post<RestResponse<any>>(`${API_BASE_URL}/sent-otp`,{
        email,
    });
      return response.data;
};

export const signupSeller= async(
    requestData: SignupRequest
): Promise<RestResponse<any>>=>{
    const response= axios.post<RestResponse<any>>(
        `${API_BASE_URL}/signup`,
        requestData
    );
  return (await response).data;
};