

import api from "./api";

export default {
 
  async login(adminData) {
    try {
     
      const response = await api.post("/login", adminData);
      const { token, permission, userName, successAuthenticate } = response.data.data;

      if (token && permission && userName) {
        const cleanToken = token.replace(/[\s\u0000-\u001F]+/g, "");

        localStorage.setItem("admin_jwt", cleanToken);
        localStorage.setItem("admin_role", permission);
        localStorage.setItem("admin_name", userName);
        localStorage.setItem("admin_permission", permission);
        
      
        if (adminData.rememberMe) {
          localStorage.setItem("adminRememberMe", "true");
          localStorage.setItem("adminUserName", userName);
        } else {
          localStorage.removeItem("adminRememberMe");
          localStorage.removeItem("adminUserName");
        }
      }

      return response.data;
    } catch (error) {
      return (
        error.response?.data || {
          code: "-1",
          message: "Login failed. Please check your credentials",
        }
      );
    }
  },


  isTokenExpired() {
    const token = localStorage.getItem("admin_jwt");
    if (!token) return true;

    try {
      const payload = JSON.parse(atob(token.split(".")[1]));
      const expiryTime = payload.exp * 1000;
      return Date.now() > expiryTime;
    } catch (error) {
      console.error("Invalid token:", error);
      return true;
    }
  },

 
  isAuthenticated() {
    if (this.isTokenExpired()) {
      this.logout();
      return false;
    }
    return !!localStorage.getItem("admin_jwt");
  },

 
  getToken() {
    return localStorage.getItem("admin_jwt");
  },

  getRole() {
    return localStorage.getItem("admin_role");
  },

  getUserName() {
    return localStorage.getItem("admin_name");
  },

  getPermission() {
    return localStorage.getItem("admin_permission");
  },
  getRememberedUser() {
    if (localStorage.getItem("adminRememberMe") === "true") {
      return localStorage.getItem("adminUserName") || "";
    }
    return "";
  },


//   logout() {
//     localStorage.removeItem("admin_jwt");
//     localStorage.removeItem("admin_role");
//     localStorage.removeItem("admin_name");
//     localStorage.removeItem("admin_permission");
    
//   },

  logout: async function () {
  try {
    const token = localStorage.getItem("admin_jwt");

    if (token) {
      await api.post(
        "/logout",
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
    }
  } catch (error) {
    console.error("Logout API failed:", error.response?.data || error.message);
  } finally {
    // Always clear localStorage even if API fails
    localStorage.removeItem("admin_jwt");
    localStorage.removeItem("admin_role");
    localStorage.removeItem("admin_name");
    localStorage.removeItem("admin_permission");
    localStorage.removeItem("adminRememberMe");
    localStorage.removeItem("adminUserName");
  }
},



};