import api from "./api";

export default{
    async login(userData){
        try{
            const response= await api.post('/auth/user/login',userData)
            const token= response.data.token

            if(token){
                localStorage.setItem('jwt',token)
            }
            return response.data

        }catch(error){
            throw error.response?.data || {message: "Login failed"};
            
        }
    },

     getToken() {
        return localStorage.getItem('jwt')
    },

     isAuthenticated() {
        return !!localStorage.getItem('jwt')
     }

}