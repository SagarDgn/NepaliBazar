<template>
  <div class="min-h-screen">
    <Hero />
    <Navbar />
    
    <!-- Custom Notification -->
    <div
      v-if="showNotification"
      class="fixed top-4 right-4 z-50 transform transition-all duration-500"
      :class="notificationClass"
    >
      <div class="flex items-center space-x-3 px-6 py-4 rounded-2xl shadow-2xl backdrop-blur-sm border" :class="notificationStyle">
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

    <!-- Clear Cart Confirmation Modal -->
    <div
      v-if="showConfirmation"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4"
    >
      <div class="bg-white rounded-2xl p-6 max-w-md w-full mx-auto transform transition-all duration-300 scale-100">
        <div class="text-center">
          <div class="w-16 h-16 mx-auto mb-4 bg-red-100 rounded-full flex items-center justify-center">
            <svg class="w-8 h-8 text-red-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/>
            </svg>
          </div>
          <h3 class="text-xl font-semibold text-gray-800 mb-2">Clear Cart</h3>
          <p class="text-gray-600 mb-6">Are you sure you want to remove all items from your cart? This action cannot be undone.</p>
          <div class="flex gap-3 justify-center">
            <button
              @click="cancelClear"
              class="px-6 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors duration-200 font-medium"
            >
              Cancel
            </button>
            <button
              @click="confirmClear"
              class="px-6 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-colors duration-200 font-medium"
            >
              Clear All
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Remove Item Confirmation Modal -->
    <div
      v-if="showRemoveConfirmationModal"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4"
    >
      <div class="bg-white rounded-2xl p-6 max-w-md w-full mx-auto transform transition-all duration-300 scale-100">
        <div class="text-center">
          <div class="w-16 h-16 mx-auto mb-4 bg-orange-100 rounded-full flex items-center justify-center">
            <svg class="w-8 h-8 text-orange-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/>
            </svg>
          </div>
          <h3 class="text-xl font-semibold text-gray-800 mb-2">Remove Item</h3>
          <p class="text-gray-600 mb-6">Are you sure you want to remove "{{ productNameToRemove }}" from your cart?</p>
          <div class="flex gap-3 justify-center">
            <button
              @click="cancelRemove"
              class="px-6 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors duration-200 font-medium"
            >
              Cancel
            </button>
            <button
              @click="confirmRemove"
              class="px-6 py-2 bg-orange-600 text-white rounded-lg hover:bg-orange-700 transition-colors duration-200 font-medium"
            >
              Remove Item
            </button>
          </div>
        </div>
      </div>
    </div>
    
    <div class="container mx-auto px-4 py-8 max-w-6xl">
      <!-- Page Header -->
      <div class="mb-8 text-center">
        <h1 class="text-3xl md:text-4xl font-bold text-gray-800 mb-2">Your Shopping Cart</h1>
        <p class="text-gray-600">Review and manage your items</p>
      </div>

      <!-- Empty Cart State -->
      <div v-if="mergedCartItems.length === 0" class="text-center py-16">
        <div class="max-w-md mx-auto">
          <div class="w-24 h-24 mx-auto mb-6 text-gray-400">
            <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4m0 0L7 13m0 0l-2.5 5.5M7 13l2.5 5.5m0 0L17 21m-7.5-2.5h9M17 21v-6m0 6l-2.5-5.5"/>
            </svg>
          </div>
          <h3 class="text-xl font-semibold text-gray-700 mb-2">Your cart is empty</h3>
          <p class="text-gray-500 mb-6">Start shopping to add items to your cart</p>
          <button 
            @click="$router.push('/products')"
            class="px-6 py-3 bg-gradient-to-r from-blue-600 to-purple-600 text-white rounded-lg hover:from-blue-700 hover:to-purple-700 transition-all duration-200 transform hover:scale-105 font-medium shadow-lg"
          >
            Continue Shopping
          </button>
        </div>
      </div>

      <!-- Cart with Items -->
      <div v-else class="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <!-- Cart Items Section -->
        <div class="lg:col-span-2">
          <div class="bg-white rounded-2xl shadow-lg border border-gray-200 overflow-hidden">
            <!-- Cart Header -->
            <div class="p-6 border-b border-gray-200 bg-gradient-to-r from-blue-50 to-indigo-50">
              <div class="flex justify-between items-center">
                <h2 class="text-xl font-semibold text-gray-800">Cart Items ({{ totalItems }})</h2>
                <button
                  @click.stop="showClearConfirmation"
                  class="px-4 py-2 text-red-600 hover:bg-red-50 rounded-lg transition-all duration-200 font-medium border border-red-200 hover:border-red-300"
                >
                  Clear All
                </button>
              </div>
            </div>

            <!-- Cart Items List -->
            <div class="divide-y divide-gray-100">
              <div
                v-for="(item, index) in mergedCartItems"
                :key="item.productId + '-' + index"
                class="p-6 hover:bg-gray-50 transition-all duration-200 group"
              >
                <div class="flex items-center space-x-4">
                  <!-- Product Image Placeholder -->
                  <div class="w-20 h-20 bg-gradient-to-br from-blue-100 to-purple-100 rounded-xl flex items-center justify-center group-hover:scale-105 transition-transform duration-200">
                    <span class="text-2xl">🛒</span>
                  </div>

                  <!-- Product Details -->
                  <div class="flex-1 min-w-0">
                    <div class="flex items-start justify-between">
                      <div>
                        <h3 class="text-lg font-semibold text-gray-800 truncate">{{ item.productName }}</h3>
                        <p class="text-gray-600 mt-1">Rs. {{ item.price.toLocaleString() }} per item</p>
                      </div>
                      <!-- Item Total -->
                      <div class="text-right">
                        <p class="text-lg font-semibold text-blue-600">
                          Rs. {{ (item.price * item.quantity).toLocaleString() }}
                        </p>
                        <p class="text-sm text-gray-500">
                          {{ item.quantity }} × Rs. {{ item.price.toLocaleString() }}
                        </p>
                      </div>
                    </div>
                    
                    <!-- Quantity Controls -->
                    <div class="flex items-center space-x-3 mt-4">
                      <button
                        @click.stop="updateQuantity(item.productId, item.quantity - 1)"
                        :disabled="item.quantity <= 1 || isLoading"
                        class="w-10 h-10 flex items-center justify-center bg-gray-100 hover:bg-gray-200 rounded-xl transition-all duration-200 disabled:opacity-50 disabled:cursor-not-allowed hover:shadow-md"
                        :class="{ 'hover:bg-gray-200': item.quantity > 1 && !isLoading }"
                      >
                        <span class="text-gray-600 font-semibold text-lg">−</span>
                      </button>
                      
                      <div class="flex flex-col items-center">
                        <span class="w-16 text-center font-semibold text-gray-700 bg-gray-50 py-2 rounded-xl text-lg border border-gray-200">
                          {{ item.quantity }}
                        </span>
                        <span class="text-xs text-gray-500 mt-1">quantity</span>
                      </div>
                      
                      <button
                        @click.stop="updateQuantity(item.productId, item.quantity + 1)"
                        :disabled="isLoading"
                        class="w-10 h-10 flex items-center justify-center bg-gray-100 hover:bg-gray-200 rounded-xl transition-all duration-200 disabled:opacity-50 disabled:cursor-not-allowed hover:shadow-md"
                      >
                        <span class="text-gray-600 font-semibold text-lg">+</span>
                      </button>

                      <!-- Remove Button -->
                      <button
                        @click.stop="showRemoveConfirmation(item.productId, item.productName)"
                        :disabled="isLoading"
                        class="ml-4 px-4 py-2 bg-red-50 text-red-600 hover:bg-red-100 rounded-xl transition-all duration-200 flex items-center space-x-2 disabled:opacity-50 disabled:cursor-not-allowed border border-red-200 hover:border-red-300 hover:shadow-md"
                      >
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/>
                        </svg>
                        <span>Remove</span>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Order Summary -->
        <div class="lg:col-span-1">
          <div class="bg-white rounded-2xl shadow-lg border border-gray-200 sticky top-4">
            <!-- Summary Header -->
            <div class="p-6 border-b border-gray-200 bg-gradient-to-r from-green-50 to-emerald-50">
              <h2 class="text-xl font-semibold text-gray-800">Order Summary</h2>
            </div>

            <!-- Pricing Details -->
            <div class="p-6 space-y-4">
              <div class="flex justify-between items-center">
                <span class="text-gray-600">Items ({{ mergedCartItems.length }})</span>
                <span class="text-gray-800 font-medium">{{ totalItems }} items</span>
              </div>
              
              <div class="flex justify-between items-center">
                <span class="text-gray-600">Subtotal</span>
                <span class="text-gray-800 font-medium">Rs. {{ totalMrpPrice.toLocaleString() }}</span>
              </div>
              
              <div v-if="totalMrpPrice > totalSellingPrice" class="flex justify-between items-center text-green-600 bg-green-50 rounded-lg p-3">
                <span class="font-medium">Discount</span>
                <span class="font-semibold">- Rs. {{ (totalMrpPrice - totalSellingPrice).toLocaleString() }}</span>
              </div>
              
              <div class="border-t border-gray-200 pt-4">
                <div class="flex justify-between items-center text-lg font-semibold">
                  <span class="text-gray-800">Total Amount</span>
                  <span class="text-green-600">Rs. {{ totalSellingPrice.toLocaleString() }}</span>
                </div>
                <p class="text-sm text-gray-500 mt-1">Inclusive of all taxes</p>
              </div>

              <!-- Savings Badge -->
              <div v-if="totalMrpPrice > totalSellingPrice" class="bg-gradient-to-r from-green-500 to-emerald-500 text-white rounded-lg p-4 text-center">
                <div class="flex items-center justify-center space-x-2">
                  <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"/>
                  </svg>
                  <span class="font-semibold">
                    You save Rs. {{ (totalMrpPrice - totalSellingPrice).toLocaleString() }}
                  </span>
                </div>
              </div>
            </div>

            <!-- Action Buttons -->
            <div class="p-6 border-t border-gray-200 space-y-3">
              <button
                @click="placeOrder"
                :disabled="isLoading || mergedCartItems.length === 0"
                class="w-full py-3 bg-gradient-to-r from-red-600 to-red-600 hover:from-red-700 hover:to-red-700 text-white rounded-xl font-semibold transition-all duration-200 transform hover:scale-[1.02] shadow-lg disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none"
              >
                <span v-if="!isLoading" class="flex items-center justify-center space-x-2">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                  </svg>
                  <span>Place Order • Rs. {{ totalSellingPrice.toLocaleString() }}</span>
                </span>
                <span v-else class="flex items-center justify-center space-x-2">
                  <svg class="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  <span>Processing...</span>
                </span>
              </button>
              
              <button
                @click="$router.push('/products')"
                :disabled="isLoading"
                class="w-full py-3 border border-gray-300 text-gray-700 hover:bg-gray-50 rounded-xl font-medium transition-all duration-200 hover:shadow-md disabled:opacity-50 disabled:cursor-not-allowed"
              >
                Continue Shopping
              </button>
            </div>

            <!-- Trust Badges -->
            <div class="px-6 pb-6">
              <div class="flex items-center justify-center space-x-6 text-gray-500 text-sm">
                <div class="flex items-center space-x-1">
                  <svg class="w-4 h-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M2.166 4.999A11.954 11.954 0 0010 1.944 11.954 11.954 0 0017.834 5c.11.65.166 1.32.166 2.001 0 5.225-3.34 9.67-8 11.317C5.34 16.67 2 12.225 2 7c0-.682.057-1.35.166-2.001zm11.541 3.708a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"/>
                  </svg>
                  <span>Secure Checkout</span>
                </div>
                <div class="flex items-center space-x-1">
                  <svg class="w-4 h-4 text-blue-500" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M4 4a2 2 0 00-2 2v4a2 2 0 002 2V6h10a2 2 0 00-2-2H4zm2 6a2 2 0 012-2h8a2 2 0 012 2v4a2 2 0 01-2 2H8a2 2 0 01-2-2v-4zm6 4a2 2 0 100-4 2 2 0 000 4z" clip-rule="evenodd"/>
                  </svg>
                  <span>Easy Returns</span>
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
import Footer from "../components/ui/Footer.vue";
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";
import CartService from "../services/CartService";

export default {
  components: { Navbar, Hero, Footer },
  name: "CartItemPage",
  data() {
    return {
      cartItems: [],
      totalItems: 0,
      totalMrpPrice: 0,
      totalSellingPrice: 0,
      isLoading: false,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success',
      showConfirmation: false,
      showRemoveConfirmationModal: false,
      productToRemove: null,
      productNameToRemove: '',
      notificationTimeout: null
    };
  },
  computed: {
    // Merge duplicate products and sum their quantities
    mergedCartItems() {
      const merged = {};
      
      this.cartItems.forEach(item => {
        if (merged[item.productId]) {
          merged[item.productId].quantity += item.quantity;
        } else {
          merged[item.productId] = {
            ...item,
            quantity: item.quantity
          };
        }
      });
      
      return Object.values(merged);
    },
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
  mounted() {
    this.fetchCart();
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

    showClearConfirmation() {
      this.showConfirmation = true;
    },

    cancelClear() {
      this.showConfirmation = false;
    },

    async confirmClear() {
      this.showConfirmation = false;
      await this.clearCart();
    },

    showRemoveConfirmation(productId, productName) {
      this.productToRemove = productId;
      this.productNameToRemove = productName;
      this.showRemoveConfirmationModal = true;
    },

    cancelRemove() {
      this.showRemoveConfirmationModal = false;
      this.productToRemove = null;
      this.productNameToRemove = '';
    },

    async confirmRemove() {
      if (this.productToRemove) {
        this.showRemoveConfirmationModal = false;
        await this.removeItem(this.productToRemove);
        this.productToRemove = null;
        this.productNameToRemove = '';
      }
    },

    async fetchCart() {
      this.isLoading = true;
      try {
        const result = await CartService.getCartItems();
        console.log("Cart fetch result:", result);

        if (result.code === "0" && result.data) {
          const cartData = result.data;
          
          this.cartItems = cartData.cartItems || [];
          this.totalItems = cartData.totalItems || 0;
          this.totalMrpPrice = cartData.totalMrpPrice || 0;
          this.totalSellingPrice = cartData.totalSellingPrice || 0;
        } else {
          this.showCustomNotification(result.message || "Failed to load cart", 'error');
        }
      } catch (err) {
        console.error("Cart fetch error:", err);
        this.showCustomNotification("Failed to load cart. Please try again.", 'error');
      } finally {
        this.isLoading = false;
      }
    },

    async updateQuantity(productId, quantity) {
      if (quantity < 1) return;
      
      this.isLoading = true;
      try {
        // Update quantity locally since we don't have backend API
        const itemIndex = this.cartItems.findIndex(item => item.productId === productId);
        if (itemIndex !== -1) {
          this.cartItems[itemIndex].quantity = quantity;
          
          // Recalculate totals
          this.calculateTotals();
          
          this.showCustomNotification("Quantity updated successfully! ", 'success');
        }
      } catch (error) {
        console.error("Update quantity error:", error);
        this.showCustomNotification("Failed to update quantity. Please try again.", 'error');
      } finally {
        this.isLoading = false;
      }
    },

    calculateTotals() {
      // Recalculate all totals based on current cart items
      this.totalItems = this.cartItems.reduce((sum, item) => sum + item.quantity, 0);
      this.totalMrpPrice = this.cartItems.reduce((sum, item) => sum + (item.price * item.quantity), 0);
      this.totalSellingPrice = this.totalMrpPrice; // Assuming no discounts for now
    },

    async removeItem(productId) {
      this.isLoading = true;
      try {
        const result = await CartService.removeCartItem(productId);
        if (result.success) {
          this.showCustomNotification("Item removed from cart 🗑️", 'success');
          await this.fetchCart();
        } else {
          this.showCustomNotification(result.message, 'error');
        }
      } catch (error) {
        console.error("Remove item error:", error);
        this.showCustomNotification("Failed to remove item. Please try again.", 'error');
      } finally {
        this.isLoading = false;
      }
    },

    async clearCart() {
      this.isLoading = true;
      try {
        const result = await CartService.clearCart();
        if (result.success) {
          this.showCustomNotification("Cart cleared successfully! ", 'success');
          await this.fetchCart();
        } else {
          this.showCustomNotification(result.message, 'error');
        }
      } catch (error) {
        console.error("Clear cart error:", error);
        this.showCustomNotification("Failed to clear cart. Please try again.", 'error');
      } finally {
        this.isLoading = false;
      }
    },

    async placeOrder() {
      if (this.mergedCartItems.length === 0) {
        this.showCustomNotification("Your cart is empty. Add some items first! ", 'error');
        return;
      }

      this.isLoading = true;
      try {
        // Simulate order placement
        await new Promise(resolve => setTimeout(resolve, 2000));
        
        this.showCustomNotification(" Order placed successfully! Thank you for your purchase!", 'success');
        
        // Clear cart after successful order
        await this.clearCart();
        
      } catch (error) {
        console.error("Place order error:", error);
        this.showCustomNotification("Failed to place order. Please try again.", 'error');
      } finally {
        this.isLoading = false;
      }
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 1200px;
}

/* Smooth transitions for all interactive elements */
button {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Custom animations */
@keyframes slideIn {
  from {
    transform: translateX(100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

@keyframes slideOut {
  from {
    transform: translateX(0);
    opacity: 1;
  }
  to {
    transform: translateX(100%);
    opacity: 0;
  }
}

/* Prevent event propagation issues */
button {
  position: relative;
  z-index: 1;
}
</style>