import api from "./api";

export default{
    async login(sellerData){
        try{
            const response= await api.post('/auth/seller/login',sellerData)
            const token= response.data.token

            if(token){
                localStorage.setItem('jwt',token)
                localStorage.setItem('role', 'SELLER')
            }
            return response.data
        }catch(error){
            throw error.response?.data || {message: "Login failed"};
        }
    },
    getToken(){
        return localStorage.getItem('jwt')
    },

     getRole() {
    return localStorage.getItem('role');
  },

    isAuthenticated(){
        return !!localStorage.getItem('jwt')
    }
}