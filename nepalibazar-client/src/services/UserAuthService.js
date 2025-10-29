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

  getToken() {
    return localStorage.getItem("buyer_jwt");
  },
  getRole() {
    return localStorage.getItem("buyer_role");
  },

  isAuthenticated() {
    return !!localStorage.getItem("buyer_jwt");
  },
};
