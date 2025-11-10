import api from "./api";


export default {
  async login(sellerData) {
    try {
      const response = await api.post("/auth/seller/login", sellerData);
      const {token,permission} = response.data.data;

      if (token && permission) {
        sessionStorage.setItem("seller_jwt", token);
       sessionStorage.setItem("seller_role", permission[0]);
      
        
      }
      return response.data;
    } catch (error) {
      return  error.response?.data || {
        code:-1,
         message: "Login failed" };
    }
  },

   async googleLogin(idToken){
      try{
        const response= await api.post("/seller/google/login",{
          token: idToken,
          clientId: "819481703907-espu7bdv7nntjvn3jn0lvjtl1ncpleru.apps.googleusercontent.com"
  
        });
        const {token,permission}= response.data.data;
  
        if(token && permission){
          const cleanToken = token.replace(/[\s\u0000-\u001F]+/g, "");
          sessionStorage.setItem("seller_jwt", cleanToken);
          sessionStorage.setItem("seller_role", permission);
        }
        return response.data;
  
      }catch(error){
        console.error("Google login failed:", error);
        return (
          error.response?.data || {
            code: "-1",
            message: "Google login failed. Retry",
          }
        );
  
      }
    },
  getToken() {
    return sessionStorage.getItem("seller_jwt");
  },

  getRole() {
    return sessionStorage.getItem("seller_role");
  },

  isAuthenticated() {
    return !!sessionStorage.getItem("seller_jwt");
  },

 // In your sellerAuthService.js
async logout() {
  const token = this.getToken();
  
  // Always clear local storage first to prevent race conditions
  const savedToken = token; // Save token before removing
  sessionStorage.removeItem("seller_jwt");
  sessionStorage.removeItem("seller_role");

  try {
    if (savedToken) {
      // Call backend logout with the saved token
      const response = await api.post("/auth/seller/logout", {}, {
        headers: {
          Authorization: savedToken
        },
        timeout: 5000 // 5 second timeout
      });
      
      return {
        code: 200,
        message: response.data?.message || "Logged out successfully",
        data: response.data
      };
    }
    
    return { code: 200, message: "Logged out successfully" };
    
  } catch (error) {
    // Don't throw error here - logout should always succeed locally
    console.warn("Backend logout failed, but local logout completed:", error);
    
    return {
      code: error.response?.status || -1,
      message: "Logged out locally (backend call failed)",
      error: error.response?.data
    };
  }
}
};
