<template>
  <div class="min-h-screen flex flex-col bg-white dark:bg-gray-900">
    <!-- Header -->
    <div class="p-8 border-b border-gray-300 dark:border-gray-700 bg-white dark:bg-gray-800">
      <div class="flex items-center space-x-4">
        <div class="p-3 bg-black rounded-2xl shadow">
          <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"></path>
          </svg>
        </div>
        <div>
          <h1 class="text-3xl font-bold text-gray-900 dark:text-white">
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
        <div class="group bg-white dark:bg-gray-800 rounded-xl p-6 shadow border border-gray-200 dark:border-gray-700 transition-all duration-300">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-gray-600 dark:text-gray-400 text-sm font-semibold mb-2">TOTAL PRODUCTS</p>
              <p class="text-3xl font-bold text-gray-900 dark:text-white">{{ totalProducts }}</p>
              <p class="text-gray-500 dark:text-gray-500 text-sm mt-2">Active listings</p>
            </div>
            <div class="p-3 bg-gray-900 rounded-xl shadow">
              <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"></path>
              </svg>
            </div>
          </div>
        </div>

        <!-- Inventory Value -->
        <div class="group bg-white dark:bg-gray-800 rounded-xl p-6 shadow border border-gray-200 dark:border-gray-700 transition-all duration-300">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-gray-600 dark:text-gray-400 text-sm font-semibold mb-2">INVENTORY VALUE</p>
              <p class="text-3xl font-bold text-gray-900 dark:text-white">NRP.{{ totalInventoryValue }}</p>
              <p class="text-gray-500 dark:text-gray-500 text-sm mt-2">Total stock worth</p>
            </div>
            <div class="p-3 bg-gray-900 rounded-xl shadow flex items-center justify-center">
              <span class="text-white font-bold text-sm">NRP.</span>
            </div>
          </div>
        </div>

        <!-- Low Stock -->
        <div class="group bg-white dark:bg-gray-800 rounded-xl p-6 shadow border border-gray-200 dark:border-gray-700 transition-all duration-300">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-amber-600 dark:text-amber-400 text-sm font-semibold mb-2">LOW STOCK</p>
              <p class="text-3xl font-bold text-gray-900 dark:text-white">{{ lowStockCount }}</p>
              <p class="text-amber-500 dark:text-amber-500 text-sm mt-2">Need restocking</p>
            </div>
            <div class="p-3 bg-amber-500 rounded-xl shadow">
              <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L4.35 16.5c-.77.833.192 2.5 1.732 2.5z"></path>
              </svg>
            </div>
          </div>
        </div>

        <!-- Average Price -->
        <div class="group bg-white dark:bg-gray-800 rounded-xl p-6  border border-gray-200 dark:border-gray-700 transition-all duration-300">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-gray-600 dark:text-gray-400 text-sm font-semibold mb-2">AVG PRICE</p>
              <p class="text-3xl font-bold text-gray-900 dark:text-white">NRP.{{ averagePrice }}</p>
              <p class="text-gray-500 dark:text-gray-500 text-sm mt-2">Per product</p>
            </div>
            <div class="p-3 bg-gray-900 rounded-xl shadow flex items-center justify-center">
              <span class="text-white font-bold text-sm">NRP.</span>
            </div>
          </div>
        </div>
      </div>
      

      <!-- Charts & Additional Info -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <!-- Low Stock Alert -->
        <div class="bg-white dark:bg-gray-800 rounded-xl p-6 shadow   dark:border-amber-800/30">
          <div class="flex items-center space-x-3 mb-6">
            <div class="p-2 bg-amber-100 dark:bg-amber-900/30 rounded-lg">
              <svg class="w-5 h-5 text-amber-600 dark:text-amber-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L4.35 16.5c-.77.833.192 2.5 1.732 2.5z"></path>
              </svg>
            </div>
            <h3 class="text-lg font-bold text-gray-900 dark:text-white">Low Stock Alert</h3>
            <span class="px-3 py-1 bg-amber-500 text-white text-sm font-semibold rounded-full">
              {{ lowStockProducts.length }} items
            </span>
          </div>

          <div v-if="lowStockProducts.length > 0" class="space-y-4">
            <div 
              v-for="product in lowStockProducts" 
              :key="product.id"
              class="flex items-center justify-between p-4 bg-amber-50 dark:bg-amber-900/10 rounded-lg border border-amber-200 dark:border-amber-800/30 transition-all duration-300"
            >
              <div class="flex items-center space-x-4">
                <div class="w-12 h-12 bg-amber-100 dark:bg-amber-800/30 rounded-lg flex items-center justify-center">
                  <svg class="w-6 h-6 text-amber-600 dark:text-amber-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4"></path>
                  </svg>
                </div>
                <div>
                  <h4 class="font-semibold text-gray-900 dark:text-white text-sm">{{ product.name }}</h4>
                  <p class="text-amber-600 dark:text-amber-400 text-xs font-medium mt-1">
                    Only {{ product.quantity }} left in stock
                  </p>
                </div>
              </div>
              <router-link 
                :to="{ name: 'AddProduct', query: { edit: product.id }, state: { productData: product } }"
                class="px-4 py-2 bg-amber-500 text-white text-sm font-semibold rounded-lg transition-all duration-300 shadow"
              >
                Restock
              </router-link>
            </div>
          </div>
          <div v-else class="text-center py-8">
            <div class="w-16 h-16 bg-emerald-100 dark:bg-emerald-900/30 rounded-lg flex items-center justify-center mx-auto mb-4">
              <svg class="w-8 h-8 text-emerald-600 dark:text-emerald-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
              </svg>
            </div>
            <p class="text-emerald-600 dark:text-emerald-400 font-semibold">All products are well stocked!</p>
          </div>
        </div>

        <!-- Performance Overview -->
        <div class="bg-white dark:bg-gray-800 rounded-xl p-6 shadow  dark:border-gray-700">
          <div class="flex items-center space-x-3 mb-6">
            <div class="p-2 bg-gray-100 dark:bg-gray-700 rounded-lg">
              <svg class="w-5 h-5 text-gray-700 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6"></path>
              </svg>
            </div>
            <h3 class="text-lg font-bold text-gray-900 dark:text-white">Performance Overview</h3>
          </div>

          <div class="space-y-6">
            <!-- Stock Distribution -->
            <div>
              <h4 class="text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3">Stock Distribution</h4>
              <div class="space-y-3">
                <div class="flex items-center justify-between">
                  <span class="text-sm text-gray-600 dark:text-gray-400">Well Stocked</span>
                  <span class="font-semibold text-emerald-600 dark:text-emerald-400">{{ wellStockedCount }} products</span>
                </div>
                <div class="w-full bg-gray-200 dark:bg-gray-700 rounded-full h-2">
                  <div class="bg-emerald-500 h-2 rounded-full" :style="{ width: `${(wellStockedCount / totalProducts) * 100}%` }"></div>
                </div>
              </div>
              
              <div class="space-y-3 mt-4">
                <div class="flex items-center justify-between">
                  <span class="text-sm text-gray-600 dark:text-gray-400">Low Stock</span>
                  <span class="font-semibold text-amber-600 dark:text-amber-400">{{ lowStockCount }} products</span>
                </div>
                <div class="w-full bg-gray-200 dark:bg-gray-700 rounded-full h-2">
                  <div class="bg-amber-500 h-2 rounded-full" :style="{ width: `${(lowStockCount / totalProducts) * 100}%` }"></div>
                </div>
              </div>
            </div>

            <!-- Quick Actions -->
            <div class="pt-4 border-t border-gray-200 dark:border-gray-700">
              <h4 class="text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3">Quick Actions</h4>
              <div class="grid grid-cols-2 gap-3">
                <router-link 
                  to="/sellerdashboard/add-product"
                  class="p-3 bg-black text-white text-sm font-semibold rounded-lg transition-all duration-300 shadow text-center"
                >
                  Add Product
                </router-link>
                <router-link 
                  to="/sellerdashboard/products"
                  class="p-3 bg-gray-800 text-white text-sm font-semibold rounded-lg transition-all duration-300 shadow text-center"
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