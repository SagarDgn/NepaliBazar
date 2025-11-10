<template>
  <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
    <!-- Payment Form -->
    <div class="lg:col-span-2">
      <div class="bg-white rounded-lg shadow border border-gray-200 p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-2">Payment Method</h2>
        <p class="text-gray-600 mb-6">Choose how you want to pay</p>
        
        <div class="space-y-4">
          <!-- Cash on Delivery -->
          <div 
            @click="$emit('select-payment', 'CASH')"
            class="border-2 rounded-lg p-4 cursor-pointer transition-all duration-200"
            :class="paymentMethod === 'CASH' ? 'border-gray-800 bg-gray-50' : 'border-gray-200 hover:border-gray-400'"
          >
            <div class="flex items-center justify-between">
              <div class="flex items-center space-x-4">
                <div class="w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center">
                  <svg class="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 9V7a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2m2 4h10a2 2 0 002-2v-6a2 2 0 00-2-2H9a2 2 0 00-2 2v6a2 2 0 002 2zm7-5a2 2 0 11-4 0 2 2 0 014 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-800">Cash on Delivery</h3>
                  <p class="text-sm text-gray-600">Pay when you receive your order</p>
                </div>
              </div>
              <div 
                class="w-6 h-6 rounded-full border-2 flex items-center justify-center"
                :class="paymentMethod === 'CASH' ? 'border-gray-800 bg-gray-800' : 'border-gray-300'"
              >
                <svg v-if="paymentMethod === 'CASH'" class="w-3 h-3 text-white" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"/>
                </svg>
              </div>
            </div>
          </div>

          <!-- Stripe Payment -->
          <div 
            @click="$emit('select-payment', 'STRIPE')"
            class="border-2 rounded-lg p-4 cursor-pointer transition-all duration-200"
            :class="paymentMethod === 'STRIPE' ? 'border-gray-800 bg-gray-50' : 'border-gray-200 hover:border-gray-400'"
          >
            <div class="flex items-center justify-between">
              <div class="flex items-center space-x-4">
                <div class="w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center">
                  <svg class="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-800">Credit/Debit Card</h3>
                  <p class="text-sm text-gray-600">Secure online payment via Stripe</p>
                </div>
              </div>
              <div 
                class="w-6 h-6 rounded-full border-2 flex items-center justify-center"
                :class="paymentMethod === 'STRIPE' ? 'border-gray-800 bg-gray-800' : 'border-gray-300'"
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
        <div class="flex justify-between items-center pt-8 border-t border-gray-200">
          <button
            @click="$emit('back')"
            :disabled="isLoading"
            class="px-6 py-3 border border-gray-300 text-gray-700 rounded-lg font-medium hover:bg-gray-50 transition-colors duration-200 disabled:opacity-50 flex items-center space-x-2"
          >
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path>
            </svg>
            <span>Back to Shipping</span>
          </button>
          <button
            @click="$emit('validate')"
            :disabled="isLoading"
            class="px-8 py-3 bg-black text-white rounded-lg font-semibold transition-colors duration-200 disabled:opacity-50 disabled:cursor-not-allowed flex items-center space-x-2"
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
</template>

<script>
import OrderSummaryInline from "./OrderSummaryInline.vue";

export default {
  name: "PaymentStep",
  components: { OrderSummaryInline },
  props: {
    paymentMethod: {
      type: String,
      default: null
    },
    errors: {
      type: Object,
      default: () => ({})
    },
    isLoading: {
      type: Boolean,
      default: false
    },
    cartItems: {
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
  emits: ['select-payment', 'validate', 'back']
};
</script>