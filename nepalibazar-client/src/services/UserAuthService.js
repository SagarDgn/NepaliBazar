import api from "./api";

export default {
  async login(userData) {
    try {
      const response = await api.post("/auth/user/login", userData);
      const { token, permission } = response.data.data;

      if (token && permission) {
        

         const cleanToken = token.replace(/[\s\u0000-\u001F]+/g, "");
        localStorage.setItem("buyer_jwt", cleanToken);
        localStorage.setItem("buyer_role", permission);
      }

      return response.data;
    } catch (error) {
      return error.response?.data || {
        code:"-1",
         message: "Login failed.Retry" };
    }
  },

  async googleLogin(idToken){
    try{
      const response= await api.post("/user/google/login",{
        token: idToken,
        clientId: "819481703907-espu7bdv7nntjvn3jn0lvjtl1ncpleru.apps.googleusercontent.com"

      });
      const {token,permission}= response.data.data;

      if(token && permission){
        const cleanToken = token.replace(/[\s\u0000-\u001F]+/g, "");
        localStorage.setItem("buyer_jwt", cleanToken);
        localStorage.setItem("buyer_role", permission);
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
    return localStorage.getItem("buyer_jwt");
  },
  getRole() {
    return localStorage.getItem("buyer_role");
  },

  isAuthenticated() {
    return !!localStorage.getItem("buyer_jwt");
  },
  logout(){
    localStorage.removeItem("buyer_jwt");
    localStorage.removeItem("buyer_role");
  },

};
