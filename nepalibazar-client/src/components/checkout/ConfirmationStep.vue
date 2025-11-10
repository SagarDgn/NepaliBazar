<template>
  <div class="max-w-2xl mx-auto">
    <div class="bg-white rounded-lg shadow border border-gray-200 p-8 text-center">
      <div class="w-20 h-20 mx-auto mb-6 bg-gray-100 rounded-full flex items-center justify-center border-4 border-gray-200">
        <svg class="w-10 h-10 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
        </svg>
      </div>
      
      <h2 class="text-3xl font-semibold text-gray-800 mb-4">Order Placed Successfully!</h2>
      <p class="text-lg text-gray-600 mb-2">Thank you for your purchase</p>
      <p class="text-gray-500 mb-6">Your order ID: <span class="font-semibold text-gray-800">#{{ orderId }}</span></p>

      <div class="bg-gray-50 rounded-lg p-6 mb-8 text-left border border-gray-200">
        <h3 class="font-semibold text-gray-800 mb-4 text-lg">Order Details</h3>
        <div class="space-y-3 text-sm text-gray-600">
          <div class="flex justify-between">
            <span class="font-medium">Payment Method:</span>
            <span class="font-medium">{{ paymentMethod === 'CASH' ? 'Cash on Delivery' : 'Credit/Debit Card' }}</span>
          </div>
          <div class="flex justify-between">
            <span class="font-medium">Total Amount:</span>
            <span class="font-semibold text-gray-800 text-lg">Rs. {{ totalSellingPrice.toLocaleString() }}</span>
          </div>
        </div>
      </div>

      <div class="flex flex-col sm:flex-row gap-4 justify-center">
        <button
          @click="$emit('view-orders')"
          class="px-8 py-3 bg-black text-white rounded-lg font-semibold transition-colors duration-200"
        >
          View Order History
        </button>
        <button
          @click="$emit('continue-shopping')"
          class="px-8 py-3 border border-gray-300 text-gray-700 rounded-lg font-medium hover:bg-gray-50 transition-colors duration-200"
        >
          Continue Shopping
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ConfirmationStep",
  props: {
    orderId: {
      type: [String, Number],
      default: null
    },
    paymentMethod: {
      type: String,
      default: null
    },
    totalSellingPrice: {
      type: Number,
      default: 0
    }
  },
  emits: ['view-orders', 'continue-shopping']
};
</script>