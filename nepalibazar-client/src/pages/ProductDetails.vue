<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900">
    <!-- Navigation Header -->
    <nav class="bg-white dark:bg-gray-800 shadow-sm border-b border-gray-200 dark:border-gray-700">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center h-16">
          <button 
            @click="$router.back()" 
            class="flex items-center space-x-2 text-gray-600 dark:text-gray-300 hover:text-gray-900 dark:hover:text-white transition-colors duration-200"
          >
            <ArrowLeft class="w-5 h-5" />
            <span>Back to Products</span>
          </button>
        </div>
      </div>
    </nav>

    <!-- Loading State -->
    <div v-if="loading" class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="animate-pulse">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <div class="bg-gray-300 dark:bg-gray-700 rounded-2xl h-96"></div>
          <div class="space-y-4">
            <div class="h-8 bg-gray-300 dark:bg-gray-700 rounded"></div>
            <div class="h-4 bg-gray-300 dark:bg-gray-700 rounded"></div>
            <div class="h-6 bg-gray-300 dark:bg-gray-700 rounded w-1/3"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- Product Details Section -->
    <div v-else class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="bg-white dark:bg-gray-800 rounded-3xl shadow-lg overflow-hidden">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 p-8">
          <!-- Product Images -->
          <div class="space-y-4">
            <!-- Main Image -->
            <div class="rounded-2xl overflow-hidden bg-gray-100 dark:bg-gray-700 aspect-square">
              <img
                :src="product.imageUrl || '/placeholder-image.jpg'"
                :alt="product.name"
                class="w-full h-full object-cover"
              />
            </div>
          </div>

          <!-- Product Information -->
          <div class="space-y-6">
            <!-- Product Header -->
            <div>
              <h1 class="text-3xl lg:text-4xl font-bold text-gray-900 dark:text-white mb-4">
                {{ product.name }}
              </h1>
              
              <!-- Stock Status -->
              <div class="mb-4">
                <span class="text-sm text-green-600 dark:text-green-400 font-semibold" v-if="product.stock > 0">
                  ✓ In Stock
                </span>
                <span class="text-sm text-red-600 dark:text-red-400 font-semibold" v-else>
                  ✗ Out of Stock
                </span>
              </div>
            </div>

            <!-- Price Section -->
            <div class="flex items-center space-x-4">
              <span class="text-4xl font-bold text-green-600 dark:text-green-400">
                Rs. {{ (product.price || 0).toLocaleString() }}
              </span>
              
              <span v-if="product.originalPrice" class="text-2xl text-gray-500 line-through">
                Rs. {{ product.originalPrice.toLocaleString() }}
              </span>
              
              <span 
                v-if="product.originalPrice && product.originalPrice > product.price" 
                class="px-3 py-1 bg-green-500 text-white text-sm font-bold rounded-full"
              >
                Save {{ calculateDiscount(product.originalPrice, product.price) }}%
              </span>
            </div>

            <!-- Description -->
            <div>
              <h3 class="text-lg font-semibold text-gray-900 dark:text-white mb-3">Description</h3>
              <p class="text-gray-600 dark:text-gray-300 leading-relaxed">
                {{ product.description || 'No description available.' }}
              </p>
            </div>

            <!-- Product Details -->
            <div class="grid grid-cols-2 gap-4 text-sm">
              <div>
                <span class="font-semibold text-gray-600 dark:text-gray-400">Category:</span>
                <span class="ml-2 text-gray-900 dark:text-white">{{ product.category || 'N/A' }}</span>
              </div>
              <div>
                <span class="font-semibold text-gray-600 dark:text-gray-400">Brand:</span>
                <span class="ml-2 text-gray-900 dark:text-white">{{ product.brand || 'N/A' }}</span>
              </div>
              <div>
                <span class="font-semibold text-gray-600 dark:text-gray-400">SKU:</span>
                <span class="ml-2 text-gray-900 dark:text-white">{{ product.sku || 'N/A' }}</span>
              </div>
            </div>

            <!-- Quantity and Actions -->
            <div class="space-y-4 pt-6 border-t border-gray-200 dark:border-gray-700">
              <!-- Quantity Selector -->
              <div class="flex items-center space-x-4">
                <span class="font-semibold text-gray-700 dark:text-gray-300">Quantity:</span>
                <div class="flex items-center space-x-2">
                  <button
                    @click="decreaseQuantity"
                    :disabled="quantity <= 1"
                    class="w-10 h-10 rounded-full bg-gray-100 dark:bg-gray-700 flex items-center justify-center disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-200 dark:hover:bg-gray-600 transition-colors"
                  >
                    <Minus class="w-4 h-4" />
                  </button>
                  <span class="w-12 text-center font-semibold text-lg">{{ quantity }}</span>
                  <button
                    @click="increaseQuantity"
                    :disabled="quantity >= product.stock"
                    class="w-10 h-10 rounded-full bg-gray-100 dark:bg-gray-700 flex items-center justify-center disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-200 dark:hover:bg-gray-600 transition-colors"
                  >
                    <Plus class="w-4 h-4" />
                  </button>
                </div>
                <span class="text-sm text-gray-500 dark:text-gray-400">
                  {{ product.stock || 0 }} available
                </span>
              </div>

              <!-- Action Buttons -->
              <div class="flex flex-col sm:flex-row space-y-3 sm:space-y-0 sm:space-x-4">
                <button
                  @click="handleAddToCart"
                  :disabled="isSeller || isCartLoading || product.stock === 0"
                  class="flex-1 px-8 py-4 bg-gradient-to-r from-red-600 to-red-700 text-white rounded-2xl font-semibold shadow-lg hover:from-red-700 hover:to-red-800 disabled:opacity-50 disabled:cursor-not-allowed transition-all duration-300 flex items-center justify-center space-x-3"
                >
                  <ShoppingBasket class="w-5 h-5" :class="isCartLoading ? 'animate-spin' : ''" />
                  <span>{{ isCartLoading ? 'Adding...' : (product.stock === 0 ? 'Out of Stock' : 'Add to Cart') }}</span>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Seller Information Section -->
      <div class="mt-8 bg-white dark:bg-gray-800 rounded-3xl shadow-lg p-6">
        <h3 class="text-xl font-bold text-gray-900 dark:text-white mb-6">Seller Information</h3>
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <!-- Seller Details -->
          <div class="space-y-4">
            <div class="flex items-center space-x-4">
              <div class="w-16 h-16 bg-gradient-to-r from-blue-500 to-purple-600 rounded-full flex items-center justify-center text-white font-bold text-xl">
                {{ (seller.sellerName || 'Seller').charAt(0).toUpperCase() }}
              </div>
              <div>
                <h4 class="text-lg font-semibold text-gray-900 dark:text-white">{{ seller.sellerName }}</h4>
                <p class="text-gray-600 dark:text-gray-400">Verified Seller</p>
              </div>
            </div>
            
            <div class="space-y-3">
              <div class="flex items-center space-x-3">
                <Mail class="w-5 h-5 text-gray-400" />
                <span class="text-gray-700 dark:text-gray-300">{{ seller.emailPhone }}</span>
              </div>
              <div class="flex items-center space-x-3">
                <MapPin class="w-5 h-5 text-gray-400" />
                <span class="text-gray-700 dark:text-gray-300">{{ seller.location }}</span>
              </div>
              <div class="flex items-center space-x-3">
                <Tag class="w-5 h-5 text-gray-400" />
                <span class="text-gray-700 dark:text-gray-300">{{ seller.productCategory }}</span>
              </div>
              <div class="flex items-center space-x-3">
                <Calendar class="w-5 h-5 text-gray-400" />
                <span class="text-gray-700 dark:text-gray-300">Member since {{ formatDate(seller.createdAt) }}</span>
              </div>
            </div>
          </div>

          <!-- Message Seller Section -->
          <div class="space-y-4">
            <h4 class="text-lg font-semibold text-gray-900 dark:text-white">Contact Seller</h4>
            <p class="text-gray-600 dark:text-gray-400 text-sm">
              Have questions about this product? Send a message directly to the seller.
            </p>
            
            <button
              @click="handleMessageSeller"
              :disabled="isSeller"
              class="w-full px-6 py-4 bg-gradient-to-r from-blue-600 to-blue-700 text-white rounded-2xl font-semibold shadow-lg hover:from-blue-700 hover:to-blue-800 disabled:opacity-50 disabled:cursor-not-allowed transition-all duration-300 flex items-center justify-center space-x-3"
            >
              <MessageCircle class="w-5 h-5" />
              <span>{{ isSeller ? 'Cannot message yourself' : 'Message Seller' }}</span>
            </button>

            <!-- Quick Actions -->
            <div class="flex space-x-4">
              <button
                @click="handleCallSeller"
                class="flex-1 px-4 py-3 bg-gradient-to-r from-green-600 to-green-700 text-white rounded-2xl font-semibold hover:from-green-700 hover:to-green-800 transition-all duration-300 flex items-center justify-center space-x-2"
              >
                <Phone class="w-4 h-4" />
                <span>Call</span>
              </button>
              
              <button
                @click="handleEmailSeller"
                class="flex-1 px-4 py-3 bg-gradient-to-r from-purple-600 to-purple-700 text-white rounded-2xl font-semibold hover:from-purple-700 hover:to-purple-800 transition-all duration-300 flex items-center justify-center space-x-2"
              >
                <Mail class="w-4 h-4" />
                <span>Email</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Notification Toast -->
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
  </div>
</template>

<script>
import { 
  ArrowLeft, 
  ShoppingBasket, 
  MessageCircle, 
  Plus,
  Minus,
  Mail,
  MapPin,
  Tag,
  Calendar,
  Phone
} from "lucide-vue-next";
import CartService from "../services/CartService";
import ProductService from "../services/ProductService";

export default {
  name: "ProductDetails",
  components: {
    ArrowLeft,
    ShoppingBasket,
    MessageCircle,
    Plus,
    Minus,
    Mail,
    MapPin,
    Tag,
    Calendar,
    Phone
  },
  data() {
    return {
      product: {
        name: '',
        description: '',
        price: 0,
        originalPrice: 0,
        stock: 0,
        imageUrl: '',
        category: '',
        brand: '',
        sku: ''
      },
      seller: {
        sellerName: "Sagar Dhungana",
        emailPhone: "sagardada2535@gmail.com",
        location: "Balaju",
        role: "SELLER",
        createdAt: "2025-10-26T04:10:41.721836Z",
        updatedAt: "2025-10-26T04:10:41.721836Z",
        productCategory: "Nepali"
      },
      quantity: 1,
      loading: true,
      isCartLoading: false,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success',
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
  async mounted() {
    await this.loadProductDetails();
  },
  methods: {
    async loadProductDetails() {
      this.loading = true;
      const productId = this.$route.params.id;
      
      try {
        // Try to get product from route query first (for quick navigation)
        if (this.$route.query.product) {
          this.product = JSON.parse(this.$route.query.product);
        } else {
          // If not in query, fetch from API
          const response = await ProductService.getProductById(productId);
          this.product = response.data;
        }
      } catch (error) {
        console.error('Error loading product details:', error);
        this.showCustomNotification('Failed to load product details', 'error');
      } finally {
        this.loading = false;
      }
    },

    calculateDiscount(original, current) {
      return Math.round(((original - current) / original) * 100);
    },

    increaseQuantity() {
      if (this.quantity < this.product.stock) {
        this.quantity++;
      }
    },

    decreaseQuantity() {
      if (this.quantity > 1) {
        this.quantity--;
      }
    },

    formatDate(dateString) {
      return new Date(dateString).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
      });
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

      if (this.product.stock === 0) {
        this.showCustomNotification("This product is out of stock!", 'error');
        return;
      }

      this.isCartLoading = true;

      try {
        const result = await CartService.addToCart(this.product.id, this.quantity);
        
        if (result && result.success) {
          this.showCustomNotification("Product added to cart successfully!", 'success');
        } else {
          const errorMessage = result?.message || "Failed to add item to cart. Please try again.";
          this.showCustomNotification(errorMessage, 'error');
        }
      } catch (error) {
        console.error("Cart error:", error);
        let errorMessage = "Something went wrong while adding to cart!";
        
        if (error.response?.data?.message) {
          errorMessage = error.response.data.message;
        } else if (error.message) {
          errorMessage = error.message;
        }
        
        this.showCustomNotification(errorMessage, 'error');
      } finally {
        this.isCartLoading = false;
      }
    },

    handleMessageSeller() {
      if (this.isSeller) {
        this.showCustomNotification("You cannot message yourself!", 'error');
        return;
      }
      
      // For now, show contact info
      this.showCustomNotification(`Contact seller at: ${this.seller.emailPhone}`, 'info');
    },

    handleCallSeller() {
      // Extract phone number from emailPhone if it contains a phone number
      // For now, just show notification
      this.showCustomNotification("Call feature coming soon!", 'info');
    },

    handleEmailSeller() {
      // Open email client
      window.location.href = `mailto:${this.seller.emailPhone}?subject=Inquiry about ${this.product.name}`;
    }
  }
};
</script>

<style scoped>
/* Custom styles if needed */
</style>