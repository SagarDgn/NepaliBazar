import axios from "axios";

const API_BASE_URL= 'http://localhost:8080';

export interface LoginPayload{
    emailPhone: string,
    password: string
}

export interface LoginResponse{
    token: string,
    sellername: string,
    message: string
}

export const loginSeller={
    login: async(payload: LoginPayload):Promise<LoginResponse>=>{
        const response=  await axios.post(`${API_BASE_URL}/api/auth/seller/login`,payload);
        return response.data;
    },
};