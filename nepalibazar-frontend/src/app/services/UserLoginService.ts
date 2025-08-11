import axios from "axios";

const API_BASE_URL= 'http://localhost:8080';

export interface LoginPayload{
    emailPhone: string
    password : string
}

export interface LoginResponse{
    token: string
    username: string
}

export const loginUser= {
    login: async (payload: LoginPayload): Promise<LoginResponse>=>{
        const response= await axios.post(`${API_BASE_URL}/api/auth/user/login`,payload);
        return response.data; //token, username
    },
};