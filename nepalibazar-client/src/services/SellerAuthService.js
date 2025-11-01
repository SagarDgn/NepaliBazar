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

 // In your sellerAuthService.js
async logout() {
  const token = this.getToken();
  
  // Always clear local storage first to prevent race conditions
  const savedToken = token; // Save token before removing
  localStorage.removeItem("seller_jwt");
  localStorage.removeItem("seller_role");

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
