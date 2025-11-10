// services/OrderService.js
import api from './api';

const OrderService = {
  async placeOrder(orderData) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { success: false, message: "Only buyers can place orders." };
      }

      const cleanToken = token.trim();

      const requestBody = {
        paymentMethod: orderData.paymentMethod,
        country: orderData.country,
        city: orderData.city,
        state: orderData.state,
        street: orderData.street,
        pinCode: orderData.pinCode,
        phoneNumber: orderData.phoneNumber
      };

      const response = await api.post(
        "/place/order",
        requestBody,
        {
          headers: { Authorization: `Bearer ${cleanToken}`, 'Content-Type': 'application/json' }
        }
      );

      const res = response.data;

      if ((res.code === "0" || res.code === 0) && res.data && (res.data.code === 0 || res.data.code === "0")) {
        return {
          success: true,
          message: res.data.message || "Order placed successfully",
          orderId: res.data.orderId,
          data: res.data
        };
      }

      return {
        success: false,
        message: res.message || "Failed to place order."
      };

    } catch (error) {
      console.error("Place order error:", error);
      return { success: false, message: "Failed to place order. Please try again." };
    }
  },

  async cancelOrder(orderId) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { success: false, message: "Only buyers can cancel orders." };
      }

      const cleanToken = token.trim();

      const response = await api.post(
        "/order/cancel",
        { orderId: parseInt(orderId) },
        { headers: { Authorization: `Bearer ${cleanToken}` } }
      );

      const res = response.data;

      if (res.code === 0) {
        return { success: true, message: res.message || "Order cancelled successfully" };
      }

      return { success: false, message: res.message || "Failed to cancel order." };

    } catch (error) {
      console.error("Cancel order error:", error);
      return { success: false, message: "Failed to cancel order. Please try again." };
    }
  },

  async getUserOrders() {
  try {
    const token = localStorage.getItem("buyer_jwt");
    const role = localStorage.getItem("buyer_role");

    if (!token || role !== "BUYER") {
      return { success: false, message: "Authentication required" };
    }

    const cleanToken = token.trim();

    const response = await api.get("/orders", {
      headers: { Authorization: `Bearer ${cleanToken}` }
    });

    const res = response.data;
    
    // Try different possible response structures
    let orders = [];
    
    if (res.data?.orders) {
      orders = res.data.orders;
    } else if (res.orders) {
      orders = res.orders;
    } else if (Array.isArray(res)) {
      orders = res;
    } else if (Array.isArray(res.data)) {
      orders = res.data;
    }
    
    console.log("Final orders array:", orders);
    
    return { 
      success: true, 
      orders: orders,
      message: "Orders fetched successfully"
    };

  } catch (error) {
    console.error("Get user orders error:", error);
    return { 
      success: false, 
      message: "Failed to fetch orders. Please try again.",
      orders: []
    };
  }
}
};

export default OrderService;
