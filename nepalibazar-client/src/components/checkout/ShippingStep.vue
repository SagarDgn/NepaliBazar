<template>
  <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
    <!-- Shipping Form -->
    <div class="lg:col-span-2">
      <div class="bg-white rounded-xl shadow-lg border border-gray-200 p-6">
        <h2 class="text-2xl font-bold text-gray-800 mb-2">Shipping Information</h2>
        <p class="text-gray-600 mb-6">Enter your delivery details</p>
        
        <form @submit.prevent="$emit('validate')" class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <!-- Country -->
            <div>
              <label class="block text-sm font-semibold text-gray-700 mb-2">Country</label>
              <input
                :value="shippingData.country"
                @input="$emit('update:shippingData', 'country', $event.target.value)"
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
                :value="shippingData.city"
                @input="$emit('update:shippingData', 'city', $event.target.value)"
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
                :value="shippingData.state"
                @input="$emit('update:shippingData', 'state', $event.target.value)"
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
                :value="shippingData.pinCode"
                @input="$emit('update:shippingData', 'pinCode', $event.target.value)"
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
                :value="shippingData.street"
                @input="$emit('update:shippingData', 'street', $event.target.value)"
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
                :value="shippingData.phoneNumber"
                @input="$emit('update:shippingData', 'phoneNumber', $event.target.value)"
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
</template>

<script>
import OrderSummaryInline from "./OrderSummaryInline.vue";

export default {
  name: "ShippingStep",
  components: { OrderSummaryInline },
  props: {
    shippingData: {
      type: Object,
      required: true
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
    }
  },
  emits: ['update:shippingData', 'validate', 'back']
};
</script>