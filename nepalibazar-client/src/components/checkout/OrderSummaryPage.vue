<template>
  <div class="bg-white rounded-2xl shadow-lg border border-gray-200 sticky top-4">
    <!-- Summary Header -->
    <div class="p-6 border-b border-gray-200 bg-gradient-to-r from-green-50 to-emerald-50">
      <h2 class="text-xl font-semibold text-gray-800">Order Summary</h2>
    </div>

    <!-- Items List -->
    <div class="p-6 max-h-80 overflow-y-auto">
      <div v-for="item in items" :key="item.productId" class="flex items-center space-x-3 py-3 border-b border-gray-100 last:border-b-0">
        <div class="w-12 h-12 bg-gradient-to-br from-blue-100 to-purple-100 rounded-lg flex items-center justify-center">
          <span class="text-lg">🛒</span>
        </div>
        <div class="flex-1 min-w-0">
          <h4 class="text-sm font-medium text-gray-800 truncate">{{ item.productName }}</h4>
          <p class="text-xs text-gray-500">Qty: {{ item.quantity }} × Rs. {{ item.price.toLocaleString() }}</p>
        </div>
        <div class="text-right">
          <p class="text-sm font-semibold text-gray-800">Rs. {{ (item.price * item.quantity).toLocaleString() }}</p>
        </div>
      </div>
    </div>

    <!-- Pricing Details -->
    <div class="p-6 space-y-3 border-t border-gray-200">
      <div class="flex justify-between items-center">
        <span class="text-gray-600">Subtotal</span>
        <span class="text-gray-800 font-medium">Rs. {{ totalMrpPrice.toLocaleString() }}</span>
      </div>
      
      <div v-if="totalMrpPrice > totalSellingPrice" class="flex justify-between items-center text-green-600">
        <span class="font-medium">Discount</span>
        <span class="font-semibold">- Rs. {{ (totalMrpPrice - totalSellingPrice).toLocaleString() }}</span>
      </div>
      
      <div class="border-t border-gray-200 pt-3">
        <div class="flex justify-between items-center text-lg font-semibold">
          <span class="text-gray-800">Total Amount</span>
          <span class="text-green-600">Rs. {{ totalSellingPrice.toLocaleString() }}</span>
        </div>
      </div>
    </div>

    <!-- Shipping Address Preview -->
    <div v-if="shippingData" class="p-6 border-t border-gray-200 bg-gray-50">
      <h3 class="text-sm font-semibold text-gray-800 mb-2">Shipping to:</h3>
      <div class="text-xs text-gray-600 space-y-1">
        <p>{{ shippingData.street }}</p>
        <p>{{ shippingData.city }}, {{ shippingData.state }} - {{ shippingData.pinCode }}</p>
        <p>{{ shippingData.country }}</p>
        <p class="font-medium">{{ shippingData.phoneNumber }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderSummary",
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
  }
};
</script>