import api from "./api";

export default {
  async toggleWishlist(productId) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      // Only buyers can add to wishlist
      if (!token || role !== "BUYER") {
        return { success: false, message: "Only buyers can add products to wishlist." };
      }

      const cleanToken = token.trim();

      const response = await api.post(
        "/wishlist/add",
        { productId },
        { headers: { Authorization: `Bearer ${cleanToken}` } }
      );
      
      console.log("Toggle wishlist response:", response.data);
      const res = response.data;

      if (res.code === "0" && res.data) {
        // Use the new boolean field from backend
        return { 
          success: true, 
          message: res.data.message,
         isInWishlist: res.data.isWishlisted ?? res.data.isInWishlisted
// This is the new boolean field
        };
      } else {
        return { 
          success: false, 
          message: res.message || "Could not update wishlist." 
        };
      }
    } catch (error) {
      console.error("Wishlist API Error:", error);

      const backendMessage = error.response?.data?.message?.toLowerCase() || "";

      // Show friendly session expired message
      if (backendMessage.includes("expired")) {
        return { success: false, message: "Your session has expired. Please login again." };
      }

      // Generic error message for all other backend errors
      return { success: false, message: "Something went wrong. Please try again." };
    }
  },

 
  async getWishlistProducts() {
  const token = localStorage.getItem("buyer_jwt");
  if (!token) return [];

  try {
    const response = await api.get("/wishlist", {
      headers: { Authorization: `Bearer ${token}` },
    });

    const res = response.data;
    console.log("Get wishlist response:", res);

    if (res.code === "0" && res.data) {
      const data = res.data.data || res.data;
      return data.wishlist || data.wishlistItems || [];
    }

    return [];
  } catch (error) {
    console.error("Get wishlist error:", error);
    const backendMessage = error.response?.data?.message?.toLowerCase() || "";
    if (backendMessage.includes("expired")) {
      console.warn("Session expired while fetching wishlist");
    }
    return [];
  }
},


};