import api from "./api";

export default {
  // -----------------------------
  // LOGIN
  // -----------------------------
  async login(userData) {
    try {
      const response = await api.post("/auth/user/login", userData);
      const { token, permission, userName } = response.data.data;

      if (token && permission && userName) {
        const cleanToken = token.replace(/[\s\u0000-\u001F]+/g, "");

        localStorage.setItem("buyer_jwt", cleanToken);
        localStorage.setItem("buyer_role", permission);
        localStorage.setItem("buyer_name", userName);
      }

      return response.data;
    } catch (error) {
      return (
        error.response?.data || {
          code: "-1",
          message: "Login failed. Retry",
        }
      );
    }
  },

  // -----------------------------
  // GOOGLE LOGIN
  // -----------------------------
  async googleLogin(idToken) {
    try {
      const response = await api.post("/user/google/login", {
        token: idToken,
        clientId:
          "819481703907-espu7bdv7nntjvn3jn0lvjtl1ncpleru.apps.googleusercontent.com",
      });

      const { token, permission, userName } = response.data.data;

      if (token && permission && userName) {
        const cleanToken = token.replace(/[\s\u0000-\u001F]+/g, "");

        localStorage.setItem("buyer_jwt", cleanToken);
        localStorage.setItem("buyer_role", permission);
        localStorage.setItem("buyer_name", userName);
      }

      return response.data;
    } catch (error) {
      console.error("Google login failed:", error);
      return (
        error.response?.data || {
          code: "-1",
          message: "Google login failed. Retry",
        }
      );
    }
  },

  // -----------------------------
  // CHECK TOKEN EXPIRY
  // -----------------------------
  isTokenExpired() {
    const token = localStorage.getItem("buyer_jwt");
    if (!token) return true;

    try {
      const payload = JSON.parse(atob(token.split(".")[1])); // decode JWT
      const expiryTime = payload.exp * 1000; // JWT exp is in seconds

      return Date.now() > expiryTime; // true if expired
    } catch (error) {
      console.error("Invalid token:", error);
      return true;
    }
  },

  // -----------------------------
  // AUTH CHECK
  // -----------------------------
  isAuthenticated() {
    if (this.isTokenExpired()) {
      this.logout();
      return false;
    }
    return true;
  },

  // -----------------------------
  // GETTERS
  // -----------------------------
  getToken() {
    return localStorage.getItem("buyer_jwt");
  },

  getRole() {
    return localStorage.getItem("buyer_role");
  },

  // -----------------------------
  // LOGOUT
  // -----------------------------
  logout() {
    localStorage.removeItem("buyer_jwt");
    localStorage.removeItem("buyer_role");
    localStorage.removeItem("buyer_name");
  },
};
