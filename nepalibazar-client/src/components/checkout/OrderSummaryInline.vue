<template>
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
</template>

<script>
export default {
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
  }
};
</script>