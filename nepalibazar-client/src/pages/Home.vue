<template>
  <div class="min-h-screen bg-white">
    <Hero/>
    <Navbar />
    
    <!-- Main Content -->
    <div class="container mx-auto px-4 py-6 max-w-7xl">
      <!-- Hero Section with Product Carousel -->
      <section class="mb-12">
        <div class="grid grid-cols-1 lg:grid-cols-4 gap-6">
          <!-- Sidebar Categories - Nepali Local Products -->
          <div class="lg:col-span-1 bg-white rounded-lg border border-gray-200 p-4">
            <h3 class="text-base font-semibold text-gray-900 mb-4 flex items-center">
              <svg class="w-4 h-4 mr-2 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"/>
              </svg>
              Nepali Products
            </h3>
            <ul class="space-y-1">
              <li 
                v-for="category in categories" 
                :key="category.id" 
                class="flex items-center justify-between py-2 px-3 rounded hover:bg-gray-50 cursor-pointer transition-colors duration-200"
              >
                <span class="text-sm text-gray-700 font-medium">{{ category.name }}</span>
                <span class="text-xs text-gray-500">{{ category.count }}</span>
              </li>
            </ul>
            
            <div class="mt-4 pt-4 border-t border-gray-200">
              <router-link 
                to="/products" 
                class="w-full flex items-center justify-center px-4 py-2 bg-black text-white text-sm font-medium rounded transition-colors duration-200"
              >
                View All Products
                <svg class="w-4 h-4 ml-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"/>
                </svg>
              </router-link>
            </div>
          </div>

          <!-- Main Carousel -->
          <div class="lg:col-span-3 bg-gray-200 border-zinc-400 rounded">
            <div class="bg-white rounded-lg border border-gray-200 overflow-hidden">
              <!-- Carousel Header -->
              <div class="flex items-center justify-between p-4 border-b border-gray-200">
                <h2 class="text-2xl font-semibold text-black mb-5">Featured Nepali Products</h2>
                <div class="flex space-x-2">
                  <button @click="prevSlide" class="p-2 rounded border border-gray-300 hover:bg-gray-50 transition-colors duration-200">
                    <svg class="w-4 h-4 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
                    </svg>
                  </button>
                  <button @click="nextSlide" class="p-2 rounded border border-gray-300 hover:bg-gray-50 transition-colors duration-200">
                    <svg class="w-4 h-4 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
                    </svg>
                  </button>
                </div>
              </div>

              <!-- Loading State for Featured Products -->
              <div v-if="featuredProducts.length === 0" class="text-center py-12">
                <div class="inline-block animate-spin rounded-full h-8 w-8 border-b-2 border-gray-900 mb-3"></div>
                <p class="text-gray-600 text-sm">Loading featured products...</p>
              </div>

              <!-- Carousel Content -->
              <div v-else class="relative h-100 py-8">
                <div class="overflow-hidden">
                  <div 
                    class="flex transition-transform duration-500 ease-in-out" 
                    :style="{ transform: `translateX(-${currentSlide * 100}%)` }"
                  >
                    <div 
                      v-for="(product, index) in featuredProducts" 
                      :key="product.id"
                      class="w-full flex-shrink-0 p-6"
                    >
                    
                      <div class="grid grid-cols-1 md:grid-cols-2 gap-8 items-center">

                        <div class="space-y-4">
                          <div>
                            <span class="text-xs font-medium text-gray-500 uppercase tracking-wide">
                              {{ getProductRegion(product) }}
                            </span>
                            <h3 class="text-2xl font-semibold text-gray-900 mt-1">{{ product.name }}</h3>
                            <p class="text-gray-600 text-light mt-2 leading-relaxed">{{ product.description || 'Handcrafted with traditional Nepali techniques.' }}</p>
                          </div>
                          
                          <div class="flex items-center space-x-3">
                            <span class="  text-gray-500 line-through">NPR {{ product.price }}/-</span>
                            <span class="text-sm text-gray-500 line-through" v-if="product.originalPrice">
                              NPR {{ product.originalPrice }}/-
                            </span>
                            <span class="text-xl font-semibold">NPR {{ product.discountPrice }}/-</span>
                          </div>
                          
                          <div class="flex items-center space-x-3 pt-3">
                            <button 
                              @click="addToCart(product.id)"
                              :disabled="addingToCart === product.id"
                              class="flex-1 bg-black text-white py-3 px-6 rounded text-sm font-medium transition-colors duration-200 disabled:bg-gray-400 disabled:cursor-not-allowed"
                            >
                              <span v-if="addingToCart === product.id">Adding...</span>
                              <span v-else>Add to Cart</span>
                            </button>
                            <button 
                              @click="toggleWishlist(product.id)"
                              :disabled="togglingWishlist === product.id"
                              class="p-3 border border-gray-300 rounded hover:bg-gray-50 transition-colors duration-200 disabled:opacity-50"
                              :class="{ 'text-red-500 border-red-200 bg-red-50': isProductWishlisted(product.id) }"
                            >
                              <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
                                <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/>
                              </svg>
                            </button>
                          </div>
                          
                          <div class="flex items-center space-x-4 text-xs text-gray-500 pt-3">
                            <span class="flex items-center">
                              <svg class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 24 24">
                                <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z"/>
                              </svg>
                              In Stock
                            </span>
                            <span class="flex items-center">
                              <svg class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 24 24">
                                <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
                              </svg>
                              {{ product.sellerName || 'Local Artisan' }}
                            </span>
                          </div>
                        </div>
                        <!-- Product Image -->
                        <div class="relative">
                          <img 
                            :src="product.image || '/api/placeholder/400/400'" 
                            :alt="product.name"
                            class="w-full h-64 object-cover rounded-lg border border-gray-200"
                            @error="handleImageError"
                          />
                          <div class="absolute top-3 left-3">
                            <span class="px-2 py-1 bg-gray-900 text-white text-xs font-medium rounded">
                              Featured
                            </span>
                          </div>
                        </div>
                        
                        <!-- Product Details -->
                        
                      </div>
                    </div>
                  </div>
                </div>
                
                <!-- Carousel Indicators -->
                <div class="flex justify-center space-x-1 pb-4">
                  <button 
                    v-for="(_, index) in featuredProducts" 
                    :key="index"
                    @click="goToSlide(index)"
                    class="w-2 h-2 rounded-full transition-all duration-300"
                    :class="currentSlide === index ? 'bg-gray-900 w-6' : 'bg-gray-300'"
                  ></button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <div class="bg-gray-500 h-0.5"></div>

      <!-- Latest Products Section -->
      <section class="mb-12">
        <div class="flex items-center justify-between mb-6">
          <h2 class="text-2xl font-bold text-gray-900">Latest Nepali Products</h2>
          <router-link 
            to="/products" 
            class="flex items-center text-gray-700 text-sm font-medium hover:text-gray-900 transition-colors duration-200"
          >
            View All
            <svg class="w-4 h-4 ml-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
            </svg>
          </router-link>
        </div>

        <!-- Loading State -->
        <div v-if="latestProducts.length === 0" class="text-center py-8">
          <div class="inline-block animate-spin rounded-full h-8 w-8 border-b-2 border-gray-900 mb-3"></div>
          <p class="text-gray-600 text-sm">Loading Nepali products...</p>
        </div>

        <!-- Products Grid -->
        <div v-else class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
          <ProductCard
            v-for="product in latestProducts"
            :key="product.id"
            :product="{
              ...product,
              wishlisted: isProductWishlisted(product.id)
            }"
            @wishlist="handleWishlistUpdate"
            @add-to-cart="addToCart"
          />
        </div>
      </section>
      <div class="bg-gray-500 h-0.5 mb-1"></div>

      <!-- Exclusive Offers Section -->
      <section class="mb-12">
        <div class="flex items-center justify-between mb-6">
          <div class="flex items-center space-x-3">
            <h2 class="text-2xl font-bold text-gray-900">Exclusive Offers</h2>
            <span class="px-3 py-1 bg-black text-white text-sm font-medium rounded-full">
              50% Off or More
            </span>
          </div>
          <router-link 
            to="/products?discount=50" 
            class="flex items-center text-gray-700 text-sm font-medium hover:text-gray-900 transition-colors duration-200"
          >
            View All Offers
            <svg class="w-4 h-4 ml-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
            </svg>
          </router-link>
        </div>

        <!-- Loading State -->
        <div v-if="exclusiveOffers.length === 0 && !isLoading" class="text-center py-12">
          <div class="w-24 h-24 mx-auto mb-4 text-gray-300">
            <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M9 14l6-6m-5.5.5h.01m4.99 5h.01M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16l3.5-2 3.5 2 3.5-2 3.5 2z"/>
            </svg>
          </div>
          <h3 class="text-lg font-semibold text-gray-700 mb-2">No Exclusive Offers Available</h3>
          <p class="text-gray-500 text-sm">Check back later for amazing deals!</p>
        </div>

        <!-- Exclusive Offers Grid -->
        <div v-else-if="exclusiveOffers.length > 0" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
          <ProductCard
            v-for="product in exclusiveOffers"
            :key="product.id"
            :product="{
              ...product,
              wishlisted: isProductWishlisted(product.id)
            }"
            @wishlist="handleWishlistUpdate"
            @add-to-cart="addToCart"
          />
        </div>

        <!-- Loading State for Exclusive Offers -->
        <div v-else class="text-center py-8">
          <div class="inline-block animate-spin rounded-full h-8 w-8 border-b-2 border-gray-900 mb-3"></div>
          <p class="text-gray-600 text-sm">Loading exclusive offers...</p>
        </div>
      </section>
      <div class="bg-gray-500 h-0.5"></div>

      <!-- Value Proposition Section -->
      <section class="mb-12 mt-2">
        <div class="bg-gray-50 rounded-lg border border-gray-200 p-8">
          <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 items-center">
            <div>
              <h2 class="text-2xl font-bold text-gray-900 mb-4">Authentic Nepali Marketplace</h2>
              <p class="text-gray-600 leading-relaxed mb-6">
                Discover genuine products handcrafted by local artisans across Nepal. 
                Support traditional craftsmanship while enjoying modern e-commerce convenience.
              </p>
              <div class="grid grid-cols-2 gap-4">
                <div class="text-center p-3 bg-white rounded border border-gray-200">
                  <div class="text-lg font-bold text-gray-900 mb-1">500+</div>
                  <div class="text-sm text-gray-600">Local Artisans</div>
                </div>
                <div class="text-center p-3 bg-white rounded border border-gray-200">
                  <div class="text-lg font-bold text-gray-900 mb-1">75+</div>
                  <div class="text-sm text-gray-600">Nepali Regions</div>
                </div>
              </div>
            </div>
            <div class="text-center">
              <div class="inline-flex items-center justify-center w-16 h-16 bg-gray-900 rounded-full mb-4">
                <span class="text-white text-xl">🇳🇵</span>
              </div>
              <p class="text-gray-700 text-sm font-medium">
                Made in Nepal<br>
                <span class="text-gray-900 text-base">With Pride</span>
              </p>
            </div>
          </div>
        </div>
      </section>
    </div>

    <Footer />
  </div>
</template>

<script>
import Navbar from "../components/ui/Navbar.vue";
import Footer from "../components/ui/Footer.vue";
import ProductCard from "../components/ProductCard.vue";
import ProductService from "../services/ProductService";
import WishlistService from "../services/WishlistService";
import CartService from "../services/CartService";
import Hero from "../components/ui/Hero.vue";

export default {
  name: "Home",
  components: {Hero, Navbar, Footer, ProductCard },

  data() {
    return {
      currentSlide: 0,
      latestProducts: [],
      featuredProducts: [],
      exclusiveOffers: [],
      wishlistProductIds: [],
      addingToCart: null,
      togglingWishlist: null,
      isLoading: false,
      categories: [
        { id: 1, name: "Handicrafts & Art", count: "156" },
        { id: 2, name: "Textiles & Clothing", count: "89" },
        { id: 3, name: "Organic Food", count: "67" },
        { id: 4, name: "Herbal Products", count: "43" },
        { id: 5, name: "Traditional Jewelry", count: "34" },
        { id: 6, name: "Woodwork", count: "28" },
        { id: 7, name: "Metal Crafts", count: "22" },
        { id: 8, name: "Pottery", count: "19" }
      ],
      carouselInterval: null
    };
  },
  
  async mounted() {
    await Promise.all([
      this.fetchLatestProducts(),
      this.fetchFeaturedProducts(),
      this.fetchExclusiveOffers(),
      this.fetchWishlist()
    ]);
    
    if (this.featuredProducts.length > 1) {
      this.startCarousel();
    }
  },

  beforeUnmount() {
    if (this.carouselInterval) {
      clearInterval(this.carouselInterval);
    }
  },

  methods: {
    startCarousel() {
      this.carouselInterval = setInterval(() => {
        this.nextSlide();
      }, 5000);
    },

    nextSlide() {
      if (this.featuredProducts.length > 0) {
        this.currentSlide = (this.currentSlide + 1) % this.featuredProducts.length;
      }
    },

    prevSlide() {
      if (this.featuredProducts.length > 0) {
        this.currentSlide = this.currentSlide === 0 
          ? this.featuredProducts.length - 1 
          : this.currentSlide - 1;
      }
    },

    goToSlide(index) {
      this.currentSlide = index;
    },

    async fetchLatestProducts() {
      try {
        const allProducts = await ProductService.getProducts(0, 20);
        const sortedProducts = allProducts.sort((a, b) => {
          if (a.createdAt && b.createdAt) {
            return new Date(b.createdAt) - new Date(a.createdAt);
          }
          return b.id - a.id;
        });
        this.latestProducts = sortedProducts.slice(0, 4);
      } catch (error) {
        console.error("Error fetching latest products:", error);
        this.latestProducts = [];
      }
    },

    async fetchFeaturedProducts() {
      try {
        const allProducts = await ProductService.getProducts(0, 20);
        this.featuredProducts = allProducts.slice(0, 4).map(product => ({
          ...product,
          image: product.image || product.imageUrl || '/api/placeholder/400/400',
          sellerName: product.sellerName || product.seller?.name || 'Local Artisan'
        }));
      } catch (error) {
        console.error("Error fetching featured products:", error);
        this.featuredProducts = [];
      }
    },

    async fetchExclusiveOffers() {
      this.isLoading = true;
      try {
        const allProducts = await ProductService.getProducts(0, 50);
        
        // Filter products with discount >= 50%
        const exclusiveProducts = allProducts.filter(product => {
          const discount = product.discount || 0;
          return discount >= 50;
        });
        
        // Sort by highest discount first
        this.exclusiveOffers = exclusiveProducts
          .sort((a, b) => (b.discount || 0) - (a.discount || 0))
          .slice(0, 4); // Show top 4 exclusive offers
          
      } catch (error) {
        console.error("Error fetching exclusive offers:", error);
        this.exclusiveOffers = [];
      } finally {
        this.isLoading = false;
      }
    },

    async addToCart(productId) {
      this.addingToCart = productId;
      try {
        const result = await CartService.addToCart(productId);
        
        if (result.success) {
          // Show success message
          this.$notify({
            title: 'Success',
            message: 'Product added to cart!',
            type: 'success'
          });
        } else {
          // Show error message
          this.$notify({
            title: 'Error',
            message: result.message,
            type: 'error'
          });
          
          // If session expired, redirect to login
          if (result.message.includes('session has expired')) {
            this.$router.push('/login');
          }
        }
      } catch (error) {
        console.error('Error adding to cart:', error);
        this.$notify({
          title: 'Error',
          message: 'Failed to add product to cart',
          type: 'error'
        });
      } finally {
        this.addingToCart = null;
      }
    },

    async toggleWishlist(productId) {
      this.togglingWishlist = productId;
      try {
        const result = await WishlistService.toggleWishlist(productId);
        
        if (result.success) {
          // Update local wishlist state
          if (result.isInWishlist) {
            if (!this.wishlistProductIds.includes(productId)) {
              this.wishlistProductIds.push(productId);
            }
          } else {
            const index = this.wishlistProductIds.indexOf(productId);
            if (index !== -1) {
              this.wishlistProductIds.splice(index, 1);
            }
          }
          
          // Show success message
          this.$notify({
            title: 'Success',
            message: result.isInWishlist ? 'Added to wishlist!' : 'Removed from wishlist!',
            type: 'success'
          });
        } else {
          // Show error message
          this.$notify({
            title: 'Error',
            message: result.message,
            type: 'error'
          });
          
          // If session expired, redirect to login
          if (result.message.includes('session has expired')) {
            this.$router.push('/login');
          }
        }
      } catch (error) {
        console.error('Error toggling wishlist:', error);
        this.$notify({
          title: 'Error',
          message: 'Failed to update wishlist',
          type: 'error'
        });
      } finally {
        this.togglingWishlist = null;
      }
    },

    getProductRegion(product) {
      return product.region || product.category || 'Nepal';
    },

    handleImageError(event) {
      event.target.src = 'https://images.unsplash.com/photo-1588850561407-ed78c282e89b?w=400&h=400&fit=crop';
    },

    async fetchWishlist() {
      try {
        const token = localStorage.getItem("buyer_jwt");
        if (!token) return;
        
        const wishlistProducts = await WishlistService.getWishlistProducts();
        this.wishlistProductIds = wishlistProducts.map(product => product.id);
      } catch (error) {
        console.error("Error fetching wishlist:", error);
      }
    },

    isProductWishlisted(productId) {
      return this.wishlistProductIds.includes(productId);
    },

    handleWishlistUpdate({ id, wishlisted }) {
      if (wishlisted) {
        if (!this.wishlistProductIds.includes(id)) {
          this.wishlistProductIds.push(id);
        }
      } else {
        const index = this.wishlistProductIds.indexOf(id);
        if (index !== -1) {
          this.wishlistProductIds.splice(index, 1);
        }
      }
    }
  }
};
</script>

<style scoped>
/* Professional smooth transitions */
button, a {
  transition: all 0.2s ease-in-out;
}

/* Custom scrollbar for better UX */
.sidebar-scroll {
  scrollbar-width: thin;
  scrollbar-color: #d1d5db #f9fafb;
}

.sidebar-scroll::-webkit-scrollbar {
  width: 4px;
}

.sidebar-scroll::-webkit-scrollbar-track {
  background: #f9fafb;
  border-radius: 2px;
}

.sidebar-scroll::-webkit-scrollbar-thumb {
  background: #d1d5db;
  border-radius: 2px;
}

.sidebar-scroll::-webkit-scrollbar-thumb:hover {
  background: #9ca3af;
}
</style>