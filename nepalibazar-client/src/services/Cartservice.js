import api from "./api";

const CartService = {
  async addToCart(productId, quantity = 1) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { success: false, message: "Only buyers can add products to cart." };
      }

      const cleanToken = token.trim();
      console.log("Adding to cart:", { productId, quantity });

      const response = await api.post(
        "/cart/add",
        { 
          productId: productId,
          quantity: quantity 
        },
        { headers: { Authorization: `Bearer ${cleanToken}` } }
      );

      console.log("Add to cart response:", response.data);
      const res = response.data;

      if (res.code === "0" && res.data) {
        if (res.data.code === 0) {
          return { 
            success: true, 
            message: res.data.message || `Added ${quantity} item(s) to cart successfully!`
          };
        } else {
          return { 
            success: false, 
            message: res.data.message || "Could not update cart." 
          };
        }
      } else if (res.message) {
        return { success: false, message: res.message };
      }

      return { success: false, message: "Could not update cart." };
    } catch (error) {
      console.error("Cart API Error:", error);

      const backendMessage = error.response?.data?.message?.toLowerCase() || "";
      const backendData = error.response?.data?.data;

      // Friendly session expired message
      if (backendMessage.includes("expired")) {
        return { success: false, message: "Your session has expired. Please login again." };
      }

      // Stock validation messages
      if (backendMessage.includes("only") && backendMessage.includes("available")) {
        return { success: false, message: error.response.data.message };
      }

      if (backendMessage.includes("invalid quantity")) {
        return { success: false, message: "Please enter a valid quantity." };
      }

      // Check if error is in data object
      if (backendData?.message) {
        return { success: false, message: backendData.message };
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
        return { 
          code: "-1", 
          message: "Authentication required",
          data: null 
        };
      }

      const cleanToken = token.trim();
      console.log("Fetching cart items...");

      const response = await api.get("/mycart/items", {
        headers: { Authorization: `Bearer ${cleanToken}` },
      });

      console.log("Cart items response:", response.data);
      const res = response.data;
      
      // Return the full response
      return res;
    } catch (error) {
      console.error("Cart fetch error:", error);
      
      const backendMessage = error.response?.data?.message?.toLowerCase() || "";
      if (backendMessage.includes("expired")) {
        return { 
          code: "-1", 
          message: "Your session has expired. Please login again.",
          data: null 
        };
      }
      
      if (error.response?.status === 401) {
        return { 
          code: "-1", 
          message: "Please login to view your cart.",
          data: null 
        };
      }
      
      if (error.response?.status === 404) {
        return { 
          code: "0", 
          message: "Cart is empty",
          data: { cartItems: [], totalItems: 0, totalMrpPrice: 0, totalSellingPrice: 0 }
        };
      }
      
      return { 
        code: "-1", 
        message: "Failed to load cart items. Please try again.",
        data: null 
      };
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
      console.log("Clearing cart...");

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

      if (res.code === "0") {
        return { 
          success: true, 
          message: res.data?.message || res.message || "Cart cleared successfully",
          data: res.data
        };
      } else if (res.data && res.data.code === 0) {
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
      const backendData = error.response?.data?.data;
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
      
      if (backendData?.message) {
        return { 
          success: false, 
          message: backendData.message 
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

  // Helper method to get cart summary (item count, total price)
  async getCartSummary() {
    try {
      const cartData = await this.getCartItems();
      
      if (cartData.code === "0" && cartData.data) {
        const items = cartData.data.cartItems || cartData.data.cartItemEntities || [];
        const totalItems = items.reduce((sum, item) => sum + (item.quantity || 1), 0);
        const totalPrice = items.reduce((sum, item) => sum + ((item.price || 0) * (item.quantity || 1)), 0);
        
        return {
          success: true,
          totalItems: totalItems,
          totalPrice: totalPrice,
          itemCount: items.length,
          hasItems: items.length > 0
        };
      } else {
        return {
          success: false,
          totalItems: 0,
          totalPrice: 0,
          itemCount: 0,
          hasItems: false
        };
      }
    } catch (error) {
      console.error("Get cart summary error:", error);
      return {
        success: false,
        totalItems: 0,
        totalPrice: 0,
        itemCount: 0,
        hasItems: false
      };
    }
  },
  
  async updateCartItem(cartItemId, quantity) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { 
          success: false, 
          message: "Authentication required. Please login as a buyer.",
          code: "-1"
        };
      }

      const cleanToken = token.trim();
      
      console.log("Updating cart item:", { cartItemId, quantity });
      
      const response = await api.put(
        "/cart/update",
        { 
          cartItemId: cartItemId,
          quantity: quantity 
        },
        { 
          headers: { 
            Authorization: `Bearer ${cleanToken}`,
            'Content-Type': 'application/json'
          } 
        }
      );

      console.log("Update cart response:", response.data);
      const res = response.data;

      // Handle different response structures
      if (res.code === "0") {
        return { 
          success: true, 
          message: res.data?.message || res.message || "Quantity updated successfully!",
          data: res.data,
          code: "0"
        };
      } else if (res.data && res.data.code === 0) {
        return { 
          success: true, 
          message: res.data.message || "Quantity updated successfully!",
          data: res.data,
          code: "0"
        };
      } else {
        const errorMessage = res.data?.message || res.message || "Failed to update quantity";
        return { 
          success: false, 
          message: errorMessage,
          code: res.data?.code || res.code || "-1"
        };
      }
    } catch (error) {
      console.error("Update cart item error:", error);
      
      const backendMessage = error.response?.data?.message || "";
      const backendData = error.response?.data?.data;
      const backendCode = error.response?.data?.code;
      
      if (error.response?.status === 401) {
        return { 
          success: false, 
          message: "Your session has expired. Please login again.",
          code: "-1"
        };
      }
      
      if (error.response?.status === 404) {
        return { 
          success: false, 
          message: "Cart item not found. Please refresh your cart.",
          code: "-1"
        };
      }
      
      if (error.response?.status === 400) {
        return { 
          success: false, 
          message: backendMessage || "Invalid request. Please check the quantity.",
          code: backendCode || "-1"
        };
      }
      
      // Check for stock errors
      if (backendMessage.includes("only") && backendMessage.includes("available")) {
        return { 
          success: false, 
          message: backendMessage,
          code: backendCode || "-1"
        };
      }
      
      if (backendData?.message) {
        return { 
          success: false, 
          message: backendData.message,
          code: backendData.code || "-1"
        };
      }
      
      return { 
        success: false, 
        message: "Failed to update quantity. Please try again.",
        code: "-1"
      };
    }
  }

};

export default CartService;