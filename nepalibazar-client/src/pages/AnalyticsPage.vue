<template>
  <div class="min-h-screen flex flex-col">
    <!-- Header -->
    <div class="p-8 border-b border-gray-200/30 dark:border-gray-700/30 bg-gradient-to-r from-white to-gray-50 dark:from-gray-800 dark:to-gray-700">
      <div class="flex items-center space-x-4">
        <div class="p-3 bg-gradient-to-r from-purple-500 to-indigo-500 rounded-2xl shadow-lg">
          <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"></path>
          </svg>
        </div>
        <div>
          <h1 class="text-3xl font-bold bg-gradient-to-r from-purple-600 to-indigo-600 bg-clip-text text-transparent">
            Business Analytics
          </h1>
          <p class="text-gray-600 dark:text-gray-400 mt-2">Track your store performance and insights</p>
        </div>
      </div>
    </div>

    <!-- Analytics Content -->
    <div class="flex-1 p-8">
      <!-- Stats Grid -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <!-- Total Products -->
        <div class="group bg-gradient-to-br from-white to-green-50 dark:from-gray-800 dark:to-green-900/20 rounded-3xl p-6 shadow-lg border border-green-200/50 dark:border-green-800/30 hover:shadow-xl transition-all duration-500 transform hover:scale-105">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-green-600 dark:text-green-400 text-sm font-semibold mb-2">TOTAL PRODUCTS</p>
              <p class="text-3xl font-bold text-gray-800 dark:text-white">{{ totalProducts }}</p>
              <p class="text-green-600 dark:text-green-400 text-sm mt-2">Active listings</p>
            </div>
            <div class="p-3 bg-gradient-to-r from-green-500 to-emerald-500 rounded-2xl shadow-lg">
              <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"></path>
              </svg>
            </div>
          </div>
        </div>

        <!-- Inventory Value -->
        <div class="group bg-gradient-to-br from-white to-blue-50 dark:from-gray-800 dark:to-blue-900/20 rounded-3xl p-6 shadow-lg border border-blue-200/50 dark:border-blue-800/30 hover:shadow-xl transition-all duration-500 transform hover:scale-105">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-blue-600 dark:text-blue-400 text-sm font-semibold mb-2">INVENTORY VALUE</p>
              <p class="text-3xl font-bold text-gray-800 dark:text-white">Rs.{{ totalInventoryValue }}</p>
              <p class="text-blue-600 dark:text-blue-400 text-sm mt-2">Total stock worth</p>
            </div>
           <div class="p-3 bg-gradient-to-r from-blue-500 to-cyan-500 rounded-2xl shadow-lg flex items-center justify-center">
  <span class="text-white font-bold text-sm">Rs.</span>
</div>
          </div>
        </div>

        <!-- Low Stock -->
        <div class="group bg-gradient-to-br from-white to-orange-50 dark:from-gray-800 dark:to-orange-900/20 rounded-3xl p-6 shadow-lg border border-orange-200/50 dark:border-orange-800/30 hover:shadow-xl transition-all duration-500 transform hover:scale-105">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-orange-600 dark:text-orange-400 text-sm font-semibold mb-2">LOW STOCK</p>
              <p class="text-3xl font-bold text-gray-800 dark:text-white">{{ lowStockCount }}</p>
              <p class="text-orange-600 dark:text-orange-400 text-sm mt-2">Need restocking</p>
            </div>
            <div class="p-3 bg-gradient-to-r from-orange-500 to-amber-500 rounded-2xl shadow-lg">
              <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L4.35 16.5c-.77.833.192 2.5 1.732 2.5z"></path>
              </svg>
            </div>
          </div>
        </div>

        <!-- Average Price -->
        <div class="group bg-gradient-to-br from-white to-purple-50 dark:from-gray-800 dark:to-purple-900/20 rounded-3xl p-6 shadow-lg border border-purple-200/50 dark:border-purple-800/30 hover:shadow-xl transition-all duration-500 transform hover:scale-105">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-purple-600 dark:text-purple-400 text-sm font-semibold mb-2">AVG PRICE</p>
              <p class="text-3xl font-bold text-gray-800 dark:text-white">Rs.{{ averagePrice }}</p>
              <p class="text-purple-600 dark:text-purple-400 text-sm mt-2">Per product</p>
            </div>
           <div class="p-3 bg-gradient-to-r from-purple-500 to-violet-500 rounded-2xl shadow-lg flex items-center justify-center">
  <span class="text-white font-bold text-sm">Rs.</span>
</div>
          </div>
        </div>
      </div>

      <!-- Charts & Additional Info -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <!-- Low Stock Alert -->
        <div class="bg-gradient-to-br from-white to-yellow-50 dark:from-gray-800 dark:to-yellow-900/10 rounded-3xl p-6 shadow-lg border border-yellow-200/50 dark:border-yellow-800/30">
          <div class="flex items-center space-x-3 mb-6">
            <div class="p-2 bg-yellow-100 dark:bg-yellow-900/30 rounded-xl">
              <svg class="w-5 h-5 text-yellow-600 dark:text-yellow-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L4.35 16.5c-.77.833.192 2.5 1.732 2.5z"></path>
              </svg>
            </div>
            <h3 class="text-lg font-bold text-gray-800 dark:text-white">Low Stock Alert</h3>
            <span class="px-3 py-1 bg-yellow-500 text-white text-sm font-semibold rounded-full animate-pulse">
              {{ lowStockProducts.length }} items
            </span>
          </div>

          <div v-if="lowStockProducts.length > 0" class="space-y-4">
            <div 
              v-for="product in lowStockProducts" 
              :key="product.id"
              class="flex items-center justify-between p-4 bg-white dark:bg-gray-700 rounded-2xl border border-yellow-200 dark:border-yellow-800 hover:shadow-md transition-all duration-300 group"
            >
              <div class="flex items-center space-x-4">
                <div class="w-12 h-12 bg-gradient-to-br from-yellow-200 to-orange-200 dark:from-yellow-600 dark:to-orange-600 rounded-xl flex items-center justify-center">
                  <svg class="w-6 h-6 text-yellow-600 dark:text-yellow-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4"></path>
                  </svg>
                </div>
                <div>
                  <h4 class="font-semibold text-gray-800 dark:text-white text-sm">{{ product.name }}</h4>
                  <p class="text-yellow-600 dark:text-yellow-400 text-xs font-medium mt-1">
                    Only {{ product.quantity }} left in stock
                  </p>
                </div>
              </div>
              <router-link 
                :to="{ name: 'AddProduct', query: { edit: product.id }, state: { productData: product } }"
                class="px-4 py-2 bg-gradient-to-r from-yellow-500 to-orange-500 hover:from-yellow-600 hover:to-orange-600 text-white text-sm font-semibold rounded-xl transition-all duration-300 transform group-hover:scale-105 shadow-lg"
              >
                Restock
              </router-link>
            </div>
          </div>
          <div v-else class="text-center py-8">
            <div class="w-16 h-16 bg-gradient-to-br from-green-200 to-emerald-200 dark:from-green-600 dark:to-emerald-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-green-600 dark:text-green-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
              </svg>
            </div>
            <p class="text-gray-600 dark:text-gray-400 font-semibold">All products are well stocked! 🎉</p>
          </div>
        </div>

        <!-- Performance Overview -->
        <div class="bg-gradient-to-br from-white to-blue-50 dark:from-gray-800 dark:to-blue-900/10 rounded-3xl p-6 shadow-lg border border-blue-200/50 dark:border-blue-800/30">
          <div class="flex items-center space-x-3 mb-6">
            <div class="p-2 bg-blue-100 dark:bg-blue-900/30 rounded-xl">
              <svg class="w-5 h-5 text-blue-600 dark:text-blue-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6"></path>
              </svg>
            </div>
            <h3 class="text-lg font-bold text-gray-800 dark:text-white">Performance Overview</h3>
          </div>

          <div class="space-y-6">
            <!-- Stock Distribution -->
            <div>
              <h4 class="text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3">Stock Distribution</h4>
              <div class="space-y-3">
                <div class="flex items-center justify-between">
                  <span class="text-sm text-gray-600 dark:text-gray-400">Well Stocked</span>
                  <span class="font-semibold text-green-600 dark:text-green-400">{{ wellStockedCount }} products</span>
                </div>
                <div class="w-full bg-gray-200 dark:bg-gray-700 rounded-full h-2">
                  <div class="bg-green-500 h-2 rounded-full" :style="{ width: `${(wellStockedCount / totalProducts) * 100}%` }"></div>
                </div>
              </div>
              
              <div class="space-y-3 mt-4">
                <div class="flex items-center justify-between">
                  <span class="text-sm text-gray-600 dark:text-gray-400">Low Stock</span>
                  <span class="font-semibold text-yellow-600 dark:text-yellow-400">{{ lowStockCount }} products</span>
                </div>
                <div class="w-full bg-gray-200 dark:bg-gray-700 rounded-full h-2">
                  <div class="bg-yellow-500 h-2 rounded-full" :style="{ width: `${(lowStockCount / totalProducts) * 100}%` }"></div>
                </div>
              </div>
            </div>

            <!-- Quick Actions -->
            <div class="pt-4 border-t border-gray-200 dark:border-gray-700">
              <h4 class="text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3">Quick Actions</h4>
              <div class="grid grid-cols-2 gap-3">
                <router-link 
                  to="/sellerdashboard/add-product"
                  class="p-3 bg-gradient-to-r from-green-500 to-emerald-500 hover:from-green-600 hover:to-emerald-600 text-white text-sm font-semibold rounded-xl transition-all duration-300 transform hover:scale-105 shadow-lg text-center"
                >
                  Add Product
                </router-link>
                <router-link 
                  to="/sellerdashboard/products"
                  class="p-3 bg-gradient-to-r from-blue-500 to-cyan-500 hover:from-blue-600 hover:to-cyan-600 text-white text-sm font-semibold rounded-xl transition-all duration-300 transform hover:scale-105 shadow-lg text-center"
                >
                  View All
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Footer -->
    <SellerFooter />
  </div>
</template>

<script>
import SellerFooter from '../components/ui/SellerFooter.vue';
import ProductService from '../services/ProductService';

export default {
  name: 'SellerAnalytics',
  components: { SellerFooter },
  data() {
    return {
      products: []
    }
  },
  async mounted() {
    await this.fetchProducts()
  },
  computed: {
    totalProducts() {
      return this.products.length
    },
    totalInventoryValue() {
      return this.products.reduce((total, product) => {
        const discountedPrice = product.price * (1 - product.discount / 100)
        return total + (discountedPrice * product.quantity)
      }, 0).toFixed(2)
    },
    averagePrice() {
      if (this.products.length === 0) return '0.00'
      const total = this.products.reduce((sum, product) => sum + product.price, 0)
      return (total / this.products.length).toFixed(2)
    },
    lowStockCount() {
      return this.products.filter(product => product.quantity < 10).length
    },
    wellStockedCount() {
      return this.products.filter(product => product.quantity >= 10).length
    },
    lowStockProducts() {
      return this.products.filter(product => product.quantity < 10)
    }
  },
  methods: {
    async fetchProducts() {
      try {
        this.products = await ProductService.getMyProducts()
      } catch (error) {
        console.error('Error fetching products for analytics:', error)
        this.$parent.showToast('Failed to load analytics data', 'error')
      }
    }
  }
}
</script>