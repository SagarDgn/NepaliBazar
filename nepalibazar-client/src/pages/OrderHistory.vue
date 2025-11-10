<template>
  <div class="min-h-screen bg-white">
    <Hero />
    <Navbar />
    
    <div class="container mx-auto px-4 py-8">
      <!-- Header Section -->
      <div class="text-center mb-12">
        <div class="inline-flex items-center justify-center w-20 h-20 bg-black rounded-lg shadow mb-6">
          <svg class="w-10 h-10 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z"/>
          </svg>
        </div>
        <h1 class="text-4xl font-semibold text-gray-800 mb-4">Order History</h1>
        <p class="text-lg text-gray-600 max-w-2xl mx-auto">
          Track your purchases, view order details, and manage your shopping history
        </p>
      </div>

      <!-- Stats Cards -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-12">
        <div class="bg-white rounded-lg p-6 shadow border border-gray-200 transition-all duration-300">
          <div class="flex items-center">
            <div class="p-3 bg-gray-100 rounded-lg mr-4">
              <svg class="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2"/>
              </svg>
            </div>
            <div>
              <p class="text-2xl font-semibold text-gray-800">{{ totalOrders }}</p>
              <p class="text-sm text-gray-600">Total Orders</p>
            </div>
          </div>
        </div>

        <div class="bg-white rounded-lg p-6 shadow border border-gray-200 transition-all duration-300">
          <div class="flex items-center">
            <div class="p-3 bg-gray-100 rounded-lg mr-4">
              <svg class="w-6 h-6 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
              </svg>
            </div>
            <div>
              <p class="text-2xl font-semibold text-gray-800">{{ confirmedOrders }}</p>
              <p class="text-sm text-gray-600">Confirmed</p>
            </div>
          </div>
        </div>

        <div class="bg-white rounded-lg p-6 shadow border border-gray-200 transition-all duration-300">
          <div class="flex items-center">
            <div class="p-3 bg-gray-100 rounded-lg mr-4">
              <svg class="w-6 h-6 text-red-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
              </svg>
            </div>
            <div>
              <p class="text-2xl font-semibold text-gray-800">{{ pendingOrders }}</p>
              <p class="text-sm text-gray-600">Pending</p>
            </div>
          </div>
        </div>

        <div class="bg-white rounded-lg p-6 shadow border border-gray-200 transition-all duration-300">
          <div class="flex items-center">
            <div class="p-3 bg-gray-100 rounded-lg mr-4">
              <h1>NPR.</h1>
            </div>
            <div>
              <p class="text-2xl font-semibold text-gray-800">NPR {{ totalSpent.toLocaleString() }}</p>
              <p class="text-sm text-gray-600">Total Spent</p>
            </div>
          </div>
        </div>
      </div>

      <!-- Loading State -->
      <div v-if="isLoading" class="text-center py-16">
        <div class="animate-spin rounded-full h-16 w-16 border-b-2 border-gray-800 mx-auto mb-4"></div>
        <p class="text-gray-600 text-lg">Loading your orders...</p>
      </div>

      <!-- Empty State -->
      <div v-else-if="orders.length === 0" class="text-center py-20">
        <div class="w-32 h-32 mx-auto mb-6 text-gray-300">
          <svg fill="none" stroke="currentColor" viewBox="0 0 24 24" class="w-full h-full">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z"/>
          </svg>
        </div>
        <h3 class="text-2xl font-semibold text-gray-800 mb-3">No orders yet</h3>
        <p class="text-gray-600 text-lg mb-8 max-w-md mx-auto">
          Start shopping to see your order history here. Your purchases will appear in this section.
        </p>
        <button 
          @click="$router.push('/products')"
          class="px-8 py-4 bg-black text-white rounded-lg font-semibold transition-colors duration-200"
        >
          Start Shopping
        </button>
      </div>

      <!-- Orders List -->
      <div v-else class="space-y-6">
        <div 
          v-for="order in orders" 
          :key="order.id"
          class="bg-white rounded-lg shadow border border-gray-200 overflow-hidden transition-all duration-300"
        >
          <!-- Order Header -->
          <div class="bg-gray-50 p-6 border-b border-gray-200">
            <div class="flex flex-col lg:flex-row lg:items-center lg:justify-between">
              <div class="flex-1">
                <div class="flex flex-wrap items-center gap-3 mb-4">
                  <h3 class="text-xl font-semibold text-gray-800">Order #{{ order.id }}</h3>
                  <div class="flex flex-wrap gap-2">
                    <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium" 
                          :class="getStatusClass(order.orderStatus)">
                      <span class="w-2 h-2 rounded-full mr-2" :class="getStatusDotClass(order.orderStatus)"></span>
                      {{ formatStatus(order.orderStatus) }}
                    </span>
                    <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-gray-100 text-gray-700">
                      <svg class="w-4 h-4 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z"/>
                      </svg>
                      {{ order.paymentMethod }}
                    </span>
                  </div>
                </div>
                
                <div class="flex flex-wrap gap-6 text-sm text-gray-600">
                  <div class="flex items-center space-x-2">
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"/>
                    </svg>
                    <span>{{ formatDate(order.orderDate) }}</span>
                  </div>
                  <div class="flex items-center space-x-2">
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z"/>
                    </svg>
                    <span>{{ order.totalItem }} item{{ order.totalItem !== 1 ? 's' : '' }}</span>
                  </div>
                </div>
              </div>
              
              <div class="mt-4 lg:mt-0 lg:text-right">
                <div class="space-y-1">
                  <p class="text-2xl font-semibold text-gray-800">
                    NPR {{ order.totalSellingPrice?.toLocaleString() }}
                  </p>
                  <p v-if="order.discount > 0" class="text-sm text-gray-500 line-through">
                    NPR {{ order.totalMrpPrice?.toLocaleString() }}
                  </p>
                </div>
              </div>
            </div>
          </div>

          <!-- Order Items -->
          <div class="p-6">
            <h4 class="text-lg font-semibold text-gray-800 mb-4 flex items-center">
              <svg class="w-5 h-5 mr-2 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4"/>
              </svg>
              Order Items ({{ order.orderItem?.length || 0 }})
            </h4>
            
            <div class="space-y-3">
              <div 
                v-for="item in order.orderItem" 
                :key="item.id"
                class="flex items-center space-x-4 p-4 bg-gray-50 rounded-lg border border-gray-200"
              >
                <div class="w-16 h-16 bg-gray-200 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"/>
                  </svg>
                </div>
                <div class="flex-1 min-w-0">
                  <div class="flex flex-col sm:flex-row sm:items-start sm:justify-between">
                    <div class="flex-1">
                      <h5 class="font-medium text-gray-800 mb-1">
                        {{ item.productEntity?.productName }}
                      </h5>
                      <div class="flex flex-wrap items-center gap-3 text-sm text-gray-600">
                        <span>Quantity: {{ item.quantity }}</span>
                        <span>•</span>
                        <span>NPR {{ item.mrpPrice?.toLocaleString() }} each</span>
                      </div>
                    </div>
                    <div class="mt-2 sm:mt-0 sm:text-right">
                      <p class="font-semibold text-gray-800">
                        NPR {{ (item.mrpPrice * item.quantity).toLocaleString() }}
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Shipping & Actions -->
          <div class="bg-gray-50 p-6 border-t border-gray-200">
            <div class="flex flex-col lg:flex-row lg:items-start lg:justify-between">
              <div class="flex-1">
                <h5 class="font-medium text-gray-800 mb-3 flex items-center">
                  <svg class="w-4 h-4 mr-2 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"/>
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"/>
                  </svg>
                  Shipping Address
                </h5>
                <div class="text-gray-600 text-sm space-y-1">
                  <p>{{ order.shippingAddress?.street }}, {{ order.shippingAddress?.city }}</p>
                  <p>{{ order.shippingAddress?.state }}, {{ order.shippingAddress?.country }} - {{ order.shippingAddress?.pinCode }}</p>
                  <p class="flex items-center space-x-1">
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z"/>
                    </svg>
                    <span>{{ order.shippingAddress?.phoneNumber }}</span>
                  </p>
                </div>
              </div>
              
              <div class="mt-4 lg:mt-0 lg:text-right space-y-3">
                <button 
                  @click="viewOrderDetails(order)"
                  class="w-full lg:w-auto px-6 py-3 bg-black text-white font-medium rounded-lg transition-colors duration-200 flex items-center justify-center space-x-2"
                >
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"/>
                  </svg>
                  <span>View Details</span>
                </button>
                
                <div class="flex flex-col sm:flex-row gap-2 justify-center lg:justify-end">
                  <button 
                    v-if="order.orderStatus === 'PENDING'"
                    @click="cancelOrder(order.id)"
                    class="px-4 py-2 text-sm bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 transition-colors duration-200 font-medium"
                  >
                    Cancel Order
                  </button>
                  <button 
                    v-if="order.orderStatus === 'DELIVERED'"
                    @click="reorder(order)"
                    class="px-4 py-2 text-sm bg-gray-800 text-white rounded-lg hover:bg-gray-900 transition-colors duration-200 font-medium"
                  >
                    Reorder
                  </button>
                  <button 
                    v-if="order.orderStatus === 'CONFIRMED'"
                    @click="trackOrder(order.id)"
                    class="px-4 py-2 text-sm bg-gray-800 text-white rounded-lg hover:bg-gray-900 transition-colors duration-200 font-medium"
                  >
                    Track Order
                  </button>
                </div>
              </div>
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
  computed: {
    totalOrders() {
      return this.orders.length;
    },
    confirmedOrders() {
      return this.orders.filter(order => order.orderStatus === 'CONFIRMED').length;
    },
    pendingOrders() {
      return this.orders.filter(order => order.orderStatus === 'PENDING').length;
    },
    totalSpent() {
      return this.orders.reduce((total, order) => total + (order.totalSellingPrice || 0), 0);
    }
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
          console.log('Orders loaded:', this.orders);
        } else {
          console.error("Failed to fetch orders:", result.message);
          this.$parent.showToast(result.message, 'error');
        }
      } catch (error) {
        console.error("Error fetching orders:", error);
        this.$parent.showToast('Failed to load orders', 'error');
      } finally {
        this.isLoading = false;
      }
    },
    formatDate(dateString) {
      return new Date(dateString).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
      });
    },
    formatStatus(status) {
      const statusMap = {
        'PENDING': 'Pending',
        'CONFIRMED': 'Confirmed',
        'CANCELLED': 'Cancelled',
        'SHIPPED': 'Shipped',
        'DELIVERED': 'Delivered'
      };
      return statusMap[status] || status;
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
    getStatusDotClass(status) {
      const dotClasses = {
        'CONFIRMED': 'bg-green-500',
        'PENDING': 'bg-yellow-500',
        'CANCELLED': 'bg-red-500',
        'SHIPPED': 'bg-blue-500',
        'DELIVERED': 'bg-purple-500'
      };
      return dotClasses[status] || 'bg-gray-500';
    },
    viewOrderDetails(order) {
      console.log("View order details:", order);
      this.$router.push(`/orders/${order.id}`);
    },
    async cancelOrder(orderId) {
      try {
        const result = await OrderService.cancelOrder(orderId);
        if (result.success) {
          this.$parent.showToast(result.message, 'success');
          await this.fetchOrders();
        } else {
          this.$parent.showToast(result.message, 'error');
        }
      } catch (error) {
        console.error("Error cancelling order:", error);
        this.$parent.showToast('Failed to cancel order', 'error');
      }
    },
    reorder(order) {
      console.log("Reorder:", order);
      this.$parent.showToast('Reorder functionality coming soon!', 'info');
    },
    trackOrder(orderId) {
      console.log("Track order:", orderId);
      this.$parent.showToast('Order tracking coming soon!', 'info');
    }
  }
};
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>