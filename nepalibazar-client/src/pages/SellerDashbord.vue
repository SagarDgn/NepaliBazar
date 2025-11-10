<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900 transition-colors duration-300">
    <!-- Custom Notification -->
    <CustomNotification 
      :show="showNotification" 
      :message="notificationMessage" 
      :type="notificationType"
      @hide="showNotification = false"
    />
    
    <!-- Header -->
    <header class="bg-white dark:bg-gray-800 border-b border-gray-200 dark:border-gray-700 shadow-sm sticky top-0 z-40">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center py-4">
          <!-- Logo & Brand -->
          <div class="flex items-center space-x-4">
            <router-link to="/" class="flex items-center space-x-3 group">
              <div class="relative">
                <div class="w-12 h-12 bg-gray-900 dark:bg-white rounded-xl flex items-center justify-center shadow-lg">
                  <span class="text-white dark:text-gray-900 font-bold text-lg">NB</span>
                </div>
              </div>
              <div>
                <h1 class="text-2xl font-bold text-gray-900 dark:text-white">
                  Seller Center
                </h1>
                <p class="text-gray-600 dark:text-gray-400 text-sm">Manage your business</p>
              </div>
            </router-link>
          </div>

          <!-- Header Actions -->
          <div class="flex items-center space-x-4">
            <!-- Dark Mode Toggle -->
            <button 
              @click="toggleDarkMode"
              class="p-3 rounded-lg bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-600 transition-all duration-300 group border border-gray-200 dark:border-gray-600"
              :title="isDarkMode ? 'Switch to Light Mode' : 'Switch to Dark Mode'"
            >
              <svg v-if="isDarkMode" class="w-5 h-5 transform group-hover:rotate-180 transition-transform duration-500" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z" clip-rule="evenodd"></path>
              </svg>
              <svg v-else class="w-5 h-5 transform group-hover:rotate-180 transition-transform duration-500" fill="currentColor" viewBox="0 0 20 20">
                <path d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"></path>
              </svg>
            </button>

            <!-- User Menu -->
            <div class="relative group">
              <button class="flex items-center space-x-3 p-2 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors duration-200">
                <div class="w-8 h-8 bg-gray-300 dark:bg-gray-600 rounded-full flex items-center justify-center">
                  <span class="text-sm font-semibold text-gray-700 dark:text-gray-300">S</span>
                </div>
                <div class="text-left hidden md:block">
                  <p class="text-sm font-medium text-gray-900 dark:text-white">Seller Account</p>
                  <p class="text-xs text-gray-500 dark:text-gray-400">Business</p>
                </div>
              </button>
            </div>

            <!-- Logout Button -->
            <button 
              @click="logout"
              :disabled="loading"
              :class="[
                'px-4 py-2 font-semibold rounded-lg transition-all duration-300 flex items-center space-x-2 border',
                loading 
                  ? 'bg-gray-300 cursor-not-allowed text-gray-500 border-gray-300' 
                  : 'bg-white dark:bg-gray-800 text-gray-900 dark:text-white border-gray-300 dark:border-gray-600 hover:bg-gray-50 dark:hover:bg-gray-700 hover:border-gray-400 dark:hover:border-gray-500'
              ]"
            >
              <svg v-if="!loading" class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"></path>
              </svg>
              <svg v-else class="w-4 h-4 animate-spin" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 2v4m0 12v4m8-10h-4M6 12H2m15.364-7.364l-2.828 2.828M7.464 17.536l-2.828 2.828m12.728 0l-2.828-2.828M7.464 6.464L4.636 3.636"></path>
              </svg>
              <span class="hidden sm:inline">{{ loading ? 'Logging out...' : 'Logout' }}</span>
            </button>
          </div>
        </div>
      </div>
    </header>

    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="flex flex-col lg:flex-row gap-8">
        <!-- Sidebar -->
        <aside class="lg:w-80 flex-shrink-0">
          <div class="bg-white dark:bg-gray-800 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700 p-6 sticky top-32">
            <!-- Navigation -->
            <nav class="space-y-2">
              <router-link 
                v-for="item in navigation"
                :key="item.name"
                :to="item.to"
                :class="[
                  'group flex items-center space-x-3 px-4 py-3 rounded-lg transition-all duration-200 border',
                  $route.name === item.name 
                    ? 'bg-gray-900 text-white border-gray-900 shadow-sm' 
                    : 'text-gray-700 dark:text-gray-300 border-transparent hover:bg-gray-50 dark:hover:bg-gray-700 hover:border-gray-300 dark:hover:border-gray-600'
                ]"
              >
                <div :class="[
                  'p-2 rounded-lg transition-colors duration-200',
                  $route.name === item.name 
                    ? 'bg-white/20' 
                    : 'bg-gray-100 dark:bg-gray-700 group-hover:bg-gray-200 dark:group-hover:bg-gray-600'
                ]">
                  <component :is="item.icon" class="w-4 h-4" />
                </div>
                <span class="font-medium text-sm">{{ item.name }}</span>
                <div v-if="item.badge" class="ml-auto">
                  <span class="px-2 py-1 text-xs rounded-full bg-red-500 text-white font-medium">
                    {{ item.badge }}
                  </span>
                </div>
              </router-link>
            </nav>

            <!-- Quick Stats -->
            <div class="mt-8 pt-6 border-t border-gray-200 dark:border-gray-700">
              <h3 class="text-xs font-semibold text-gray-500 dark:text-gray-400 uppercase tracking-wider mb-4">Business Overview</h3>
              <div class="space-y-3">
                <div class="flex justify-between items-center p-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600">
                  <div class="flex items-center space-x-2">
                    <div class="w-2 h-2 bg-gray-400 rounded-full"></div>
                    <span class="text-sm text-gray-600 dark:text-gray-400">Total Products</span>
                  </div>
                  <span class="font-bold text-gray-900 dark:text-white">{{ quickStats.totalProducts || 0 }}</span>
                </div>
                <div class="flex justify-between items-center p-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600">
                  <div class="flex items-center space-x-2">
                    <div class="w-2 h-2 bg-yellow-500 rounded-full"></div>
                    <span class="text-sm text-gray-600 dark:text-gray-400">Low Stock</span>
                  </div>
                  <span class="font-bold text-yellow-600 dark:text-yellow-400">{{ quickStats.lowStock || 0 }}</span>
                </div>
                <div class="flex justify-between items-center p-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600">
                  <div class="flex items-center space-x-2">
                    <div class="w-2 h-2 bg-blue-500 rounded-full"></div>
                    <span class="text-sm text-gray-600 dark:text-gray-400">Active Orders</span>
                  </div>
                  <span class="font-bold text-blue-600 dark:text-blue-400">{{ quickStats.activeOrders || 0 }}</span>
                </div>
              </div>
            </div>

            <!-- Performance Metrics -->
            <div class="mt-6 p-4 bg-gray-50 dark:bg-gray-700 rounded-lg border border-gray-200 dark:border-gray-600">
              <h4 class="text-sm font-semibold text-gray-900 dark:text-white mb-2">Performance</h4>
              <div class="space-y-2">
                <div class="flex justify-between text-sm">
                  <span class="text-gray-600 dark:text-gray-400">Store Rating</span>
                  <span class="font-semibold text-gray-900 dark:text-white">4.8/5</span>
                </div>
                <div class="flex justify-between text-sm">
                  <span class="text-gray-600 dark:text-gray-400">Response Time</span>
                  <span class="font-semibold text-green-600 dark:text-green-400">2h</span>
                </div>
              </div>
            </div>
          </div>
        </aside>

        <!-- Main Content -->
        <main class="flex-1 min-w-0">
          <!-- Breadcrumb -->
          <div class="mb-6">
            <nav class="flex items-center space-x-2 text-sm text-gray-600 dark:text-gray-400">
              <router-link to="/sellerdashboard" class="hover:text-gray-900 dark:hover:text-white transition-colors">
                Dashboard
              </router-link>
              <span class="text-gray-400">/</span>
              <span class="text-gray-900 dark:text-white font-medium capitalize">
                {{ currentPageTitle }}
              </span>
            </nav>
          </div>

          <!-- Page Content -->
          <div class="bg-white dark:bg-gray-800 rounded-xl shadow-sm border border-gray-200 dark:border-gray-700 overflow-hidden">
            <router-view></router-view>
          </div>

          <!-- Quick Actions -->
          <div class="mt-6 grid grid-cols-1 md:grid-cols-3 gap-4">
            <div class="bg-white dark:bg-gray-800 p-4 rounded-lg border border-gray-200 dark:border-gray-700 shadow-sm">
              <div class="flex items-center space-x-3">
                <div class="w-10 h-10 bg-gray-100 dark:bg-gray-700 rounded-lg flex items-center justify-center">
                  <svg class="w-5 h-5 text-gray-600 dark:text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="text-sm font-semibold text-gray-900 dark:text-white">Verified Seller</h3>
                  <p class="text-xs text-gray-500 dark:text-gray-400">Account status</p>
                </div>
              </div>
            </div>
            
            <div class="bg-white dark:bg-gray-800 p-4 rounded-lg border border-gray-200 dark:border-gray-700 shadow-sm">
              <div class="flex items-center space-x-3">
                <div class="w-10 h-10 bg-gray-100 dark:bg-gray-700 rounded-lg flex items-center justify-center">
                  <svg class="w-5 h-5 text-gray-600 dark:text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="text-sm font-semibold text-gray-900 dark:text-white">Revenue</h3>
                  <p class="text-xs text-gray-500 dark:text-gray-400">This month</p>
                </div>
              </div>
            </div>
            
            <div class="bg-white dark:bg-gray-800 p-4 rounded-lg border border-gray-200 dark:border-gray-700 shadow-sm">
              <div class="flex items-center space-x-3">
                <div class="w-10 h-10 bg-gray-100 dark:bg-gray-700 rounded-lg flex items-center justify-center">
                  <svg class="w-5 h-5 text-gray-600 dark:text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="text-sm font-semibold text-gray-900 dark:text-white">Customers</h3>
                  <p class="text-xs text-gray-500 dark:text-gray-400">Total served</p>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import CustomNotification from '../components/ui/CustomNotification.vue'
import sellerAuthService from '../services/SellerAuthService'
import { ShoppingBagIcon, PlusCircleIcon, ChartBarIcon, PackageIcon, SettingsIcon, ChartBar } from 'lucide-vue-next';

export default {
  name: 'SellerDashboard',
  components: { CustomNotification },
  data() {
    return {
      isDarkMode: false,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success',
      loading: false,
      quickStats: {
        totalProducts: 0,
        lowStock: 0,
        activeOrders: 0
      },
      navigation: [
        {
          name: 'Dashboard',
          to: '/sellerdashboard',
          icon: ChartBarIcon,
          badge: null
        },
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
          name: 'Orders',
          to: '/sellerdashboard/orders',
          icon: PackageIcon,
          badge: null
        },
        {
          name: 'Settings',
          to: '/sellerdashboard/settings',
          icon: SettingsIcon,
          badge: null
        },
        {name: 'Analytics',
          to: '/sellerdashboard/analytics',
          icon: ChartBar,
          badge: null

        }
      ]
    }
  },
  computed: {
    currentPageTitle() {
      const routeName = this.$route.name;
      if (!routeName) return 'Dashboard';
      
      // Convert route name to title case
      return routeName.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase());
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
      // Simulate API call
      setTimeout(() => {
        this.quickStats = {
          totalProducts: 24,
          lowStock: 3,
          activeOrders: 8
        }
      }, 1000)
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
        const result = await sellerAuthService.logout();
        
        if (result.code === 200) {
          this.showToast(result.message || 'Logged out successfully!', 'success');
        } else {
          this.showToast('Logged out successfully!', 'success');
        }
        
        setTimeout(() => {
          this.$router.push('/sellerlogin');
        }, 1000);
        
      } catch (error) {
        console.error('Unexpected logout error:', error);
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
/* Smooth transitions for all elements */
* {
  transition: all 0.2s ease-in-out;
}

/* Custom scrollbar for dark mode compatibility */
::-webkit-scrollbar {
  width: 6px;
}

::-webkit-scrollbar-track {
  background: rgba(0, 0, 0, 0.05);
  border-radius: 3px;
}

::-webkit-scrollbar-thumb {
  background: rgba(0, 0, 0, 0.2);
  border-radius: 3px;
}

::-webkit-scrollbar-thumb:hover {
  background: rgba(0, 0, 0, 0.3);
}

.dark ::-webkit-scrollbar-track {
  background: rgba(255, 255, 255, 0.05);
}

.dark ::-webkit-scrollbar-thumb {
  background: rgba(255, 255, 255, 0.2);
}

.dark ::-webkit-scrollbar-thumb:hover {
  background: rgba(255, 255, 255, 0.3);
}
</style>