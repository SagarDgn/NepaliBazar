<template>
  <div class="min-h-screen bg-gradient-to-br ">
    <Hero/>
    <Navbar />
    
    <!-- Hero Section with Nepal Map -->
    <section class="relative bg-white overflow-hidden">
      <div class="container mx-auto px-4 py-16">
        <div class="flex flex-col lg:flex-row items-center">
          <!-- Text Content -->
          <div class="lg:w-1/2 text-center lg:text-left mb-12 lg:mb-0">
            <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-800 mb-6">
              Products From
              <span class="text-transparent bg-clip-text bg-gradient-to-r from-green-600 to-orange-600">
                Every Part of Nepal
              </span>
            </h1>
            <p class="text-xl text-gray-600 mb-8 leading-relaxed">
              Discover authentic products handcrafted across Nepal's diverse regions. 
              From the Himalayas to the Terai, experience the rich cultural heritage 
              and craftsmanship of our beautiful nation.
            </p>
            <div class="flex flex-col sm:flex-row gap-4 justify-center lg:justify-start">
              <router-link 
                to="/products" 
                class="px-8 py-4 bg-gradient-to-r from-green-600 to-orange-600 text-white font-semibold rounded-2xl hover:from-green-700 hover:to-orange-700 transition-all duration-300 transform hover:scale-105 shadow-lg"
              >
                Explore Products
              </router-link>
              <router-link 
                to="/about" 
                class="px-8 py-4 border border-gray-300 text-gray-700 font-semibold rounded-2xl hover:bg-gray-50 transition-all duration-300"
              >
                Our Story
              </router-link>
            </div>
          </div>
          
          <!-- Nepal Map Illustration -->
          <div class="lg:w-1/2 relative">
            <div class="relative w-full max-w-lg mx-auto">
              <!-- Map Container -->
              <div class="bg-gradient-to-br from-green-50 to-orange-50 rounded-3xl p-8 shadow-2xl border border-green-200">
                <!-- Simplified Nepal Map -->
                <div class="relative h-80">
                  <!-- Map Outline -->
                  <div class="absolute inset-0 bg-gradient-to-br from-white to-green-50 rounded-2xl border-2 border-green-300"></div>
                  
                  <!-- Himalayan Region -->
                  <div class="absolute top-4 left-1/4 w-16 h-16 bg-gradient-to-br from-blue-500 to-cyan-400 rounded-full shadow-lg transform -translate-x-1/2">
                    <div class="absolute -top-2 -left-2 w-6 h-6 bg-white rounded-full flex items-center justify-center shadow-md">
                      <div class="w-3 h-3 bg-blue-500 rounded-full"></div>
                    </div>
                    <span class="absolute -bottom-8 left-1/2 transform -translate-x-1/2 text-xs font-semibold text-gray-700 whitespace-nowrap">Himalayan</span>
                  </div>
                  
                  <!-- Hilly Region -->
                  <div class="absolute top-1/3 left-2/4 w-20 h-20 bg-gradient-to-br from-green-500 to-emerald-400 rounded-full shadow-lg transform -translate-x-1/2">
                    <div class="absolute -top-2 -right-2 w-6 h-6 bg-white rounded-full flex items-center justify-center shadow-md">
                      <div class="w-3 h-3 bg-green-500 rounded-full"></div>
                    </div>
                    <span class="absolute -bottom-8 left-1/2 transform -translate-x-1/2 text-xs font-semibold text-gray-700 whitespace-nowrap">Hilly</span>
                  </div>
                  
                  <!-- Terai Region -->
                  <div class="absolute bottom-8 left-1/3 w-24 h-16 bg-gradient-to-br from-orange-500 to-yellow-400 rounded-full shadow-lg transform -translate-x-1/2">
                    <div class="absolute -bottom-2 left-1/2 transform -translate-x-1/2 w-6 h-6 bg-white rounded-full flex items-center justify-center shadow-md">
                      <div class="w-3 h-3 bg-orange-500 rounded-full"></div>
                    </div>
                    <span class="absolute -top-8 left-1/2 transform -translate-x-1/2 text-xs font-semibold text-gray-700 whitespace-nowrap">Terai</span>
                  </div>
                  
                  <!-- Kathmandu Valley -->
                  <div class="absolute top-2/4 left-2/4 w-8 h-8 bg-red-500 rounded-full shadow-lg transform -translate-x-1/2 -translate-y-1/2 animate-pulse">
                    <div class="absolute inset-0 bg-red-400 rounded-full animate-ping"></div>
                    <span class="absolute -top-6 left-1/2 transform -translate-x-1/2 text-xs font-bold text-red-600 whitespace-nowrap">Kathmandu</span>
                  </div>
                  
                  <!-- Connecting Lines -->
                  <div class="absolute top-8 left-1/4 w-1/4 h-1 border-t-2 border-dashed border-green-300 transform rotate-12"></div>
                  <div class="absolute top-20 left-2/4 w-1/6 h-1 border-t-2 border-dashed border-green-300 transform -rotate-6"></div>
                </div>
                
                <!-- Map Legend -->
                <div class="grid grid-cols-3 gap-4 mt-6 pt-6 border-t border-green-200">
                  <div class="text-center">
                    <div class="w-3 h-3 bg-blue-500 rounded-full mx-auto mb-1"></div>
                    <span class="text-xs text-gray-600 font-medium">Himalayan</span>
                  </div>
                  <div class="text-center">
                    <div class="w-3 h-3 bg-green-500 rounded-full mx-auto mb-1"></div>
                    <span class="text-xs text-gray-600 font-medium">Hilly</span>
                  </div>
                  <div class="text-center">
                    <div class="w-3 h-3 bg-orange-500 rounded-full mx-auto mb-1"></div>
                    <span class="text-xs text-gray-600 font-medium">Terai</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Main Content -->
    <div class="container mx-auto px-4 py-12 max-w-7xl">
      
      <!-- Regional Products Section -->
      <section class="mb-16">
        <div class="text-center mb-12">
          <h2 class="text-4xl font-bold text-gray-800 mb-4">Products From Across Nepal</h2>
          <p class="text-xl text-gray-600 max-w-3xl mx-auto">
            Explore unique products crafted by skilled artisans from different regions of Nepal. 
            Each product tells a story of our rich cultural heritage.
          </p>
        </div>

        <!-- Regional Highlights -->
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8 mb-12">
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-green-200 text-center">
            <div class=" mx-auto mb-4 bg-blue-100 rounded-2xl flex items-center justify-center">
              <span class="text-2xl">
                <img src="https://imgs.search.brave.com/OTM5MhX6icZ5Y61BIOFx5ilzrQ5R_hh-c2u1qiPqBX4/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9tZWRp/YS5nZXR0eWltYWdl/cy5jb20vaWQvMTI4/NTIxNDgzNi9waG90/by90aGUtZXZlcmVz/dC1iYXNlLWNhbXAt/aGlraW5nLXRyYWls/LW5lYXItdGhlLWRp/bmdib2NoZS12aWxs/YWdlLWluLXRoZS1o/aW1hbGF5YS1pbi1u/ZXBhbC5qcGc_cz02/MTJ4NjEyJnc9MCZr/PTIwJmM9b1VkcFM0/aVpyZHo1bjItSHl2/QzVwNUUzVm55cVc3/b01kX0RPa2pOd205/RT0"
                />
              </span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Himalayan Region</h3>
            <p class="text-gray-600">Woolen crafts, herbal products, and traditional items from the high mountains</p>
          </div>
          
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-green-200 text-center">
            <div class=" mx-auto mb-4 bg-green-100 rounded-2xl flex items-center justify-center">
              <span class="text-2xl">
                <img src="https://imgs.search.brave.com/yeF_ikky1Q2Lq7M1B2CyWrl2b_b4U8GryLesTuYPMsI/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pbWFn/ZXMucGV4ZWxzLmNv/bS9waG90b3MvMzAy/NzEzOC9wZXhlbHMt/cGhvdG8tMzAyNzEz/OC5qcGVnP2F1dG89/Y29tcHJlc3MmY3M9/dGlueXNyZ2ImZHBy/PTEmdz01MDA"
                />
              </span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Hilly Region</h3>
            <p class="text-gray-600">Handicrafts, textiles, and organic products from the hills and valleys</p>
          </div>
          
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-green-200 text-center">
            <div class=" mx-auto mb-4 bg-orange-100 rounded-2xl flex items-center justify-center">
              <span class="text-2xl">
                <img src="https://imgs.search.brave.com/LcvrqotJUSd9Wtc20V4jVA0MXxsOeaO4PUHIECFqoGY/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pLnBp/bmltZy5jb20vb3Jp/Z2luYWxzLzY2Lzlk/L2JkLzY2OWRiZDgw/MjQ5ZTQ2MWQ2N2U2/YjIyODFlNzM5NGQ2/LmpwZw"
               />
              </span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Terai Region</h3>
            <p class="text-gray-600">Agricultural products, textiles, and traditional crafts from the plains</p>
          </div>
        </div>

        <!-- Loading State -->
        <div class="h-20 mx-1 font-bold text-3xl text-from-geen-500 text-transparent bg-clip-text bg-gradient-to-r from-green-600 to-orange-600 ">
          <h1>Latest Product</h1>
        </div>
        <div v-if="latestProducts.length === 0" class="text-center py-12">
          <div class="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-green-600 mb-4"></div>
          <p class="text-gray-600 text-lg">Discovering amazing Nepali products...</p>
        </div>

        <!-- Products Grid -->
        <div v-else class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          <ProductCard
            v-for="product in latestProducts"
            :key="product.id"
            :product="{
              ...product,
              wishlisted: isProductWishlisted(product.id)
            }"
            @wishlist="handleWishlistUpdate"
            class="transform hover:scale-105 transition-transform duration-300"
          />
        </div>

        <!-- View All Button -->
        <div class="text-center mt-12">
          <router-link 
            to="/products" 
            class="inline-flex items-center px-8 py-3 bg-gradient-to-r from-green-600 to-orange-600 text-white font-semibold rounded-2xl hover:from-green-700 hover:to-orange-700 transition-all duration-300 transform hover:scale-105 shadow-lg"
          >
            <span>Explore All Nepali Products</span>
            <svg class="w-5 h-5 ml-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8l4 4m0 0l-4 4m4-4H3"/>
            </svg>
          </router-link>
        </div>
      </section>

      <!-- Cultural Heritage Section -->
      <section class="mb-16">
        <div class="bg-gradient-to-r from-green-600 to-orange-600 rounded-3xl p-8 text-white">
          <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 items-center">
            <div>
              <h2 class="text-3xl font-bold mb-4">Preserving Nepali Heritage</h2>
              <p class="text-green-100 text-lg mb-6">
                Every purchase supports local artisans and helps preserve traditional Nepali 
                craftsmanship that has been passed down through generations.
              </p>
              <ul class="space-y-3">
                <li class="flex items-center">
                  <span class="w-2 h-2 bg-white rounded-full mr-3"></span>
                  Supporting local artisans and their families
                </li>
                <li class="flex items-center">
                  <span class="w-2 h-2 bg-white rounded-full mr-3"></span>
                  Preserving traditional crafting techniques
                </li>
                <li class="flex items-center">
                  <span class="w-2 h-2 bg-white rounded-full mr-3"></span>
                  Promoting sustainable and eco-friendly products
                </li>
              </ul>
            </div>
            <div class="text-center">
              <div class="text-5xl mb-4">🇳🇵</div>
              <p class="text-green-100 text-xl font-semibold">
                Made in Nepal<br>
                <span class="text-white text-2xl">With Pride</span>
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
import Hero from "../components/ui/Hero.vue";

export default {
  name: "Home",
  components: { Navbar, Footer, ProductCard, Hero },

  data() {
    return {
      latestProducts: [],
      wishlistProductIds: [], // Store IDs of wishlisted products
    };
  },
  
  async mounted() {
    // Fetch products and wishlist in parallel
    await Promise.all([
      this.fetchLatestProducts(),
      this.fetchWishlist()
    ]);
  },

  methods: {
    async fetchLatestProducts() {
      // Fetch a reasonable number of products (e.g., 20) to find the latest 4
      const allProducts = await ProductService.getProducts(0, 20);
      
      // Sort by creation date (assuming you have createdAt field)
      // If you don't have createdAt, sort by ID (assuming higher ID = newer)
      const sortedProducts = allProducts.sort((a, b) => {
        // If you have createdAt timestamp
        if (a.createdAt && b.createdAt) {
          return new Date(b.createdAt) - new Date(a.createdAt);
        }
        // Fallback: sort by ID (higher ID usually means newer)
        return b.id - a.id;
      });
      
      // Take only the first 4 latest products
      this.latestProducts = sortedProducts.slice(0, 4);
    },

    async fetchWishlist() {
      try {
        const token = localStorage.getItem("buyer_jwt");
        if (!token) return; // Only fetch if user is logged in
        
        const wishlistProducts = await WishlistService.getWishlistProducts();
        this.wishlistProductIds = wishlistProducts.map(product => product.id);
        console.log("❤️ HomePage - Wishlisted product IDs:", this.wishlistProductIds);
      } catch (error) {
        console.error("Error fetching wishlist:", error);
      }
    },

    isProductWishlisted(productId) {
      return this.wishlistProductIds.includes(productId);
    },

    handleWishlistUpdate({ id, wishlisted }) {
      console.log(`🔄 HomePage - Wishlist updated for product ${id}: ${wishlisted}`);
      
      // Update local state
      if (wishlisted) {
        // Add to wishlist
        if (!this.wishlistProductIds.includes(id)) {
          this.wishlistProductIds.push(id);
        }
      } else {
        // Remove from wishlist
        const index = this.wishlistProductIds.indexOf(id);
        if (index !== -1) {
          this.wishlistProductIds.splice(index, 1);
        }
      }
      
      console.log("❤️ HomePage - Updated wishlist IDs:", this.wishlistProductIds);
    }
  }
};
</script>

<style scoped>
/* Smooth transitions for all interactive elements */
button, a {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Custom shadow for depth */
.shadow-xl {
  box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
}
</style>