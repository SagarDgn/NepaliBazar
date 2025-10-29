<template>
  <div class="min-h-screen bg-gray-50">
    <Hero />
    <Navbar />
    
    <!-- Checkout Progress -->
    <div class="bg-white shadow-lg border-b">
      <div class="container mx-auto px-4 py-6">
        <div class="flex items-center justify-center">
          <div class="flex items-center space-x-8">
            <!-- Step 1: Shipping -->
            <div class="flex items-center">
              <div class="flex items-center justify-center w-12 h-12 rounded-full border-2"
                   :class="currentStep >= 1 ? 'bg-red-600 border-red-600 text-white' : 'bg-white border-gray-300 text-gray-500'">
                <span class="text-sm font-bold">1</span>
              </div>
              <span class="ml-3 font-semibold" :class="currentStep >= 1 ? 'text-red-600' : 'text-gray-500'">
                Shipping
              </span>
            </div>
            
            <div class="w-16 h-0.5 bg-gray-300"></div>
            
            <!-- Step 2: Payment -->
            <div class="flex items-center">
              <div class="flex items-center justify-center w-12 h-12 rounded-full border-2"
                   :class="currentStep >= 2 ? 'bg-red-600 border-red-600 text-white' : 'bg-white border-gray-300 text-gray-500'">
                <span class="text-sm font-bold">2</span>
              </div>
              <span class="ml-3 font-semibold" :class="currentStep >= 2 ? 'text-red-600' : 'text-gray-500'">
                Payment
              </span>
            </div>
            
            <div class="w-16 h-0.5 bg-gray-300"></div>
            
            <!-- Step 3: Confirmation -->
            <div class="flex items-center">
              <div class="flex items-center justify-center w-12 h-12 rounded-full border-2"
                   :class="currentStep >= 3 ? 'bg-red-600 border-red-600 text-white' : 'bg-white border-gray-300 text-gray-500'">
                <span class="text-sm font-bold">3</span>
              </div>
              <span class="ml-3 font-semibold" :class="currentStep >= 3 ? 'text-red-600' : 'text-gray-500'">
                Confirmation
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Custom Notification -->
    <div
      v-if="showNotification"
      class="fixed top-4 right-4 z-50 transform transition-all duration-500"
      :class="notificationClass"
    >
      <div class="flex items-center space-x-3 px-6 py-4 rounded-xl shadow-2xl backdrop-blur-sm border" :class="notificationStyle">
        <div class="flex-shrink-0">
          <div class="w-8 h-8 rounded-full flex items-center justify-center animate-pulse" :class="notificationIconStyle">
            <svg v-if="notificationType === 'success'" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
            </svg>
            <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
          </div>
        </div>
        <div class="flex-1 min-w-0">
          <p class="text-sm font-semibold" :class="notificationTextStyle">
            {{ notificationMessage }}
          </p>
        </div>
        <button
          @click="hideNotification"
          class="flex-shrink-0 text-gray-400 hover:text-gray-600 transition-colors"
        >
          <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
          </svg>
        </button>
      </div>
    </div>

    <div class="container mx-auto px-4 py-8 max-w-6xl">
      <!-- Empty Cart State -->
      <div v-if="cartItems.length === 0 && !isLoading" class="text-center py-16">
        <div class="max-w-md mx-auto">
          <div class="w-24 h-24 mx-auto mb-6 text-gray-400">
            <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4m0 0L7 13m0 0l-2.5 5.5M7 13l2.5 5.5m0 0L17 21m-7.5-2.5h9M17 21v-6m0 6l-2.5-5.5"/>
            </svg>
          </div>
          <h3 class="text-xl font-bold text-gray-800 mb-2">Your cart is empty</h3>
          <p class="text-gray-600 mb-6">Add some items to your cart before checkout</p>
          <button 
            @click="$router.push('/products')"
            class="px-8 py-3 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-all duration-200 transform hover:scale-105 font-semibold shadow-lg"
          >
            Continue Shopping
          </button>
        </div>
      </div>

      <!-- Checkout Steps -->
      <div v-else-if="cartItems.length > 0">
        <!-- Step 1: Shipping Information -->
        <div v-if="currentStep === 1" class="grid grid-cols-1 lg:grid-cols-3 gap-8">
          <!-- Shipping Form -->
          <div class="lg:col-span-2">
            <div class="bg-white rounded-xl shadow-lg border border-gray-200 p-6">
              <h2 class="text-2xl font-bold text-gray-800 mb-2">Shipping Information</h2>
              <p class="text-gray-600 mb-6">Enter your delivery details</p>
              
              <form @submit.prevent="validateShipping" class="space-y-6">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                  <!-- Country -->
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 mb-2">Country</label>
                    <input
                      v-model="shippingData.country"
                      type="text"
                      class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-red-500 transition-all duration-200"
                      placeholder="Enter your country"
                      :class="errors.country ? 'border-red-500' : ''"
                    >
                    <p v-if="errors.country" class="text-red-500 text-xs mt-1">{{ errors.country }}</p>
                  </div>

                  <!-- City -->
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 mb-2">City</label>
                    <input
                      v-model="shippingData.city"
                      type="text"
                      class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-red-500 transition-all duration-200"
                      placeholder="Enter your city"
                      :class="errors.city ? 'border-red-500' : ''"
                    >
                    <p v-if="errors.city" class="text-red-500 text-xs mt-1">{{ errors.city }}</p>
                  </div>

                  <!-- State -->
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 mb-2">State</label>
                    <input
                      v-model="shippingData.state"
                      type="text"
                      class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-red-500 transition-all duration-200"
                      placeholder="Enter your state"
                      :class="errors.state ? 'border-red-500' : ''"
                    >
                    <p v-if="errors.state" class="text-red-500 text-xs mt-1">{{ errors.state }}</p>
                  </div>

                  <!-- Pin Code -->
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 mb-2">Pin Code</label>
                    <input
                      v-model="shippingData.pinCode"
                      type="text"
                      class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-red-500 transition-all duration-200"
                      placeholder="Enter pin code"
                      :class="errors.pinCode ? 'border-red-500' : ''"
                    >
                    <p v-if="errors.pinCode" class="text-red-500 text-xs mt-1">{{ errors.pinCode }}</p>
                  </div>

                  <!-- Street Address -->
                  <div class="md:col-span-2">
                    <label class="block text-sm font-semibold text-gray-700 mb-2">Street Address</label>
                    <textarea
                      v-model="shippingData.street"
                      rows="3"
                      class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-red-500 transition-all duration-200"
                      placeholder="Enter your complete street address"
                      :class="errors.street ? 'border-red-500' : ''"
                    ></textarea>
                    <p v-if="errors.street" class="text-red-500 text-xs mt-1">{{ errors.street }}</p>
                  </div>

                  <!-- Phone Number -->
                  <div class="md:col-span-2">
                    <label class="block text-sm font-semibold text-gray-700 mb-2">Phone Number</label>
                    <input
                      v-model="shippingData.phoneNumber"
                      type="tel"
                      class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-red-500 transition-all duration-200"
                      placeholder="Enter your phone number"
                      :class="errors.phoneNumber ? 'border-red-500' : ''"
                    >
                    <p v-if="errors.phoneNumber" class="text-red-500 text-xs mt-1">{{ errors.phoneNumber }}</p>
                  </div>
                </div>

                <!-- Continue Button -->
                <div class="flex justify-end pt-4 border-t border-gray-200">
                  <button
                    type="submit"
                    :disabled="isLoading"
                    class="px-8 py-3 bg-red-600 text-white rounded-lg font-semibold hover:bg-red-700 transition-all duration-200 transform hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed flex items-center space-x-2 shadow-lg"
                  >
                    <span>Continue to Payment</span>
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                    </svg>
                  </button>
                </div>
              </form>
            </div>
          </div>

          <!-- Order Summary -->
          <div class="lg:col-span-1">
            <OrderSummaryInline 
              :items="cartItems"
              :totalMrpPrice="totalMrpPrice"
              :totalSellingPrice="totalSellingPrice"
            />
          </div>
        </div>

        <!-- Step 2: Payment Method -->
        <div v-if="currentStep === 2" class="grid grid-cols-1 lg:grid-cols-3 gap-8">
          <!-- Payment Form -->
          <div class="lg:col-span-2">
            <div class="bg-white rounded-xl shadow-lg border border-gray-200 p-6">
              <h2 class="text-2xl font-bold text-gray-800 mb-2">Payment Method</h2>
              <p class="text-gray-600 mb-6">Choose how you want to pay</p>
              
              <div class="space-y-4">
                <!-- Cash on Delivery -->
                <div 
                  @click="selectPaymentMethod('CASH')"
                  class="border-2 rounded-lg p-4 cursor-pointer transition-all duration-200 hover:shadow-md"
                  :class="paymentMethod === 'CASH' ? 'border-red-500 bg-red-50' : 'border-gray-200 hover:border-gray-300'"
                >
                  <div class="flex items-center justify-between">
                    <div class="flex items-center space-x-4">
                      <div class="w-12 h-12 bg-red-100 rounded-full flex items-center justify-center">
                        <span class="text-xl">💰</span>
                      </div>
                      <div>
                        <h3 class="font-bold text-gray-800">Cash on Delivery</h3>
                        <p class="text-sm text-gray-600">Pay when you receive your order</p>
                      </div>
                    </div>
                    <div 
                      class="w-6 h-6 rounded-full border-2 flex items-center justify-center"
                      :class="paymentMethod === 'CASH' ? 'border-red-500 bg-red-500' : 'border-gray-300'"
                    >
                      <svg v-if="paymentMethod === 'CASH'" class="w-3 h-3 text-white" fill="currentColor" viewBox="0 0 20 20">
                        <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"/>
                      </svg>
                    </div>
                  </div>
                </div>

                <!-- Stripe Payment -->
                <div 
                  @click="selectPaymentMethod('STRIPE')"
                  class="border-2 rounded-lg p-4 cursor-pointer transition-all duration-200 hover:shadow-md"
                  :class="paymentMethod === 'STRIPE' ? 'border-red-500 bg-red-50' : 'border-gray-200 hover:border-gray-300'"
                >
                  <div class="flex items-center justify-between">
                    <div class="flex items-center space-x-4">
                      <div class="w-12 h-12 bg-blue-100 rounded-full flex items-center justify-center">
                        <span class="text-xl">💳</span>
                      </div>
                      <div>
                        <h3 class="font-bold text-gray-800">Credit/Debit Card (Stripe)</h3>
                        <p class="text-sm text-gray-600">Secure online payment</p>
                      </div>
                    </div>
                    <div 
                      class="w-6 h-6 rounded-full border-2 flex items-center justify-center"
                      :class="paymentMethod === 'STRIPE' ? 'border-red-500 bg-red-500' : 'border-gray-300'"
                    >
                      <svg v-if="paymentMethod === 'STRIPE'" class="w-3 h-3 text-white" fill="currentColor" viewBox="0 0 20 20">
                        <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"/>
                      </svg>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Payment Method Error -->
              <div v-if="errors.paymentMethod" class="mt-4 p-3 bg-red-50 border border-red-200 rounded-lg">
                <p class="text-red-600 text-sm font-medium">{{ errors.paymentMethod }}</p>
              </div>

              <!-- Action Buttons -->
              <div class="flex justify-between pt-8 border-t border-gray-200">
                <button
                  @click="currentStep = 1"
                  :disabled="isLoading"
                  class="px-6 py-3 border border-gray-300 text-gray-700 rounded-lg font-medium hover:bg-gray-50 transition-all duration-200 disabled:opacity-50 flex items-center space-x-2"
                >
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path>
                  </svg>
                  <span>Back to Shipping</span>
                </button>
                <button
                  @click="validatePayment"
                  :disabled="isLoading"
                  class="px-8 py-3 bg-red-600 text-white rounded-lg font-semibold hover:bg-red-700 transition-all duration-200 transform hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed flex items-center space-x-2 shadow-lg"
                >
                  <span v-if="paymentMethod === 'CASH'">Place Order</span>
                  <span v-else>Proceed to Payment</span>
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                  </svg>
                </button>
              </div>
            </div>
          </div>

          <!-- Order Summary -->
          <div class="lg:col-span-1">
            <OrderSummaryInline 
              :items="cartItems"
              :totalMrpPrice="totalMrpPrice"
              :totalSellingPrice="totalSellingPrice"
              :shippingData="shippingData"
            />
          </div>
        </div>

        <!-- Step 3: Order Confirmation -->
        <div v-if="currentStep === 3" class="max-w-2xl mx-auto">
          <div class="bg-white rounded-xl shadow-lg border border-gray-200 p-8 text-center">
            <div class="w-20 h-20 mx-auto mb-6 bg-green-100 rounded-full flex items-center justify-center border-4 border-green-200">
              <svg class="w-10 h-10 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
              </svg>
            </div>
            
            <h2 class="text-3xl font-bold text-gray-800 mb-4">Order Placed Successfully!</h2>
            <p class="text-lg text-gray-600 mb-2">Thank you for your purchase</p>
            <p class="text-gray-500 mb-6">Your order ID: <span class="font-bold text-red-600">#{{ orderId }}</span></p>

            <div class="bg-gray-50 rounded-lg p-6 mb-8 text-left border border-gray-200">
              <h3 class="font-bold text-gray-800 mb-4 text-lg">Order Details</h3>
              <div class="space-y-3 text-sm text-gray-600">
                <div class="flex justify-between">
                  <span class="font-semibold">Payment Method:</span>
                  <span class="font-medium">{{ paymentMethod === 'CASH' ? 'Cash on Delivery' : 'Credit/Debit Card' }}</span>
                </div>
                <div class="flex justify-between">
                  <span class="font-semibold">Total Amount:</span>
                  <span class="font-bold text-green-600 text-lg">Rs. {{ totalSellingPrice.toLocaleString() }}</span>
                </div>
              </div>
            </div>

            <div class="flex flex-col sm:flex-row gap-4 justify-center">
              <button
                @click="viewOrderHistory"
                class="px-8 py-3 bg-red-600 text-white rounded-lg font-semibold hover:bg-red-700 transition-all duration-200 shadow-lg"
              >
                View Order History
              </button>
              <button
                @click="continueShopping"
                class="px-8 py-3 border border-gray-300 text-gray-700 rounded-lg font-medium hover:bg-gray-50 transition-all duration-200"
              >
                Continue Shopping
              </button>
            </div>
          </div>
        </div>
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

// Inline OrderSummary component
const OrderSummaryInline = {
  name: "OrderSummaryInline",
  props: {
    items: {
      type: Array,
      default: () => []
    },
    totalMrpPrice: {
      type: Number,
      default: 0
    },
    totalSellingPrice: {
      type: Number,
      default: 0
    },
    shippingData: {
      type: Object,
      default: null
    }
  },
  template: `
    <div class="bg-white rounded-xl shadow-lg border border-gray-200 sticky top-4">
      <div class="p-6 border-b border-gray-200 bg-gradient-to-r from-red-50 to-pink-50">
        <h2 class="text-xl font-bold text-gray-800">Order Summary</h2>
      </div>

      <div class="p-6 max-h-80 overflow-y-auto">
        <div v-for="item in items" :key="item.productId" class="flex items-center space-x-3 py-3 border-b border-gray-100 last:border-b-0">
          <div class="w-12 h-12 bg-gradient-to-br from-red-100 to-pink-100 rounded-lg flex items-center justify-center">
            <span class="text-lg">🛒</span>
          </div>
          <div class="flex-1 min-w-0">
            <h4 class="text-sm font-semibold text-gray-800 truncate">{{ item.productName }}</h4>
            <p class="text-xs text-gray-500">Qty: {{ item.quantity }} × Rs. {{ item.price.toLocaleString() }}</p>
          </div>
          <div class="text-right">
            <p class="text-sm font-bold text-gray-800">Rs. {{ (item.price * item.quantity).toLocaleString() }}</p>
          </div>
        </div>
      </div>

      <div class="p-6 space-y-3 border-t border-gray-200">
        <div class="flex justify-between items-center">
          <span class="text-gray-600 font-medium">Subtotal</span>
          <span class="text-gray-800 font-bold">Rs. {{ totalMrpPrice.toLocaleString() }}</span>
        </div>
        
        <div v-if="totalMrpPrice > totalSellingPrice" class="flex justify-between items-center text-green-600 bg-green-50 p-3 rounded-lg">
          <span class="font-semibold">Discount</span>
          <span class="font-bold">- Rs. {{ (totalMrpPrice - totalSellingPrice).toLocaleString() }}</span>
        </div>
        
        <div class="border-t border-gray-200 pt-3">
          <div class="flex justify-between items-center text-lg font-bold">
            <span class="text-gray-800">Total Amount</span>
            <span class="text-green-600">Rs. {{ totalSellingPrice.toLocaleString() }}</span>
          </div>
        </div>
      </div>

      <div v-if="shippingData" class="p-6 border-t border-gray-200 bg-gray-50">
        <h3 class="text-sm font-bold text-gray-800 mb-2">Shipping to:</h3>
        <div class="text-xs text-gray-600 space-y-1">
          <p class="font-medium">{{ shippingData.street }}</p>
          <p>{{ shippingData.city }}, {{ shippingData.state }} - {{ shippingData.pinCode }}</p>
          <p>{{ shippingData.country }}</p>
          <p class="font-semibold">{{ shippingData.phoneNumber }}</p>
        </div>
      </div>
    </div>
  `
};

export default {
  components: { Hero, Navbar, Footer, OrderSummaryInline },
  name: "CheckoutPage",
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
      errors: {}
    };
  },
  computed: {
    notificationClass() {
      const baseClasses = 'transition-all duration-500 ease-in-out';
      if (this.showNotification) {
        return `${baseClasses} translate-x-0 opacity-100`;
      } else {
        return `${baseClasses} translate-x-full opacity-0`;
      }
    },
    notificationStyle() {
      return this.notificationType === 'success' 
        ? 'bg-green-50 border-green-200 text-green-800' 
        : 'bg-red-50 border-red-200 text-red-800';
    },
    notificationIconStyle() {
      return this.notificationType === 'success' 
        ? 'bg-green-100 text-green-600' 
        : 'bg-red-100 text-red-600';
    },
    notificationTextStyle() {
      return this.notificationType === 'success' 
        ? 'text-green-800' 
        : 'text-red-800';
    }
  },
  async mounted() {
    await this.fetchCart();
  },
  methods: {
    showCustomNotification(message, type = 'success') {
      this.notificationMessage = message;
      this.notificationType = type;
      this.showNotification = true;

      clearTimeout(this.notificationTimeout);
      this.notificationTimeout = setTimeout(() => {
        this.hideNotification();
      }, 4000);
    },

    hideNotification() {
      this.showNotification = false;
      clearTimeout(this.notificationTimeout);
    },

    clearErrors() {
      this.errors = {};
    },

    validateField(field, value) {
      this.errors[field] = '';

      if (!value || value.trim() === '') {
        this.errors[field] = `${this.formatFieldName(field)} is required`;
        return false;
      }

      // Specific validations
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

    async fetchCart() {
      this.isLoading = true;
      try {
        const result = await CartService.getCartItems();
        if (result.code === "0" && result.data) {
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
      this.isLoading = true;
      try {
        // First, place the order
        const orderData = {
          paymentMethod: this.paymentMethod,
          Country: this.shippingData.country,
          city: this.shippingData.city,
          state: this.shippingData.state,
          street: this.shippingData.street,
          pinCode: this.shippingData.pinCode,
          phoneNumber: this.shippingData.phoneNumber
        };

        console.log("Placing order with data:", orderData);

        const placeOrderResult = await OrderService.placeOrder(orderData);
        
        if (!placeOrderResult.success) {
          this.showCustomNotification(placeOrderResult.message, 'error');
          return;
        }

        this.orderId = placeOrderResult.orderId;

        // Handle different payment methods
        if (this.paymentMethod === 'CASH') {
          // Confirm Cash on Delivery order
          const codResult = await OrderService.confirmCodOrder(this.orderId);
          
          if (codResult.success) {
            this.currentStep = 3;
            this.showCustomNotification("Order placed successfully with Cash on Delivery! 🎉", 'success');
            
            // Clear cart after successful order
            await CartService.clearCart();
          } else {
            this.showCustomNotification(codResult.message, 'error');
          }

        } else if (this.paymentMethod === 'STRIPE') {
          // Create Stripe checkout session
          const successUrl = `${window.location.origin}/order-success?orderId=${this.orderId}`;
          const cancelUrl = `${window.location.origin}/checkout?step=2`;

          const stripeResult = await OrderService.createStripeCheckout(
            this.orderId, 
            successUrl, 
            cancelUrl
          );

          if (stripeResult.success) {
            // Redirect to Stripe checkout
            window.location.href = stripeResult.sessionUrl;
          } else {
            this.showCustomNotification(stripeResult.message, 'error');
          }
        }

      } catch (error) {
        console.error("Payment processing error:", error);
        this.showCustomNotification("Failed to process payment. Please try again.", 'error');
      } finally {
        this.isLoading = false;
      }
    },

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
.container {
  max-width: 1200px;
}

/* Custom focus styles for better UX */
input:focus, textarea:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(239, 68, 68, 0.1);
}
</style>