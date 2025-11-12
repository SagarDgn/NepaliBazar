<template>
  <div
    class="group bg-white rounded shadow-sm hover:shadow-lg transition-all duration-300 transform hover:-translate-y-1 overflow-hidden border border-gray-200  "
  >
    <!-- Custom Notification -->
    <div
      v-if="showNotification"
      class="fixed top-4 right-4 z-50 transform transition-all duration-500"
      :class="notificationClass"
    >
      <div class="flex items-center space-x-3 px-6 py-4 rounded-lg shadow-xl bg-white border border-gray-200">
        <div class="flex-shrink-0">
          <div class="w-6 h-6 rounded-full flex items-center justify-center" :class="notificationIconStyle">
            <svg v-if="notificationType === 'success'" class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
            </svg>
            <svg v-else class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
          </div>
        </div>
        <div class="flex-1 min-w-0">
          <p class="text-sm font-medium" :class="notificationTextStyle">
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
    <div class="relative h-56 bg-gray-50 flex items-center justify-center overflow-hidden">
      <!-- Image -->
      <div class="relative w-full h-full">
        <img
          v-if="product.imageUrl"
          :src="product.imageUrl"
          alt="product image"
          class="w-full h-full object-cover transition-transform duration-500 group-hover:scale-105"
        />
        <div v-else class="flex flex-col items-center justify-center h-full text-gray-400">
          <svg class="w-12 h-12 mb-2 opacity-50" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"/>
          </svg>
          <span class="text-xs font-medium">No Image</span>
        </div>

        <!-- Overlay on Hover -->
        <div class="absolute inset-0 bg-black/10 opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
      </div>

      
      <!-- Wishlist Button -->
      <button
        @click="handleWishlist"
        class="absolute top-3 right-3 p-2 rounded-lg bg-white/80 backdrop-blur-sm hover:bg-gray-100 transition-all duration-300 shadow-sm"
        :class="{ 
          'bg-gray-100': isWishlisted, 
        }"
        :disabled="isWishlistLoading"
      >
        <div class="relative">
          <HeartIcon
            v-if="isWishlisted"
            class="w-4 h-4 text-gray-900 transition-all duration-300"
            :class="isWishlistLoading ? 'animate-pulse' : ''"
          />
          <HeartOffIcon
            v-else
            class="w-4 h-4 text-gray-600 hover:text-gray-900 transition-all duration-300"
            :class="isWishlistLoading ? 'animate-pulse' : ''"
          />
        </div>
      </button>
      
      <!-- View Details Button -->
      <button
        @click="handleViewDetails"
        class="absolute bottom-3 left-3 px-3 py-1.5 bg-gray-900 text-white rounded-lg text-xs font-medium opacity-0 group-hover:opacity-100 transition-all duration-300 hover:bg-gray-800 shadow-sm flex items-center space-x-1"
      >
        <Eye class="w-3 h-3" />
        <span>View</span>
      </button>

      <!-- Quick Add to Cart Button -->
      <button
        @click="handleAddToCart"
        :disabled="isSeller || isCartLoading"
        class="absolute bottom-3 right-3 px-3 py-1.5 bg-gray-900 text-white rounded-lg text-xs font-medium opacity-0 group-hover:opacity-100 transition-all duration-300 hover:bg-gray-800 disabled:opacity-50 disabled:cursor-not-allowed shadow-sm flex items-center space-x-1"
      >
        <ShoppingBasket class="w-3 h-3" :class="isCartLoading ? 'animate-spin' : ''" />
        <span>{{ isCartLoading ? '...' : 'Add' }}</span>
      </button>

      <!-- Seller Badge -->
      <div v-if="product.sellerName" class="absolute top-3 left-3 flex gap-3 rounded">
        <span class="px-2 py-1  text-white text-sm font-medium rounded backdrop-blur-sm  ">
          {{ product.sellerName }}
        </span>
      </div>

      <!-- Success Animation Overlay -->
      <div
        v-if="showSuccessAnimation"
        class="absolute inset-0 bg-white/90 flex items-center justify-center backdrop-blur-sm "
      >
        <div class="text-gray-900 text-center">
          <div class="w-12 h-12 mx-auto mb-2 bg-gray-900 rounded-full flex items-center justify-center">
            <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
            </svg>
          </div>
          <p class="font-semibold text-sm">Added!</p>
        </div>
      </div>

      <!-- Discount Badge -->
      <div
        v-if="product.originalPrice && product.originalPrice > product.price"
        class="absolute top-3 right-12 px-2 py-1 bg-gray-900 text-white text-xs font-bold rounded"
      >
        -{{ calculateDiscount(product.originalPrice, product.price) }}%
      </div>
    </div>

    <!-- Product Info -->
    <div class="p-4 bg-black text-white">
      <div class="space-y-3">
        <!-- Product Name and Description -->
        <div>
          <h3 class="text-light font-semibold text-white mb-1 line-clamp-1">
            {{ product.name }}
          </h3>
          <p
            v-if="product.description"
            class="text-gray-300 text-sm leading-relaxed line-clamp-2"
          >
            {{ product.description }}
          </p>
        </div>

        <!-- Price Section -->
        <div class="flex items-center justify-between">
          <div class="flex flex-col ">
            <span class="text-md text-gray-500 line-through ">NPR {{ product.price.toLocaleString() }}/-</span>
            <span v-if="product.originalPrice" class="text-xs text-white line-through">
              NPR {{ product.originalPrice.toLocaleString() }}/-
            </span>
            <span class="text-md font-medium text-white">NPR {{ product.discountPrice }}/-</span>
            
          </div>
          <div class="mt ml-10">
              <span class="font-light text-sm  mt-5 pt-5">{{ product.discount }}% off</span>
          </div>

          <!-- Add to Cart Button -->
          <button
            @click="handleAddToCart"
            :disabled="isSeller || isCartLoading"
            class="p-2 rounded-lg bg-gray-900   hover:bg-gray-800 text-white transition-all duration-300 disabled:opacity-50 disabled:cursor-not-allowed shadow-sm"
            :class="isCartLoading ? 'animate-pulse' : ''"
          >
            <ShoppingBasket class="w-4 h-4" :class="isCartLoading ? 'animate-spin' : ''" />
          </button>
        </div>

        <!-- Additional Info -->
        <div class="flex items-center justify-between text-xs text-gray-500 pt-2 border-t border-gray-100">
          <span v-if="product.stock !== undefined" :class="product.stock > 0 ? 'text-gray-600' : 'text-gray-400'">
            {{ product.stock > 0 ? `${product.stock} in stock` : 'Out of stock' }}
          </span>
          <span v-if="product.rating" class="flex items-center space-x-1">
            <svg class="w-3 h-3 text-gray-600" fill="currentColor" viewBox="0 0 20 20">
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"/>
            </svg>
            <span>{{ product.rating }}</span>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { HeartIcon, HeartOffIcon, ShoppingBasket, Eye } from "lucide-vue-next";
import WishlistService from "../services/WishlistService";
import CartService from "../services/CartService";

export default {
  name: "ProductCard",
  components: { 
    HeartIcon, 
    HeartOffIcon, 
    ShoppingBasket, 
    Eye 
  },
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
      return sessionStorage.getItem("seller_role") === "SELLER";
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
        ? 'bg-gray-100 text-gray-900' 
        : 'bg-gray-100 text-gray-900';
    },
    notificationIconStyle() {
      return this.notificationType === 'success' 
        ? 'bg-gray-200 text-gray-700' 
        : 'bg-gray-200 text-gray-700';
    },
    notificationTextStyle() {
      return this.notificationType === 'success' 
        ? 'text-gray-900' 
        : 'text-gray-900';
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

    handleViewDetails() {
      // Navigate to product details page
    this.$router.push(`/product/${this.product.id}`);
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
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.shadow-sm {
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
}

.hover\:shadow-lg:hover {
  box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
}
</style>