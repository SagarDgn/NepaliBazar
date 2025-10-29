<template>
  <div class="text-center py-16">
    <div class="max-w-md mx-auto">
      <div class="w-24 h-24 mx-auto mb-6 text-gray-400">
        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4m0 0L7 13m0 0l-2.5 5.5M7 13l2.5 5.5m0 0L17 21m-7.5-2.5h9M17 21v-6m0 6l-2.5-5.5"/>
        </svg>
      </div>
      <h3 class="text-xl font-bold text-gray-800 mb-2">Your cart is empty</h3>
      <p class="text-gray-600 mb-6">Add some items to your cart before checkout</p>
      <button 
        @click="$emit('continue-shopping')"
        class="px-8 py-3 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-all duration-200 transform hover:scale-105 font-semibold shadow-lg"
      >
        Continue Shopping
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "EmptyCartState",
  emits: ['continue-shopping']
};
</script>