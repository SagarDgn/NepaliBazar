<!-- pages/OrderHistory.vue -->
<template>
  <div class="min-h-screen bg-gray-50">
    <Hero />
    <Navbar />
    
    <div class="container mx-auto px-4 py-8">
      <h1 class="text-3xl font-bold text-gray-800 mb-8">Order History</h1>
      
      <div v-if="isLoading" class="text-center py-8">
        <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-red-600 mx-auto"></div>
        <p class="mt-4 text-gray-600">Loading orders...</p>
      </div>

      <div v-else-if="orders.length === 0" class="text-center py-16">
        <div class="w-24 h-24 mx-auto mb-6 text-gray-400">
          <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z"/>
          </svg>
        </div>
        <h3 class="text-xl font-bold text-gray-800 mb-2">No orders yet</h3>
        <p class="text-gray-600 mb-6">Your order history will appear here</p>
        <button 
          @click="$router.push('/products')"
          class="px-8 py-3 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-all duration-200 font-semibold"
        >
          Start Shopping
        </button>
      </div>

      <div v-else class="space-y-6">
        <div 
          v-for="order in orders" 
          :key="order.id"
          class="bg-white rounded-xl shadow-lg border border-gray-200 p-6"
        >
          <div class="flex justify-between items-start mb-4">
            <div>
              <h3 class="text-lg font-bold text-gray-800">Order #{{ order.id }}</h3>
              <p class="text-sm text-gray-600">Placed on {{ formatDate(order.orderDate) }}</p>
            </div>
            <div class="text-right">
              <span class="inline-block px-3 py-1 rounded-full text-sm font-semibold" 
                    :class="getStatusClass(order.orderStatus)">
                {{ order.orderStatus }}
              </span>
              <p class="text-lg font-bold text-green-600 mt-1">Rs. {{ order.totalSellingPrice?.toLocaleString() }}</p>
            </div>
          </div>
          
          <div class="border-t border-gray-200 pt-4">
            <button 
              @click="viewOrderDetails(order.id)"
              class="text-red-600 hover:text-red-700 font-semibold text-sm"
            >
              View Details
            </button>
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
import OrderService from "../services/OrderService";

export default {
  components: { Hero, Navbar, Footer },
  name: "OrderHistory",
  data() {
    return {
      isLoading: false,
      orders: []
    };
  },
  async mounted() {
    await this.fetchOrders();
  },
  methods: {
    async fetchOrders() {
      this.isLoading = true;
      try {
        const result = await OrderService.getUserOrders();
        if (result.success) {
          this.orders = result.orders || [];
        } else {
          console.error("Failed to fetch orders:", result.message);
        }
      } catch (error) {
        console.error("Error fetching orders:", error);
      } finally {
        this.isLoading = false;
      }
    },
    formatDate(dateString) {
      return new Date(dateString).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
      });
    },
    getStatusClass(status) {
      const statusClasses = {
        'CONFIRMED': 'bg-green-100 text-green-800',
        'PENDING': 'bg-yellow-100 text-yellow-800',
        'CANCELLED': 'bg-red-100 text-red-800',
        'SHIPPED': 'bg-blue-100 text-blue-800',
        'DELIVERED': 'bg-purple-100 text-purple-800'
      };
      return statusClasses[status] || 'bg-gray-100 text-gray-800';
    },
    viewOrderDetails(orderId) {
      // Navigate to order details page or show modal
      console.log("View order details:", orderId);
    }
  }
};
</script>