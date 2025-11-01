<template>
  <SellerHeader class=""/>
  <div class="min-h-screen bg-gradient-to-br from-gray-50 via-green-50 to-orange-50 dark:from-gray-900 dark:via-gray-800 dark:to-gray-700 transition-all duration-500">
    <!-- Custom Notification -->
    <CustomNotification 
      :show="showNotification" 
      :message="notificationMessage" 
      :type="notificationType"
      @hide="showNotification = false"
    />
    
    <!-- Header -->
    <header class="bg-white/80 dark:bg-gray-800/80 backdrop-blur-xl border-b border-gray-200/30 dark:border-gray-700/30 shadow-sm sticky top-0 z-40">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center py-4">
          <div class="flex items-center space-x-4">
            <router-link to="/" class="flex items-center space-x-3 group">
              <div class="relative">
                <!--<img 
                  src="../assets/logo.PNG" 
                  alt="company logo" 
                  width="120" 
                  class="transform group-hover:scale-105 transition-all duration-300 rounded-2xl shadow-lg border-2 border-white dark:border-gray-700"
                >-->
                <div class="absolute inset-0 bg-gradient-to-r from-green-500 to-orange-500 rounded-2xl opacity-0 group-hover:opacity-20 transition-opacity duration-300"></div>
              </div>
              <div>
                <h1 class="text-2xl font-bold bg-gradient-to-r from-green-600 to-orange-600 bg-clip-text text-transparent">
                 NepaliBazar Seller Center
                </h1>
                <p class="text-gray-600 dark:text-gray-400 text-sm">Manage your products & grow your business</p>
              </div>
            </router-link>
          </div>
          <div class="flex items-center space-x-3">
            <button 
              @click="toggleDarkMode"
              class="p-3 rounded-2xl bg-gradient-to-r from-gray-100 to-gray-200 dark:from-gray-700 dark:to-gray-600 text-gray-600 dark:text-gray-300 hover:shadow-lg transform hover:scale-105 transition-all duration-300 group"
              :title="isDarkMode ? 'Switch to Light Mode' : 'Switch to Dark Mode'"
            >
              <svg v-if="isDarkMode" class="w-5 h-5 transform group-hover:rotate-180 transition-transform duration-500" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z" clip-rule="evenodd"></path>
              </svg>
              <svg v-else class="w-5 h-5 transform group-hover:rotate-180 transition-transform duration-500" fill="currentColor" viewBox="0 0 20 20">
                <path d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"></path>
              </svg>
            </button>
            <button 
              @click="logout"
              :disabled="loading"
              :class="[
                'px-6 py-3 font-semibold rounded-2xl transition-all duration-300 transform shadow-lg flex items-center space-x-2',
                loading 
                  ? 'bg-gray-400 cursor-not-allowed text-gray-200' 
                  : 'bg-gradient-to-r from-red-500 to-red-600 hover:from-red-600 hover:to-red-700 text-white hover:scale-105'
              ]"
            >
              <svg v-if="!loading" class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"></path>
              </svg>
              <svg v-else class="w-4 h-4 animate-spin" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 2v4m0 12v4m8-10h-4M6 12H2m15.364-7.364l-2.828 2.828M7.464 17.536l-2.828 2.828m12.728 0l-2.828-2.828M7.464 6.464L4.636 3.636"></path>
              </svg>
              <span>{{ loading ? 'Logging out...' : 'Logout' }}</span>
            </button>
          </div>
        </div>
      </div>
    </header>

    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="flex flex-col lg:flex-row gap-8">
        <!-- Sidebar -->
        <aside class="lg:w-80 flex-shrink-0">
          <div class="bg-white/80 dark:bg-gray-800/80 backdrop-blur-xl rounded-3xl shadow-xl border border-gray-200/30 dark:border-gray-700/30 p-6 sticky top-32">
            <nav class="space-y-3">
              <router-link 
                v-for="item in navigation"
                :key="item.name"
                :to="item.to"
                :class="[
                  'group flex items-center space-x-4 px-4 py-4 rounded-2xl transition-all duration-300 transform hover:scale-105',
                  $route.name === item.name 
                    ? 'bg-gradient-to-r from-green-500 to-orange-500 text-white shadow-lg' 
                    : 'text-gray-600 dark:text-gray-300 hover:bg-white/50 dark:hover:bg-gray-700/50 hover:shadow-md'
                ]"
              >
                <div :class="[
                  'p-2 rounded-xl transition-all duration-300',
                  $route.name === item.name 
                    ? 'bg-white/20' 
                    : 'bg-gray-100 dark:bg-gray-700 group-hover:bg-gradient-to-r group-hover:from-green-500 group-hover:to-orange-500 group-hover:text-white'
                ]">
                  <component :is="item.icon" class="w-5 h-5" />
                </div>
                <span class="font-semibold">{{ item.name }}</span>
                <div v-if="item.badge" class="ml-auto">
                  <span class="px-2 py-1 text-xs rounded-full bg-red-500 text-white animate-pulse">
                    {{ item.badge }}
                  </span>
                </div>
              </router-link>
            </nav>

            <!-- Quick Stats -->
            <div class="mt-8 pt-6 border-t border-gray-200/30 dark:border-gray-700/30">
              <h3 class="text-sm font-semibold text-gray-500 dark:text-gray-400 mb-4">QUICK STATS</h3>
              <div class="space-y-3">
                <div class="flex justify-between items-center p-3 rounded-xl bg-green-50/50 dark:bg-green-900/20">
                  <span class="text-sm text-gray-600 dark:text-gray-400">Total Products</span>
                  <span class="font-bold text-green-600 dark:text-green-400">{{ quickStats.totalProducts }}</span>
                </div>
                <div class="flex justify-between items-center p-3 rounded-xl bg-blue-50/50 dark:bg-blue-900/20">
                  <span class="text-sm text-gray-600 dark:text-gray-400">Low Stock</span>
                  <span class="font-bold text-blue-600 dark:text-blue-400">{{ quickStats.lowStock }}</span>
                </div>
              </div>
            </div>
          </div>
        </aside>

        <!-- Main Content -->
        <main class="flex-1 min-w-0">
          <div class="bg-white/80 dark:bg-gray-800/80 backdrop-blur-xl rounded-3xl shadow-xl border border-gray-200/30 dark:border-gray-700/30 overflow-hidden">
            <router-view></router-view>
          </div>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import CustomNotification from '../components/ui/CustomNotification.vue'
import SellerHeader from '../components/ui/SellerHeader.vue';
import sellerAuthService from '../services/SellerAuthService' // Import the auth service
import { ShoppingBagIcon, PlusCircleIcon, ChartBarIcon } from 'lucide-vue-next';

export default {
  name: 'SellerDashboard',
  components: { CustomNotification ,SellerHeader},
  data() {
    return {
      isDarkMode: false,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success',
      loading: false, // Add loading state
      quickStats: {
        totalProducts: 0,
        lowStock: 0
      },
      navigation: [
        {
          name: 'My Products',
          to: '/sellerdashboard/products',
          icon: ShoppingBagIcon,
          badge: null
        },
        {
          name: 'Add Product',
          to: '/sellerdashboard/add-product',
          icon: PlusCircleIcon,
          badge: null
        },
        {
          name: 'Analytics',
          to: '/sellerdashboard/analytics',
          icon: ChartBarIcon,
          badge: null
        }
      ]
    }
  },
  mounted() {
    this.initializeDarkMode()
    this.loadQuickStats()
  },
  methods: {
    initializeDarkMode() {
      const savedMode = localStorage.getItem('darkMode')
      this.isDarkMode = savedMode === 'true'
      this.applyDarkMode()
    },

    toggleDarkMode() {
      this.isDarkMode = !this.isDarkMode
      this.applyDarkMode()
      localStorage.setItem('darkMode', this.isDarkMode.toString())
    },

    applyDarkMode() {
      if (this.isDarkMode) {
        document.documentElement.classList.add('dark')
      } else {
        document.documentElement.classList.remove('dark')
      }
    },

    async loadQuickStats() {
      // This would be populated from your API
      // For now, using placeholder data
      this.quickStats = {
        totalProducts: 12,
        lowStock: 3
      }
    },

    showToast(message, type = 'success') {
      this.notificationMessage = message
      this.notificationType = type
      this.showNotification = true
    },

    async logout() {
      if (this.loading) return;
      
      this.loading = true;
      
      try {
        // Call the backend logout service
        const result = await sellerAuthService.logout();
        
        if (result.code === 200) {
          this.showToast(result.message || 'Logged out successfully!', 'success');
        } else {
          // Still show success even if backend had issues
          this.showToast('Logged out successfully!', 'success');
        }
        
        // Redirect after short delay
        setTimeout(() => {
          this.$router.push('/sellerlogin');
        }, 1000);
        
      } catch (error) {
        console.error('Unexpected logout error:', error);
        // Even if there's an unexpected error, clear local storage and redirect
        this.showToast('Logged out successfully!', 'success');
        
        setTimeout(() => {
          this.$router.push('/sellerlogin');
        }, 1000);
      } finally {
        this.loading = false;
      }
    }
  }
}
</script>

<style scoped>
/* Smooth transitions */
* {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Custom scrollbar */
::-webkit-scrollbar {
  width: 6px;
}

::-webkit-scrollbar-track {
  background: rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  background: linear-gradient(to bottom, #10B981, #F59E0B);
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: linear-gradient(to bottom, #059669, #D97706);
}
</style>