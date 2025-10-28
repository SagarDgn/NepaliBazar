<template>
  <div
    class="group bg-white rounded-3xl shadow-lg hover:shadow-2xl transition-all duration-500 transform hover:-translate-y-2 overflow-hidden border border-gray-100"
  >
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

    <!-- Product Image with Overlay Effects -->
    <div class="relative h-64 bg-gradient-to-br from-gray-50 to-gray-100 flex items-center justify-center overflow-hidden">
      <!-- Image -->
      <div class="relative w-full h-full">
        <img
          v-if="product.imageUrl"
          :src="product.imageUrl"
          alt="product image"
          class="w-full h-full object-cover transition-transform duration-700 group-hover:scale-110"
        />
        <div v-else class="flex flex-col items-center justify-center h-full text-gray-400">
          <svg class="w-16 h-16 mb-2 opacity-50" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"/>
          </svg>
          <span class="text-sm font-medium">No Image</span>
        </div>

        <!-- Gradient Overlay on Hover -->
        <div class="absolute inset-0 bg-gradient-to-t from-black/20 to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
      </div>

      <!-- Wishlist Button -->
      <button
        @click="handleWishlist"
        class="absolute top-4 right-4 p-3 rounded-2xl bg-white/90 backdrop-blur-sm hover:bg-red-50 transition-all duration-300 transform hover:scale-110 shadow-lg"
        :class="{ 
          'bg-red-50': isWishlisted, 
          'animate-pulse': isWishlistLoading 
        }"
        :disabled="isWishlistLoading"
      >
        <div class="relative">
          <HeartIcon
            v-if="isWishlisted"
            class="w-5 h-5 text-red-600 transition-all duration-300"
            :class="isWishlistLoading ? 'animate-spin' : ''"
          />
          <HeartOffIcon
            v-else
            class="w-5 h-5 text-gray-600 group-hover:text-red-500 transition-all duration-300"
            :class="isWishlistLoading ? 'animate-spin' : ''"
          />
        </div>
      </button>

      <!-- Quick Add to Cart Button (Appears on Hover) -->
      <button
        @click="handleAddToCart"
        :disabled="isSeller || isCartLoading"
        class="absolute bottom-4 left-1/2 transform -translate-x-1/2 px-6 py-3 bg-gradient-to-r from-red-600 to-red-700 text-white rounded-2xl font-semibold shadow-lg opacity-0 group-hover:opacity-100 transition-all duration-300 hover:from-red-700 hover:to-red-800 disabled:opacity-50 disabled:cursor-not-allowed hover:shadow-xl hover:scale-105"
      >
        <div class="flex items-center space-x-2">
          <ShoppingBasket class="w-4 h-4" :class="isCartLoading ? 'animate-spin' : ''" />
          <span>{{ isCartLoading ? 'Adding...' : 'Add to Cart' }}</span>
        </div>
      </button>

      <!-- Seller Badge -->
      <div v-if="product.sellerName" class="absolute top-4 left-4">
        <span class="px-3 py-1.5 bg-black/80 text-white text-xs font-medium rounded-full backdrop-blur-sm">
          {{ product.sellerName }}
        </span>
      </div>

      <!-- Success Animation Overlay -->
      <div
        v-if="showSuccessAnimation"
        class="absolute inset-0 bg-green-500/20 flex items-center justify-center backdrop-blur-sm"
      >
        <div class="text-white text-center">
          <div class="w-16 h-16 mx-auto mb-2 bg-green-500 rounded-full flex items-center justify-center animate-bounce">
            <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
            </svg>
          </div>
          <p class="font-semibold text-lg">Added to Cart!</p>
        </div>
      </div>
    </div>

    <!-- Product Info -->
    <div class="p-6 bg-gradient-to-br from-black to-gray-900 text-white relative">
      <!-- Background Pattern -->
      <div class="absolute inset-0 opacity-5 bg-gradient-to-r from-white to-transparent"></div>
      
      <div class="relative z-10">
        <!-- Product Name and Description -->
        <div class="mb-4">
          <h3 class="text-xl font-bold mb-2 line-clamp-1 group-hover:text-red-100 transition-colors">
            {{ product.name }}
          </h3>
          <p
            v-if="product.description"
            class="text-gray-300 text-sm leading-relaxed line-clamp-2 group-hover:text-gray-200 transition-colors"
          >
            {{ product.description }}
          </p>
        </div>

        <!-- Price and Rating Section -->
        <div class="flex items-center justify-between">
          <!-- Price -->
          <div class="flex flex-col">
            <span class="text-2xl font-bold text-white">Rs. {{ product.price.toLocaleString() }}</span>
            <span v-if="product.originalPrice" class="text-sm text-gray-400 line-through">
              Rs. {{ product.originalPrice.toLocaleString() }}
            </span>
          </div>

          <!-- Discount Badge -->
          <div
            v-if="product.originalPrice && product.originalPrice > product.price"
            class="absolute -top-3 right-6 px-3 py-1 bg-gradient-to-r from-green-500 to-emerald-600 text-white text-xs font-bold rounded-full shadow-lg"
          >
            Save {{ calculateDiscount(product.originalPrice, product.price) }}%
          </div>

          <!-- Add to Cart Button (Mobile) -->
          <button
            @click="handleAddToCart"
            :disabled="isSeller || isCartLoading"
            class="lg:hidden p-3 rounded-2xl bg-gradient-to-r from-red-600 to-red-700 hover:from-red-700 hover:to-red-800 text-white transition-all duration-300 disabled:opacity-50 disabled:cursor-not-allowed shadow-lg hover:shadow-xl hover:scale-105"
            :class="isCartLoading ? 'animate-pulse' : ''"
          >
            <ShoppingBasket class="w-5 h-5" :class="isCartLoading ? 'animate-spin' : ''" />
          </button>

          <!-- Add to Cart Button (Desktop) -->
          <button
            @click="handleAddToCart"
            :disabled="isSeller || isCartLoading"
            class="hidden lg:flex items-center space-x-2 px-4 py-3 rounded-2xl bg-gradient-to-r from-red-600 to-red-700 hover:from-red-700 hover:to-red-800 text-white transition-all duration-300 disabled:opacity-50 disabled:cursor-not-allowed shadow-lg hover:shadow-xl hover:scale-105"
            :class="isCartLoading ? 'animate-pulse' : ''"
          >
            <ShoppingBasket class="w-4 h-4" :class="isCartLoading ? 'animate-spin' : ''" />
            <span class="font-semibold text-sm">{{ isCartLoading ? 'Adding...' : 'Add' }}</span>
          </button>
        </div>

        <!-- Additional Info -->
        <div class="mt-4 pt-4 border-t border-gray-700/50">
          <div class="flex items-center justify-between text-xs text-gray-400">
            <span v-if="product.stock !== undefined" :class="product.stock > 0 ? 'text-green-400' : 'text-red-400'">
              {{ product.stock > 0 ? `${product.stock} in stock` : 'Out of stock' }}
            </span>
            <span v-if="product.rating" class="flex items-center space-x-1">
              <svg class="w-4 h-4 text-yellow-400" fill="currentColor" viewBox="0 0 20 20">
                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"/>
              </svg>
              <span>{{ product.rating }}</span>
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { HeartIcon, HeartOffIcon, ShoppingBasket } from "lucide-vue-next";
import WishlistService from "../services/WishlistService";
import CartService from "../services/CartService";

export default {
  name: "ProductCard",
  components: { HeartIcon, HeartOffIcon, ShoppingBasket },
  props: {
    product: { 
      type: Object, 
      required: true,
      default: () => ({
        wishlisted: false
      })
    },
  },
  data() {
    return {
      isWishlisted: this.product.wishlisted || false,
      isWishlistLoading: false,
      isCartLoading: false,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success',
      showSuccessAnimation: false,
      notificationTimeout: null
    };
  },
  computed: {
    isSeller() {
      return localStorage.getItem("seller_role") === "SELLER";
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
  watch: {
    'product.wishlisted': {
      immediate: true,
      handler(newVal) {
        this.isWishlisted = newVal;
      }
    }
  },
  methods: {
    calculateDiscount(original, current) {
      return Math.round(((original - current) / original) * 100);
    },

    showCustomNotification(message, type = 'success') {
      this.notificationMessage = message;
      this.notificationType = type;
      this.showNotification = true;

      clearTimeout(this.notificationTimeout);
      this.notificationTimeout = setTimeout(() => {
        this.hideNotification();
      }, 3000);
    },

    hideNotification() {
      this.showNotification = false;
      clearTimeout(this.notificationTimeout);
    },

    async handleAddToCart() {
      if (this.isSeller) {
        this.showCustomNotification("Sellers cannot add products to cart!", 'error');
        return;
      }

      this.isCartLoading = true;

      try {
        const result = await CartService.addToCart(this.product.id);
        
        if (result && result.success === true) {
          this.showSuccessAnimation = true;
          setTimeout(() => {
            this.showSuccessAnimation = false;
          }, 2000);

          const successMessage = result.message || " Added to cart successfully!";
          this.showCustomNotification(successMessage);

        } else {
          const errorMessage = result?.message || "Failed to add item to cart. Please try again.";
          this.showCustomNotification(errorMessage, 'error');
        }
      } catch (error) {
        console.error("Cart error:", error);
        let errorMessage = "Something went wrong while adding to cart!";
        
        if (error.message && error.message.includes("Cannot read properties of undefined (reading 'add')")) {
          errorMessage = "Cart service is temporarily unavailable. Please try again.";
        } else if (error.response?.data?.message) {
          errorMessage = error.response.data.message;
        } else if (error.message) {
          errorMessage = error.message;
        }
        
        this.showCustomNotification(errorMessage, 'error');
      } finally {
        this.isCartLoading = false;
      }
    },

    async handleWishlist() {
      if (this.isSeller) {
        this.showCustomNotification("Sellers cannot add products to wishlist!", "error");
        return;
      }

      const token = localStorage.getItem("buyer_jwt");
      if (!token) {
        this.showCustomNotification("Please log in as a customer to use wishlist!", "error");
        return;
      }

      this.isWishlistLoading = true;

      try {
        console.log(" BEFORE - isWishlisted:", this.isWishlisted);
        
        const result = await WishlistService.toggleWishlist(this.product.id);
        console.log(" API Response:", result);

        if (result.success) {
          const previousState = this.isWishlisted;
          this.isWishlisted = result.isWishlisted;
          
          console.log(" AFTER - isWishlisted:", this.isWishlisted);
          console.log(" STATE CHANGE:", previousState, "→", this.isWishlisted);

          if (previousState && !this.isWishlisted) {
            this.showCustomNotification("Removed from wishlist ", "success");
          } else if (!previousState && this.isWishlisted) {
            this.showCustomNotification("Added to wishlist ", "success");
          }

          this.$emit("wishlist", { 
            id: this.product.id, 
            wishlisted: this.isWishlisted 
          });
        } else {
          this.showCustomNotification(result.message || "Failed to update wishlist", "error");
        }
      } catch (error) {
        console.error("Wishlist error:", error);
        this.showCustomNotification("Something went wrong!", "error");
      } finally {
        this.isWishlistLoading = false;
      }
    }
  },
};
</script>

<style scoped>
.line-clamp-1 {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.group {
  transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.shadow-lg {
  box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
}

.hover\:shadow-2xl:hover {
  box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.25);
}
</style>