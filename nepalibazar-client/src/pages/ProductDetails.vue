<template>
  <div class="min-h-screen bg-white">
    <!-- Navigation Header -->
    <nav class="bg-white shadow-sm border-b border-gray-200">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center h-16">
          <button 
            @click="$router.back()" 
            class="flex items-center space-x-2 text-gray-600 hover:text-gray-900 transition-colors duration-200"
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
          <div class="bg-gray-200 rounded-lg h-96"></div>
          <div class="space-y-4">
            <div class="h-8 bg-gray-200 rounded"></div>
            <div class="h-4 bg-gray-200 rounded"></div>
            <div class="h-6 bg-gray-200 rounded w-1/3"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- Error State -->
    <div v-else-if="error" class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="text-center py-12">
        <div class="w-24 h-24 mx-auto mb-4 text-gray-300">
          <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L4.35 16.5c-.77.833.192 2.5 1.732 2.5z"/>
          </svg>
        </div>
        <h3 class="text-xl font-semibold text-gray-900 mb-2">Product Not Found</h3>
        <p class="text-gray-600 mb-6">The product you're looking for doesn't exist or is no longer available.</p>
        <button 
          @click="$router.push('/products')"
          class="px-6 py-3 bg-black text-white rounded-lg font-medium transition-colors duration-200"
        >
          Browse Products
        </button>
      </div>
    </div>

    <!-- Product Details Section -->
    <div v-else-if="product.id" class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="bg-white rounded-lg shadow border border-gray-200 overflow-hidden">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 p-8">
          <!-- Product Images -->
          <div class="space-y-4">
            <!-- Main Image -->
            <div class="rounded-lg overflow-hidden bg-gray-100 aspect-square">
              <img
                :src="product.imageUrl || '/placeholder-image.jpg'"
                :alt="product.name"
                class="w-full h-full object-cover"
                @error="handleImageError"
              />
            </div>
            
            <!-- Additional Images -->
            <div class="grid grid-cols-4 gap-2">
              <div 
                v-for="i in 4" 
                :key="i"
                class="aspect-square bg-gray-100 rounded border border-gray-200 cursor-pointer hover:border-gray-400 transition-colors"
              >
                <img
                  :src="product.imageUrl || '/placeholder-image.jpg'"
                  :alt="product.name"
                  class="w-full h-full object-cover rounded"
                  @error="handleImageError"
                />
              </div>
            </div>
          </div>

          <!-- Product Information -->
          <div class="space-y-6">
            <!-- Product Header -->
            <div>
              <div class="flex items-center space-x-2 mb-2">
                <span class="text-sm font-medium text-gray-500 uppercase tracking-wide">
                  {{ product.category || 'General' }}
                </span>
                <span class="text-gray-300">•</span>
                <span class="text-sm text-gray-500">SKU: {{ product.sku || 'N/A' }}</span>
              </div>
              
              <h1 class="text-2xl lg:text-3xl font-semibold text-gray-900 mb-4">
                {{ product.name }}
              </h1>
              
              <!-- Stock Status -->
              <div class="mb-4">
                <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-green-100 text-green-800" v-if="product.stock > 0">
                  <span class="w-2 h-2 bg-green-500 rounded-full mr-2"></span>
                  In Stock ({{ product.stock }} available)
                </span>
                <span class="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-red-100 text-red-800" v-else>
                  <span class="w-2 h-2 bg-red-500 rounded-full mr-2"></span>
                  Out of Stock
                </span>
              </div>
            </div>

            <!-- Price Section -->
            <div class="flex items-center space-x-4">
              <span class="text-3xl font-semibold text-gray-900">
                Rs. {{ (product.discountPrice || product.price || 0).toLocaleString() }}
              </span>
              
              <span v-if="product.originalPrice && product.originalPrice > product.price" class="text-xl text-gray-500 line-through">
                Rs. {{ product.originalPrice.toLocaleString() }}
              </span>
              
              <span 
                v-if="product.discount && product.discount > 0" 
                class="px-3 py-1 bg-gray-800 text-white text-sm font-medium rounded-full"
              >
                Save {{ product.discount }}%
              </span>
            </div>

            <!-- Description -->
            <div>
              <h3 class="text-lg font-medium text-gray-900 mb-3">Product Description</h3>
              <p class="text-gray-600 leading-relaxed">
                {{ product.description || 'No description available.' }}
              </p>
            </div>

            <!-- Product Details -->
            <div class="grid grid-cols-2 gap-4 text-sm">
              <div>
                <span class="font-medium text-gray-600">Category:</span>
                <span class="ml-2 text-gray-900">{{ product.category || 'N/A' }}</span>
              </div>
              <div>
                <span class="font-medium text-gray-600">Brand:</span>
                <span class="ml-2 text-gray-900">{{ product.brand || 'N/A' }}</span>
              </div>
              <div>
                <span class="font-medium text-gray-600">SKU:</span>
                <span class="ml-2 text-gray-900">{{ product.sku || 'N/A' }}</span>
              </div>
              <div>
                <span class="font-medium text-gray-600">Seller:</span>
                <span class="ml-2 text-gray-900">{{ seller.sellerName }}</span>
              </div>
            </div>

            <!-- Quantity and Actions -->
            <div class="space-y-4 pt-6 border-t border-gray-200">
              <!-- Quantity Selector -->
              <div class="flex items-center space-x-4">
                <span class="font-medium text-gray-700">Quantity:</span>
                <div class="flex items-center space-x-2">
                  <button
                    @click="decreaseQuantity"
                    :disabled="quantity <= 1"
                    class="w-10 h-10 rounded-lg bg-gray-100 flex items-center justify-center disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-200 transition-colors"
                  >
                    <Minus class="w-4 h-4" />
                  </button>
                  <span class="w-12 text-center font-semibold text-lg border border-gray-300 rounded-lg py-2">{{ quantity }}</span>
                  <button
                    @click="increaseQuantity"
                    :disabled="quantity >= product.stock"
                    class="w-10 h-10 rounded-lg bg-gray-100 flex items-center justify-center disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-200 transition-colors"
                  >
                    <Plus class="w-4 h-4" />
                  </button>
                </div>
              </div>

              <!-- Action Buttons -->
              <div class="flex flex-col sm:flex-row space-y-3 sm:space-y-0 sm:space-x-4">
                <button
                  @click="handleAddToCart"
                  :disabled="isSeller || isCartLoading || product.stock === 0"
                  class="flex-1 px-8 py-3 bg-black text-white rounded-lg font-medium disabled:opacity-50 disabled:cursor-not-allowed transition-colors duration-200 flex items-center justify-center space-x-3"
                >
                  <ShoppingBasket class="w-5 h-5" :class="isCartLoading ? 'animate-spin' : ''" />
                  <span>{{ isCartLoading ? 'Adding...' : (product.stock === 0 ? 'Out of Stock' : 'Add to Cart') }}</span>
                </button>
                
                <button
                  @click="toggleWishlist"
                  :disabled="isSeller"
                  class="px-6 py-3 border border-gray-300 text-gray-700 rounded-lg font-medium hover:bg-gray-50 disabled:opacity-50 transition-colors duration-200 flex items-center justify-center space-x-2"
                >
                  <Heart class="w-5 h-5" :class="isWishlisted ? 'text-red-500 fill-current' : ''" />
                  <span>{{ isWishlisted ? 'Wishlisted' : 'Wishlist' }}</span>
                </button>
              </div>
            </div>

            <!-- Additional Features -->
            <div class="grid grid-cols-2 gap-4 text-sm text-gray-600">
              <div class="flex items-center space-x-2">
                <Truck class="w-4 h-4" />
                <span>Free shipping</span>
              </div>
              <div class="flex items-center space-x-2">
                <Shield class="w-4 h-4" />
                <span>2-year warranty</span>
              </div>
              <div class="flex items-center space-x-2">
                <RotateCcw class="w-4 h-4" />
                <span>30-day returns</span>
              </div>
              <div class="flex items-center space-x-2">
                <Headphones class="w-4 h-4" />
                <span>24/7 support</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Seller Information Section -->
      <div class="mt-8 bg-white rounded-lg shadow border border-gray-200 p-6">
        <h3 class="text-xl font-semibold text-gray-900 mb-6">Seller Information</h3>
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <!-- Seller Details -->
          <div class="space-y-4">
            <div class="flex items-center space-x-4">
              <div class="w-16 h-16 bg-gray-800 rounded-lg flex items-center justify-center text-white font-semibold text-xl">
                {{ (seller.sellerName || 'Seller').charAt(0).toUpperCase() }}
              </div>
              <div>
                <h4 class="text-lg font-medium text-gray-900">{{ seller.sellerName }}</h4>
                <p class="text-gray-600">Verified Seller</p>
                <div class="flex items-center space-x-1 mt-1">
                  <Star class="w-4 h-4 text-yellow-400 fill-current" v-for="i in 5" :key="i" />
                  <span class="text-sm text-gray-500 ml-1">(4.8)</span>
                </div>
              </div>
            </div>
            
            <div class="space-y-3">
              <div class="flex items-center space-x-3">
                <MapPin class="w-4 h-4 text-gray-400" />
                <span class="text-gray-700">{{ seller.location }}</span>
              </div>
              <div class="flex items-center space-x-3">
                <Tag class="w-4 h-4 text-gray-400" />
                <span class="text-gray-700">{{ seller.productCategory }}</span>
              </div>
              <div class="flex items-center space-x-3">
                <Calendar class="w-4 h-4 text-gray-400" />
                <span class="text-gray-700">Member since {{ formatDate(seller.createdAt) }}</span>
              </div>
            </div>
          </div>

          <!-- Contact Seller Section -->
          <div class="space-y-4">
            <h4 class="text-lg font-medium text-gray-900">Contact Seller</h4>
            <p class="text-gray-600 text-sm">
              Have questions about this product? Send a message directly to the seller.
            </p>
            
            <button
              @click="handleContactSeller"
              :disabled="isSeller"
              class="w-full px-6 py-3 bg-black text-white rounded-lg font-medium disabled:opacity-50 disabled:cursor-not-allowed transition-colors duration-200 flex items-center justify-center space-x-3"
            >
              <MessageCircle class="w-5 h-5" />
              <span>{{ isSeller ? 'Cannot contact yourself' : 'Contact Seller' }}</span>
            </button>
          </div>
        </div>
      </div>

      <!-- Product Specifications Section -->
      <div class="mt-8 bg-white rounded-lg shadow border border-gray-200 p-6">
        <h3 class="text-xl font-semibold text-gray-900 mb-6">Product Specifications</h3>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div class="space-y-4">
            <div v-for="spec in specifications" :key="spec.name" class="flex justify-between py-2 border-b border-gray-100">
              <span class="font-medium text-gray-600">{{ spec.name }}</span>
              <span class="text-gray-900">{{ spec.value }}</span>
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
      <div class="flex items-center space-x-3 px-6 py-4 rounded-lg shadow-lg border border-gray-200 bg-white">
        <div class="flex-shrink-0">
          <div class="w-8 h-8 rounded-full flex items-center justify-center" :class="notificationIconStyle">
            <svg v-if="notificationType === 'success'" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
            </svg>
            <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
          </div>
        </div>
        <div class="flex-1 min-w-0">
          <p class="text-sm font-medium text-gray-900">
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
  MapPin,
  Tag,
  Calendar,
  Heart,
  Star,
  Truck,
  Shield,
  RotateCcw,
  Headphones
} from "lucide-vue-next";
import CartService from "../services/CartService";
import ProductService from "../services/ProductService";
import WishlistService from "../services/WishlistService";

export default {
  name: "ProductDetails",
  components: {
    ArrowLeft,
    ShoppingBasket,
    MessageCircle,
    Plus,
    Minus,
    MapPin,
    Tag,
    Calendar,
    Heart,
    Star,
    Truck,
    Shield,
    RotateCcw,
    Headphones
  },
  data() {
    return {
      product: {
        id: null,
        name: '',
        description: '',
        price: 0,
        discountPrice: 0,
        originalPrice: 0,
        discount: 0,
        stock: 0,
        imageUrl: '',
        category: '',
        brand: '',
        sku: '',
        sellerId: null
      },
      seller: {
        sellerName: '',
        location: '',
        productCategory: '',
        createdAt: '',
        email: ''
      },
      quantity: 1,
      loading: true,
      error: false,
      isCartLoading: false,
      isWishlisted: false,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success',
      notificationTimeout: null,
      specifications: [
        { name: 'Material', value: 'Premium Quality' },
        // { name: 'Dimensions', value: '10 x 8 x 5 cm' },
        // { name: 'Weight', value: '500 grams' },
        { name: 'Color', value: 'As shown' },
        // { name: 'Warranty', value: '2 years' },
        { name: 'Origin', value: 'Nepal' }
      ]
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
    notificationIconStyle() {
      return this.notificationType === 'success' 
        ? 'bg-green-100 text-green-600' 
        : 'bg-red-100 text-red-600';
    }
  },
  async mounted() {
    console.log(' ProductDetails mounted with ID:', this.$route.params.id);
    await this.loadProductDetails();
    await this.checkWishlistStatus();
  },
  methods: {
    async loadProductDetails() {
      this.loading = true;
      this.error = false;
      const productId = this.$route.params.id;
      
      console.log(' Loading product details for ID:', productId);
      
      try {
        const response = await ProductService.getProductById(productId);
        console.log(' Full API Response:', response);
        
        // Check if response exists and has data
        if (response && response.data) {
          console.log(' Response data exists:', response.data);
          console.log(' Response code:', response.data.code);
          console.log(' Response message:', response.data.message);
          console.log(' Data array:', response.data.data);
          
          // Check if data array exists and has items
          if (response.data.data && Array.isArray(response.data.data) && response.data.data.length > 0) {
            const productData = response.data.data[0];
            console.log('🎯 Product data received:', productData);
            
            // Map backend fields to frontend structure
            this.product = {
              id: productData.id,
              name: productData.productName,
              description: productData.aboutProduct,
              price: productData.price,
              discountPrice: productData.discountedPrice, // FIXED: discountedPrice from backend
              originalPrice: productData.price,
              discount: productData.discount,
              stock: productData.quantity,
              imageUrl: productData.image,
              category: productData.sellerEntity?.productCategory || 'General',
              brand: productData.brand || 'Local Brand',
              sku: `SKU-${productData.id}`,
              sellerId: productData.sellerEntity?.id
            };
            
            console.log('✅ Mapped product:', this.product);
            
            // Load seller information from the product data
            if (productData.sellerEntity) {
              this.seller = {
                sellerName: productData.sellerEntity.sellerName,
                location: productData.sellerEntity.location,
                productCategory: productData.sellerEntity.productCategory,
                createdAt: productData.sellerEntity.createdAt,
                email: productData.sellerEntity.emailPhone
              };
              console.log('🏪 Seller info:', this.seller);
            } else {
              // Fallback seller info
              this.seller = {
                sellerName: "Local Artisan",
                location: "Nepal",
                productCategory: "Handmade",
                createdAt: new Date().toISOString()
              };
            }
            
            console.log('✅ SUCCESS: Product data loaded successfully');
          } else {
            console.log('❌ No product data found in response.data.data');
            this.error = true;
          }
        } else {
          console.log('❌ No response data or invalid response structure');
          this.error = true;
        }
      } catch (error) {
        console.error('💥 Error loading product details:', error);
        console.error('💥 Error response:', error.response);
        this.error = true;
        this.showCustomNotification('Failed to load product details', 'error');
      } finally {
        this.loading = false;
        console.log('🏁 Loading completed, error:', this.error, 'Product ID:', this.product.id);
      }
    },

    async checkWishlistStatus() {
      try {
        if (!this.product.id) return;
        
        const wishlist = await WishlistService.getWishlistProducts();
        this.isWishlisted = wishlist.some(item => item.id === this.product.id);
        console.log('❤️ Wishlist status:', this.isWishlisted);
      } catch (error) {
        console.error('Error checking wishlist status:', error);
      }
    },

    async toggleWishlist() {
      if (this.isSeller) {
        this.showCustomNotification("Sellers cannot add products to wishlist!", 'error');
        return;
      }

      try {
        const result = await WishlistService.toggleWishlist(this.product.id);
        this.isWishlisted = result.isInWishlist;
        this.showCustomNotification(
          result.isInWishlist ? 'Added to wishlist!' : 'Removed from wishlist!', 
          'success'
        );
      } catch (error) {
        console.error('Error toggling wishlist:', error);
        this.showCustomNotification('Failed to update wishlist', 'error');
      }
    },

    calculateDiscount(original, current) {
      if (!original || original <= current) return 0;
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
      if (!dateString) return 'Recently';
      try {
        return new Date(dateString).toLocaleDateString('en-US', {
          year: 'numeric',
          month: 'long',
          day: 'numeric'
        });
      } catch (error) {
        return 'Recently';
      }
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

    handleImageError(event) {
      event.target.src = 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPHJlY3Qgd2lkdGg9IjY0IiBoZWlnaHQ9IjY0IiBmaWxsPSIjRjNGNEY2Ii8+CjxwYXRoIGQ9Ik0zMiAyMEMyOC42ODYzIDIwIDI2IDIyLjY4NjMgMjYgMjZDMjYgMjkuMzEzNyAyOC42ODYzIDMyIDMyIDMyQzM1LjMxMzcgMzIgMzggMjkuMzEzNyAzOCAyNkMzOCAyMi42ODYzIDM1LjMxMzcgMjAgMzIgMjBaTTMyIDM2QzI1LjM3MzYgMzYgMjAgNDEuMzczNiAyMCA0OFY1Mkg0NFY0OEM0NCA0MS4zNzM2IDM4LjYyNjQgMzYgMzIgMzZaIiBmaWxsPSIjOUU5RkFGIi8+Cjwvc3ZnPgo=';
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

    handleContactSeller() {
      if (this.isSeller) {
        this.showCustomNotification("You cannot contact yourself!", 'error');
        return;
      }
      
      this.showCustomNotification("Contact feature coming soon!", 'info');
    }
  }
};
</script>