<template>
  <div>
    <!-- Header Section -->
    <div class="p-8 border-b border-gray-200 dark:border-gray-700 bg-white dark:bg-gray-800">
      <div class="flex flex-col sm:flex-row sm:items-center sm:justify-between">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white">
            Product Catalog
          </h1>
          <p class="text-gray-600 dark:text-gray-400 mt-2">Manage your product inventory and listings</p>
        </div>
        <div class="flex space-x-3 mt-4 sm:mt-0">
          <button 
            @click="fetchProducts"
            :disabled="loading"
            class="px-6 py-3 bg-white dark:bg-gray-700 border border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 font-semibold rounded-lg hover:bg-gray-50 dark:hover:bg-gray-600 transition-all duration-200 flex items-center space-x-2 disabled:opacity-50 disabled:cursor-not-allowed"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"></path>
            </svg>
            <span>Refresh</span>
          </button>
          <router-link 
            to="/sellerdashboard/add-product"
            class="px-6 py-3 bg-black dark:bg-white text-white dark:text-gray-900 font-semibold rounded-lg  dark:hover:bg-gray-100 transition-all duration-200 flex items-center space-x-2 border border-black dark:border-white"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path>
            </svg>
            <span>Add Product</span>
          </router-link>
        </div>
      </div>
    </div>

    <!-- Content Section -->
    <div class="p-8">
      <!-- Loading State -->
      <div v-if="loading" class="text-center py-16">
        <div class="inline-flex items-center justify-center w-16 h-16 bg-gray-100 dark:bg-gray-700 rounded-full mb-4">
          <svg class="w-8 h-8 text-gray-400 animate-spin" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"></path>
          </svg>
        </div>
        <p class="text-gray-600 dark:text-gray-400 text-lg">Loading your products...</p>
      </div>

      <!-- Empty State -->
      <div v-else-if="products.length === 0" class="text-center py-16">
        <div class="w-32 h-32 bg-gray-100 dark:bg-gray-700 rounded-2xl flex items-center justify-center mx-auto mb-6">
          <svg class="w-16 h-16 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"></path>
          </svg>
        </div>
        <h3 class="text-2xl font-bold text-gray-900 dark:text-white mb-2">No products yet</h3>
        <p class="text-gray-600 dark:text-gray-400 mb-8 max-w-md mx-auto">
          Start your journey by adding your first product to showcase on our platform.
        </p>
        <router-link 
          to="/sellerdashboard/add-product"
          class="inline-flex items-center px-8 py-4 bg-black dark:bg-white text-white dark:text-gray-900 font-semibold rounded-lg  dark:hover:bg-gray-100 transition-all duration-200 border border-black dark:border-white"
        >
          <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path>
          </svg>
          Add Your First Product
        </router-link>
      </div>

      <!-- Products Grid -->
      <div v-else class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <div 
          v-for="product in products" 
          :key="product.id"
          class="group bg-white dark:bg-gray-800 rounded-xl border border-gray-200 dark:border-gray-700 overflow-hidden hover:shadow-lg transition-all duration-300"
        >
          <!-- Product Image -->
          <div class="relative h-48 bg-gray-200 dark:bg-gray-600 overflow-hidden">
            <img 
              :src="product.imageUrl || '/api/placeholder/400/300'" 
              :alt="product.name"
              class="w-full h-full object-cover transform group-hover:scale-105 transition-transform duration-300"
            >
            <div class="absolute top-4 right-4 flex space-x-2">
              <span v-if="product.discount > 0" class="px-3 py-1 bg-red-500 text-white text-xs font-bold rounded-full">
                -{{ product.discount }}%
              </span>
              <span v-if="product.quantity < 10" class="px-3 py-1 bg-yellow-500 text-white text-xs font-bold rounded-full">
                Low Stock
              </span>
            </div>
          </div>

          <!-- Product Info -->
          <div class="p-6">
            <h3 class="font-bold text-gray-900 dark:text-white text-lg mb-2 line-clamp-2">{{ product.name }}</h3>
            <p class="text-gray-600 dark:text-gray-400 text-sm mb-4 line-clamp-2">{{ product.description }}</p>
            
            <!-- Price & Quantity -->
            <div class="flex items-center justify-between mb-4">
              <div class="flex items-center space-x-2">
                <span class="text-2xl font-bold text-gray-900 dark:text-white">
                  Rs.{{ calculateDiscountedPrice(product.price, product.discount) }}
                </span>
                <span v-if="product.discount > 0" class="text-sm text-gray-500 line-through">
                  Rs.{{ product.price }}
                </span>
              </div>
              <span class="text-sm font-semibold px-3 py-1 rounded-full border" 
                    :class="product.quantity > 10 
                      ? 'bg-green-50 text-green-700 border-green-200 dark:bg-green-900/20 dark:text-green-400 dark:border-green-800' 
                      : 'bg-yellow-50 text-yellow-700 border-yellow-200 dark:bg-yellow-900/20 dark:text-yellow-400 dark:border-yellow-800'">
                {{ product.quantity }} in stock
              </span>
            </div>

            <!-- Actions -->
            <div class="flex space-x-3">
              <button 
                @click="editProduct(product)"
                class="flex-1 bg-white dark:bg-gray-700 border border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 py-3 px-4 rounded-lg font-semibold hover:bg-gray-50 dark:hover:bg-gray-600 transition-all duration-200 flex items-center justify-center space-x-2"
              >
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                </svg>
                <span>Edit</span>
              </button>
              <button 
                @click="deleteProduct(product.id)"
                class="flex-1 bg-black dark:bg-white text-white dark:text-gray-900 py-3 px-4 rounded-lg font-semibold  dark:hover:bg-gray-100 transition-all duration-200 flex items-center justify-center space-x-2 border border-black dark:border-white"
              >
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                </svg>
                <span>Delete</span>
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Load More / Pagination -->
      <div v-if="products.length > 0" class="mt-8 flex justify-center">
        <button 
          class="px-6 py-3 bg-white dark:bg-gray-800 border border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 font-semibold rounded-lg hover:bg-gray-50 dark:hover:bg-gray-700 transition-all duration-200"
          @click="loadMoreProducts"
        >
          Load More Products
        </button>
      </div>
    </div>
    <SellerFooter/>
  </div>
</template>

<script>
import SellerFooter from '../components/ui/SellerFooter.vue';
import ProductService from '../services/ProductService'

export default {
    components: {SellerFooter},
  name: 'SellerProducts',
  data() {
    return {
      products: [],
      loading: false,
      currentPage: 1,
      hasMore: true
    }
  },
  async mounted() {
    await this.fetchProducts()
  },
  methods: {
    async fetchProducts() {
      this.loading = true
      try {
        const response = await ProductService.getMyProducts()
        this.products = response
        this.$parent.showToast('Products loaded successfully!', 'success')
      } catch (error) {
        console.error('Error fetching products:', error)
        this.$parent.showToast('Failed to load products', 'error')
      } finally {
        this.loading = false
      }
    },

    async loadMoreProducts() {
      this.currentPage++
      try {
        const response = await ProductService.getMyProducts(this.currentPage)
        if (response.length === 0) {
          this.hasMore = false
          this.$parent.showToast('All products loaded', 'info')
        } else {
          this.products = [...this.products, ...response]
        }
      } catch (error) {
        console.error('Error loading more products:', error)
        this.$parent.showToast('Failed to load more products', 'error')
      }
    },

    calculateDiscountedPrice(price, discount) {
      return (price * (1 - discount / 100)).toFixed(2)
    },

    editProduct(product) {
      this.$router.push({
        name: 'AddProduct',
        query: { edit: product.id },
        state: { productData: product }
      })
    },

    async deleteProduct(productId) {
      try {
        const Swal = (await import('sweetalert2')).default;

        const result = await Swal.fire({
          title: 'Delete Product?',
          text: 'Are you sure you want to delete this product? This action cannot be undone.',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#dc2626',
          cancelButtonColor: '#6b7280',
          confirmButtonText: 'Delete Product',
          cancelButtonText: 'Cancel',
          background: document.documentElement.classList.contains('dark') ? '#1f2937' : '#ffffff',
          color: document.documentElement.classList.contains('dark') ? '#ffffff' : '#000000',
          iconColor: '#dc2626',
        });

        if (!result.isConfirmed) {
          return;
        }

        await ProductService.deleteProduct(productId);
        this.$parent.showToast('Product deleted successfully!', 'success');
        await this.fetchProducts();
      } catch (error) {
        console.error('Error deleting product:', error);
        this.$parent.showToast('Failed to delete product', 'error');
      }
    }
  }
}
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

/* Smooth transitions for all interactive elements */
button, .router-link {
  transition: all 0.2s ease-in-out;
}

/* Custom focus styles for accessibility */
button:focus, .router-link:focus {
  outline: 2px solid #3b82f6;
  outline-offset: 2px;
}
</style>