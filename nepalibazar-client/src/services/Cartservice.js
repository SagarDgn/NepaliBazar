import api from "./api";

export default {
  async addToCart(productId) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { success: false, message: "Only buyers can add products to cart." };
      }

      const cleanToken = token.trim();
      console.log("Jwt", cleanToken);

      const response = await api.post(
        "/cart/add",
        { productId },
        { headers: { Authorization: `Bearer ${cleanToken}` } }
      );

      console.log("Sending token...", cleanToken);
      const res = response.data;

      if (res.code === "0" && res.data) {
        if (res.data.code === 0) {
          return { success: true, message: res.data.message }; // Product added/removed
        } else {
          return { success: false, message: "Could not update cart." };
        }
      }

      return { success: false, message: "Could not update cart." };
    } catch (error) {
      console.error("Cart API Error:", error);

      const backendMessage = error.response?.data?.message?.toLowerCase() || "";

      // Friendly session expired message
      if (backendMessage.includes("expired")) {
        return { success: false, message: "Your session has expired. Please login again." };
      }

      // Generic error for all other issues
      return { success: false, message: "Something went wrong. Please try again." };
    }
  },

  async getCartItems() {
  try {
    const token = localStorage.getItem("buyer_jwt");
    const role = localStorage.getItem("buyer_role");

    if (!token || role !== "BUYER") {
      return { code: "-1", message: "Authentication required" };
    }

    const cleanToken = token.trim();
    const response = await api.get("/mycart/items", {
      headers: { Authorization: `Bearer ${cleanToken}` },
    });

    const res = response.data;
    
    // Return the full response so fetchCart can access code, message, and data
    return res;
  } catch (error) {
    console.error("Cart fetch error:", error);
    
    const backendMessage = error.response?.data?.message?.toLowerCase() || "";
    if (backendMessage.includes("expired")) {
      return { code: "-1", message: "Your session has expired. Please login again." };
    }
    
    return { code: "-1", message: "Something went wrong. Please try again." };
  }
},

async removeCartItem(productId) {
  try {
    const token = localStorage.getItem("buyer_jwt");
    const role = localStorage.getItem("buyer_role");

    if (!token || role !== "BUYER") {
      return { 
        success: false, 
        message: "Only buyers can remove items from cart." 
      };
    }

    const cleanToken = token.trim();
    
    // Correct request body format - object with productId array
    const requestBody = {
      productId: [parseInt(productId)] // Ensure it's an array of numbers
    };

    console.log("Remove item request body:", requestBody);

    const response = await api.put(
      "/cart/remove/item",
      requestBody,
      { 
        headers: { 
          Authorization: `Bearer ${cleanToken}`,
          'Content-Type': 'application/json'
        } 
      }
    );

    console.log("Remove item response:", response.data);
    const res = response.data;

    if (res.code === "0" && res.data) {
      return { 
        success: true, 
        message: res.data.message || "Item removed from cart successfully",
        data: res.data
      };
    } else {
      return { 
        success: false, 
        message: res.message || "Failed to remove item from cart." 
      };
    }
  } catch (error) {
    console.error("Remove cart item error:", error);
    console.error("Error details:", error.response?.data);

    const backendMessage = error.response?.data?.message;
    const statusCode = error.response?.status;

    if (statusCode === 401) {
      return { 
        success: false, 
        message: "Your session has expired. Please login again." 
      };
    }
    if (statusCode === 403) {
      return { 
        success: false, 
        message: "You don't have permission to remove cart items." 
      };
    }
    if (backendMessage) {
      return { 
        success: false, 
        message: backendMessage 
      };
    }

    return { 
      success: false, 
      message: "Failed to remove item from cart. Please try again." 
    };
  }
},

    async clearCart() {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { 
          success: false, 
          message: "Only buyers can clear their cart." 
        };
      }

      const cleanToken = token.trim();

      const response = await api.delete(
        "/cart/clear",
        { 
          headers: { 
            Authorization: `Bearer ${cleanToken}`
          } 
        }
      );

      console.log("Clear cart response:", response.data);
      const res = response.data;

      if (res.code === "0" && res.data) {
        return { 
          success: true, 
          message: res.data.message || "Cart cleared successfully",
          data: res.data
        };
      } else {
        return { 
          success: false, 
          message: res.message || "Failed to clear cart." 
        };
      }
    } catch (error) {
      console.error("Clear cart error:", error);

      const backendMessage = error.response?.data?.message;
      const statusCode = error.response?.status;

      // Handle specific error cases
      if (statusCode === 401) {
        return { 
          success: false, 
          message: "Your session has expired. Please login again." 
        };
      }
      if (statusCode === 403) {
        return { 
          success: false, 
          message: "You don't have permission to clear the cart." 
        };
      }
      if (backendMessage) {
        return { 
          success: false, 
          message: backendMessage 
        };
      }

      return { 
        success: false, 
        message: "Failed to clear cart. Please try again." 
      };
    }
  },
};


