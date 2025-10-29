// services/PaymentService.js

import api from './api';

const PaymentService = {
  // Cash on Delivery confirmation
  async confirmCodOrder(orderId) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { 
          success: false, 
          message: "Only buyers can confirm orders." 
        };
      }

      const cleanToken = token.trim();
      
      const requestBody = {
        orderId: parseInt(orderId)
      };

      console.log("🔐 Calling COD endpoint: /checkout/cod");
      console.log("Request body:", requestBody);

      const response = await api.post(
        "/checkout/cod",
        { 
          headers: { 
            Authorization: `Bearer ${cleanToken}`,
            'Content-Type': 'application/json'
          } 
        },
        requestBody
      );

      console.log("📨 COD endpoint response:", response.data);
      const res = response.data;

      // Handle the nested response structure
      if (res.code === "0" && res.data) {
        if (typeof res.data === 'object' && res.data.code !== undefined) {
          const nestedResponse = res.data;
          if (nestedResponse.code === 0) {
            return { 
              success: true,
              message: nestedResponse.message || "Order confirmed successfully",
              data: nestedResponse
            };
          } else {
            return { 
              success: false, 
              message: nestedResponse.message || "Failed to confirm order." 
            };
          }
        } else {
          return { 
            success: true,
            message: res.message || "Order confirmed successfully",
            data: res.data
          };
        }
      } else {
        return { 
          success: false, 
          message: res.message || "Failed to confirm order." 
        };
      }
    } catch (error) {
      console.error("❌ COD confirmation error:", error);
      console.error("Error response:", error.response?.data);
      console.error("Error status:", error.response?.status);

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
          message: "You don't have permission to confirm orders." 
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
        message: "Failed to confirm COD order. Please try again." 
      };
    }
  },

  // Stripe checkout - Use the correct endpoint once you create the controller
  async createStripeCheckout(orderId, successUrl, cancelUrl) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { 
          success: false, 
          message: "Only buyers can create payment sessions." 
        };
      }

      const cleanToken = token.trim();
      
      const requestBody = {
        orderId: parseInt(orderId),
        successUrl: successUrl,
        cancelUrl: cancelUrl
      };

      console.log("💳 Calling Stripe checkout endpoint: /stripe/checkout");
      console.log("Request body:", requestBody);

      const response = await api.post(
        "/stripe/checkout", // This endpoint needs to be created in your backend
        requestBody,
         { 
          headers: { 
            Authorization: `Bearer ${cleanToken}`,
            'Content-Type': 'application/json'
          } 
        }
      );

      console.log("Stripe checkout response:", response.data);
      const res = response.data;

      if (res.code === "0" && res.data) {
        // Handle nested response
        if (typeof res.data === 'object' && res.data.code !== undefined) {
          const nestedResponse = res.data;
          if (nestedResponse.code === 0 && nestedResponse.sessionUrl) {
            return { 
              success: true,
              message: nestedResponse.message || "Stripe session created successfully",
              sessionUrl: nestedResponse.sessionUrl,
              data: nestedResponse
            };
          }
        }
        return { 
          success: true,
          message: res.message || "Stripe session created successfully",
          sessionUrl: res.data,
          data: res.data
        };
      } else {
        return { 
          success: false, 
          message: res.message || "Failed to create payment session." 
        };
      }
    } catch (error) {
      console.error("Stripe checkout error:", error);
      console.error("Error details:", error.response?.data);

      // If the endpoint doesn't exist yet, return a helpful message
      if (error.response?.status === 404) {
        return {
          success: false,
          message: "Stripe checkout endpoint not available yet. Please use Cash on Delivery."
        };
      }

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
          message: "You don't have permission to create payment sessions." 
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
        message: "Failed to create payment session. Please try again." 
      };
    }
  },
  // Process refund (if needed in future)
  async processRefund(orderId, amount) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { 
          success: false, 
          message: "Only buyers can process refunds." 
        };
      }

      const cleanToken = token.trim();
      
      const requestBody = {
        orderId: parseInt(orderId),
        amount: amount
      };

      console.log("Process refund request:", requestBody);

      // This endpoint would need to be created in your backend
      const response = await api.post(
        "/api/v1/payment/refund",
        requestBody,
        { 
          headers: { 
            Authorization: `Bearer ${cleanToken}`,
            'Content-Type': 'application/json'
          } 
        }
      );

      console.log("Process refund response:", response.data);
      const res = response.data;

      if (res.code === 0) {
        return { 
          success: true, 
          message: res.message || "Refund processed successfully",
          data: res.data
        };
      } else {
        return { 
          success: false, 
          message: res.message || "Failed to process refund." 
        };
      }
    } catch (error) {
      console.error("Process refund error:", error);
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
          message: "You don't have permission to process refunds." 
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
        message: "Failed to process refund. Please try again." 
      };
    }
  },

  // Get payment status
  async getPaymentStatus(orderId) {
    try {
      const token = localStorage.getItem("buyer_jwt");
      const role = localStorage.getItem("buyer_role");

      if (!token || role !== "BUYER") {
        return { 
          success: false, 
          message: "Only buyers can view payment status." 
        };
      }

      const cleanToken = token.trim();
      
      const response = await api.get(
        `/api/v1/payment/status/${orderId}`,
        { 
          headers: { 
            Authorization: `Bearer ${cleanToken}`,
            'Content-Type': 'application/json'
          } 
        }
      );

      console.log("Get payment status response:", response.data);
      const res = response.data;

      if (res.code === 0 && res.data) {
        return { 
          success: true, 
          message: res.message || "Payment status retrieved successfully",
          paymentStatus: res.data,
          data: res.data
        };
      } else {
        return { 
          success: false, 
          message: res.message || "Failed to retrieve payment status." 
        };
      }
    } catch (error) {
      console.error("Get payment status error:", error);
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
          message: "You don't have permission to view payment status." 
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
        message: "Failed to retrieve payment status. Please try again." 
      };
    }
  }
};

export default PaymentService;