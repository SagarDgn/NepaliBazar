<template>
  <div class="min-h-screen ">
     <Hero />
    <Navbar />
   
    
    <div class="container mx-auto px-4 py-8 max-w-7xl">
      <!-- Page Header -->
      <div class="mb-8 text-center">
        <h1 class="text-3xl font-bold text-gray-800 mb-2">All Products</h1>
        <p class="text-gray-600">Discover our amazing collection</p>
      </div>

      <!-- Products Grid -->
      <div class="mb-8">
        <div v-if="displayedProducts.length" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
          <ProductCard
            v-for="product in displayedProducts"
            :key="product.id"
            :product="{
              ...product,
              wishlisted: isProductWishlisted(product.id)
            }"
            @wishlist="handleWishlistUpdate"
          />
        </div>
        
        <!-- Empty State -->
        <div v-else class="text-center py-16">
          <div class="max-w-md mx-auto">
            <div class="w-16 h-16 mx-auto mb-4 text-gray-400">
              <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"/>
              </svg>
            </div>
            <h3 class="text-xl font-semibold text-gray-700 mb-2">No products found</h3>
            <p class="text-gray-500">We couldn't find any products at the moment.</p>
          </div>
        </div>
      </div>

      <!-- Pagination -->
      <div class="flex justify-center items-center gap-4 mt-8">
        <button
          @click="prevPage"
          :disabled="page === 0"
          class="px-6 py-2 bg-white border border-gray-300 rounded-lg hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed transition-colors duration-200"
        >
          Previous
        </button>
        
        <span class="text-gray-600 font-medium">
          Page {{ page + 1 }} of {{ totalPages }}
        </span>
        
        <button
          @click="nextPage"
          :disabled="page >= totalPages - 1"
          class="px-6 py-2 bg-white border border-gray-300 rounded-lg hover:bg-gray-50 disabled:opacity-50 disabled:cursor-not-allowed transition-colors duration-200"
        >
          Next
        </button>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";
import Footer from "../components/ui/Footer.vue";
import ProductCard from "../components/ProductCard.vue";
import ProductService from "../services/ProductService";
import WishlistService from "../services/WishlistService";

export default {
  name: "ProductPage",
  components: { Hero, Navbar, Footer, ProductCard },
  data() {
    return {
      allProducts: [], // Store all products
      page: 0,
      wishlistProductIds: [],
      pageSize: 12,
    };
  },
  computed: {
    // Calculate which products to display for current page
    displayedProducts() {
      const start = this.page * this.pageSize;
      const end = start + this.pageSize;
      return this.allProducts.slice(start, end);
    },
    // Calculate total pages
    totalPages() {
      return Math.ceil(this.allProducts.length / this.pageSize);
    }
  },
  async mounted() {
    await Promise.all([
      this.loadAllProducts(),
      this.fetchWishlist()
    ]);
  },
  methods: {
    async loadAllProducts() {
      try {
        // Fetch all products once
        this.allProducts = await ProductService.getProducts(0, 100); // Fetch large number
        console.log(`📄 Loaded ${this.allProducts.length} total products`);
      } catch (error) {
        console.error("Error loading products:", error);
        this.allProducts = [];
      }
    },

    async fetchWishlist() {
      try {
        const token = localStorage.getItem("buyer_jwt");
        if (!token) return; // Only fetch if user is logged in
        
        const wishlistProducts = await WishlistService.getWishlistProducts();
        this.wishlistProductIds = wishlistProducts.map(product => product.id);
        console.log("❤️ ProductPage - Wishlisted product IDs:", this.wishlistProductIds);
      } catch (error) {
        console.error("Error fetching wishlist:", error);
      }
    },

    isProductWishlisted(productId) {
      return this.wishlistProductIds.includes(productId);
    },

    handleWishlistUpdate({ id, wishlisted }) {
      console.log(`🔄 ProductPage - Wishlist updated for product ${id}: ${wishlisted}`);
      
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
      
      console.log("❤️ ProductPage - Updated wishlist IDs:", this.wishlistProductIds);
    },

    nextPage() {
      if (this.page < this.totalPages - 1) {
        this.page++;
      }
    },
    
    prevPage() {
      if (this.page > 0) {
        this.page--;
      }
    },
  },
};
</script>

<style scoped>
button {
  transition: all 0.2s ease-in-out;
}
</style>