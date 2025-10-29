<!-- pages/OrderSuccess.vue -->
<template>
  <div class="min-h-screen bg-gray-50">
    <Hero />
    <Navbar />
    
    <div class="container mx-auto px-4 py-8 max-w-2xl">
      <div class="bg-white rounded-xl shadow-lg border border-gray-200 p-8 text-center">
        <div class="w-20 h-20 mx-auto mb-6 bg-green-100 rounded-full flex items-center justify-center border-4 border-green-200">
          <svg class="w-10 h-10 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
          </svg>
        </div>
        
        <h2 class="text-3xl font-bold text-gray-800 mb-4">Payment Successful!</h2>
        <p class="text-lg text-gray-600 mb-2">Thank you for your purchase</p>
        <p class="text-gray-500 mb-6">Your order has been confirmed</p>

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

    <Footer />
  </div>
</template>

<script>
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";
import Footer from "../components/ui/Footer.vue";

export default {
  components: { Hero, Navbar, Footer },
  name: "OrderSuccess",
  methods: {
    viewOrderHistory() {
      this.$router.push('/orders');
    },
    continueShopping() {
      this.$router.push('/products');
    }
  },
  mounted() {
    // You can fetch order details here using the orderId from URL params
    const urlParams = new URLSearchParams(window.location.search);
    const orderId = urlParams.get('orderId');
    if (orderId) {
      console.log("Order ID from URL:", orderId);
    }
  }
};
</script>