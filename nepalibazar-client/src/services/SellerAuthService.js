import api from "./api";


export default {
  async login(sellerData) {
    try {
      const response = await api.post("/auth/seller/login", sellerData);
      const {token,permission} = response.data.data;

      if (token && permission) {
        localStorage.setItem("seller_jwt", token);
       localStorage.setItem("seller_role", permission[0]);
      
        
      }
      return response.data;
    } catch (error) {
      return  error.response?.data || {
        code:-1,
         message: "Login failed" };
    }
  },
  getToken() {
    return localStorage.getItem("seller_jwt");
  },

  getRole() {
    return localStorage.getItem("seller_role");
  },

  isAuthenticated() {
    return !!localStorage.getItem("seller_jwt");
  },
};
