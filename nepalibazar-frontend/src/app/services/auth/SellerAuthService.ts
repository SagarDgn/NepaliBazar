import axios from "axios";

const API_BASE_URL= 'http://localhost:8080';

export interface LoginPayload{
    emailPhone: string,
    password: string
}

export interface LoginResponse{
    token: string,
    sellername: string,
    permission: string,
    message: string
}

export const authSeller={
    login: async(payload: LoginPayload):Promise<LoginResponse>=>{
        const response=  await axios.post(`${API_BASE_URL}/api/auth/seller/login`,payload);
         const resData = response.data.data;
        return {
            token: resData.token,
            sellername: resData.sellerName,
            permission: resData.permission,
            message: response.data.message
        };
    },

    logout: async(token: string): Promise<boolean>=>{
        try{
            const response= await axios.post(`${API_BASE_URL}/api/auth/seller/logout`,
                {},
                {
                headers:{
                    Authorization: `Bearer ${token}`,
                },
            }
            );
            return response.status === 200;
            }catch(error){
                console.error("Logout failed: ",error);
                return false;
            }
        },
    

};


