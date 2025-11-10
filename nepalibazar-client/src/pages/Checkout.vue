<template>
  <div class="min-h-screen bg-white">
    <Hero />
    <Navbar />

    <!-- Checkout Progress -->
    <CheckoutProgress :currentStep="currentStep" />

    <!-- Custom Notification -->
    <CustomNotification
      :show="showNotification"
      :message="notificationMessage"
      :type="notificationType"
      @hide="hideNotification"
    />

    <div class="container mx-auto px-4 py-8 max-w-6xl">
      <!-- Empty Cart State -->
      <EmptyCartState
        v-if="cartItems.length === 0 && !isLoading"
        @continue-shopping="$router.push('/products')"
      />

      <!-- Checkout Steps -->
      <div v-else-if="cartItems.length > 0">
        <!-- Step 1: Shipping Information -->
        <ShippingStep
          v-if="currentStep === 1"
          :shippingData="shippingData"
          :errors="errors"
          :isLoading="isLoading"
          @update:shippingData="updateShippingData"
          @validate="validateShipping"
          @back="$router.push('/cart')"
        />

        <!-- Step 2: Payment Method -->
        <PaymentStep
          v-if="currentStep === 2"
          :paymentMethod="paymentMethod"
          :errors="errors"
          :isLoading="isLoading"
          :cartItems="cartItems"
          :totalMrpPrice="totalMrpPrice"
          :totalSellingPrice="totalSellingPrice"
          :shippingData="shippingData"
          @select-payment="selectPaymentMethod"
          @validate="validatePayment"
          @back="currentStep = 1"
        />

        <!-- Step 3: Order Confirmation -->
        <ConfirmationStep
          v-if="currentStep === 3"
          :orderId="orderId"
          :paymentMethod="paymentMethod"
          :totalSellingPrice="totalSellingPrice"
          @view-orders="viewOrderHistory"
          @continue-shopping="continueShopping"
        />
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";
import Footer from "../components/ui/Footer.vue";
import CartService from "../services/CartService";
import OrderService from "../services/OrderService";
import PaymentService from "../services/PaymentService";

import CheckoutProgress from "../components/checkout/CheckoutProgress.vue";
import CustomNotification from "../components/ui/CustomNotification.vue";
import EmptyCartState from "../components/checkout/EmptyCartState.vue";
import ShippingStep from "../components/checkout/ShippingStep.vue";
import PaymentStep from "../components/checkout/PaymentStep.vue";
import ConfirmationStep from "../components/checkout/ConfirmationStep.vue";

export default {
  name: "CheckoutPage",
  components: {
    Hero,
    Navbar,
    Footer,
    CheckoutProgress,
    CustomNotification,
    EmptyCartState,
    ShippingStep,
    PaymentStep,
    ConfirmationStep
  },
  data() {
    return {
      currentStep: 1,
      isLoading: false,
      cartItems: [],
      totalMrpPrice: 0,
      totalSellingPrice: 0,
      shippingData: {
        country: '',
        city: '',
        state: '',
        street: '',
        pinCode: '',
        phoneNumber: ''
      },
      paymentMethod: null,
      orderId: null,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success',
      notificationTimeout: null,
      errors: {
        paymentMethod: '',
        country: '',
        city: '',
        state: '',
        street: '',
        pinCode: '',
        phoneNumber: ''
      }
    };
  },
  async mounted() {
    await this.fetchCart();
  },
  methods: {
    // ------------------ Notification ------------------
    showCustomNotification(message, type = 'success') {
      console.log(`Notification: ${message}, Type: ${type}`);
      clearTimeout(this.notificationTimeout);
      this.notificationMessage = message;
      this.notificationType = type;
      this.showNotification = true;

      this.notificationTimeout = setTimeout(() => {
        this.hideNotification();
      }, 4000);
    },
    hideNotification() {
      this.showNotification = false;
      clearTimeout(this.notificationTimeout);
    },

    // ------------------ Errors ------------------
    clearErrors() {
      for (const key in this.errors) {
        if (Object.hasOwnProperty.call(this.errors, key)) {
          this.errors[key] = '';
        }
      }
    },

    // ------------------ Shipping ------------------
    updateShippingData(field, value) {
      this.shippingData[field] = value;
    },

    validateField(field, value) {
      this.errors[field] = '';

      if (!value || value.trim() === '') {
        this.errors[field] = `${this.formatFieldName(field)} is required`;
        return false;
      }

      switch (field) {
        case 'phoneNumber':
          const phoneRegex = /^[0-9]{10}$/;
          const cleanPhone = value.replace(/\D/g, '');
          if (!phoneRegex.test(cleanPhone)) {
            this.errors.phoneNumber = 'Please enter a valid 10-digit phone number';
            return false;
          }
          break;
        case 'pinCode':
          const pinRegex = /^[0-9]{6}$/;
          if (!pinRegex.test(value)) {
            this.errors.pinCode = 'Please enter a valid 6-digit pin code';
            return false;
          }
          break;
        case 'country':
        case 'city':
        case 'state':
          if (value.length < 2) {
            this.errors[field] = `${this.formatFieldName(field)} must be at least 2 characters`;
            return false;
          }
          break;
        case 'street':
          if (value.length < 5) {
            this.errors.street = 'Address must be at least 5 characters';
            return false;
          }
          break;
      }
      return true;
    },

    formatFieldName(field) {
      return field.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase());
    },

    validateShipping() {
      this.clearErrors();
      let isValid = true;
      const fields = ['country', 'city', 'state', 'street', 'pinCode', 'phoneNumber'];
      fields.forEach(field => {
        if (!this.validateField(field, this.shippingData[field])) {
          isValid = false;
        }
      });

      if (!isValid) {
        this.showCustomNotification('Please fix the errors in the form', 'error');
        return;
      }
      this.currentStep = 2;
    },

    // ------------------ Cart ------------------
    async fetchCart() {
      this.isLoading = true;
      try {
        const result = await CartService.getCartItems();
        if ((result.code === "0" || result.code === 0) && result.data) {
          const cartData = result.data;
          this.cartItems = cartData.cartItems || [];
          this.totalMrpPrice = cartData.totalMrpPrice || 0;
          this.totalSellingPrice = cartData.totalSellingPrice || 0;

          if (this.cartItems.length === 0) {
            this.showCustomNotification("Your cart is empty. Please add items before checkout.", 'error');
          }
        } else {
          this.showCustomNotification(result.message || "Failed to load cart items", 'error');
        }
      } catch (error) {
        console.error("Fetch cart error:", error);
        this.showCustomNotification("Failed to load cart", 'error');
      } finally {
        this.isLoading = false;
      }
    },

    // ------------------ Payment ------------------
    selectPaymentMethod(method) {
      this.paymentMethod = method;
      this.errors.paymentMethod = '';
    },

    validatePayment() {
      this.clearErrors();
      if (!this.paymentMethod) {
        this.errors.paymentMethod = 'Please select a payment method';
        this.showCustomNotification('Please select a payment method', 'error');
        return;
      }
      this.processPayment();
    },

    async processPayment() {
      console.log("Starting payment process...");
      
      this.isLoading = true;
      
      try {
        const orderData = {
          paymentMethod: this.paymentMethod,
          country: this.shippingData.country,
          city: this.shippingData.city,
          state: this.shippingData.state,
          street: this.shippingData.street,
          pinCode: this.shippingData.pinCode,
          phoneNumber: this.shippingData.phoneNumber
        };

        console.log("Placing order:", orderData);

        // STEP 1: Place Order
        const placeOrderResult = await OrderService.placeOrder(orderData);
        console.log("Place order result:", placeOrderResult);

        // Check if order placement was successful
        if (!placeOrderResult || !placeOrderResult.success) {
          const errorMsg = placeOrderResult?.message || 'Failed to place order';
          this.showCustomNotification(errorMsg, 'error');
          return;
        }

        // Extract order ID from the response
        this.orderId = placeOrderResult.orderId || placeOrderResult.data?.orderId;
        
        if (!this.orderId) {
          console.error("No order ID in response");
          this.showCustomNotification('Order placed but no order ID received', 'error');
          return;
        }

        console.log("Order placed successfully, Order ID:", this.orderId);

        // STEP 2: Handle payment method
        if (this.paymentMethod === 'CASH') {
          console.log("Processing Cash on Delivery...");
          
          const codResult = await PaymentService.confirmCodOrder(this.orderId);
          console.log("COD result:", codResult);

          if (codResult && codResult.success) {
            // SUCCESS: Move to confirmation step
            this.currentStep = 3;
            this.showCustomNotification("Order placed successfully with Cash on Delivery!", 'success');
            
            // Clear cart after successful order
            try {
              await CartService.clearCart();
              console.log("Cart cleared successfully");
            } catch (cartError) {
              console.warn("Could not clear cart:", cartError);
            }
          } else {
            const errorMsg = codResult?.message || 'Cash on Delivery confirmation failed';
            this.showCustomNotification(errorMsg, 'error');
          }

        } else if (this.paymentMethod === 'STRIPE') {
          console.log("Processing Stripe payment...");
          
          const successUrl = `${window.location.origin}/order-success?orderId=${this.orderId}`;
          const cancelUrl = `${window.location.origin}/checkout?step=2`;

          const stripeResult = await PaymentService.createStripeCheckout(
            this.orderId,
            successUrl,
            cancelUrl
          );

          if (stripeResult && stripeResult.success) {
            const sessionUrl = stripeResult.sessionUrl;
            if (sessionUrl) {
              window.location.href = sessionUrl;
            } else {
              this.showCustomNotification("Stripe session URL not found", 'error');
            }
          } else {
            const errorMsg = stripeResult?.message || "Stripe checkout failed";
            this.showCustomNotification(errorMsg, 'error');
          }
        }

      } catch (error) {
        console.error("Payment processing error:", error);
        this.showCustomNotification(
          error.response?.data?.message || 
          error.message || 
          "Failed to process payment. Please try again.", 
          'error'
        );
      } finally {
        this.isLoading = false;
      }
    },

    // ------------------ Navigation ------------------
    viewOrderHistory() {
      this.$router.push('/orders');
    },
    continueShopping() {
      this.$router.push('/products');
    }
  }
};
</script>

<style scoped>
/* Optional: Add some padding for empty cart */
</style>