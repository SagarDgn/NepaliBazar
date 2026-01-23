<template>
  <div class="min-h-screen bg-white">
    <!-- Header -->
    <header class="border-b border-gray-300">
      <div class="max-w-7xl mx-auto px-4 py-4">
        <div class="flex justify-between items-center">
          <div>
            <h1 class="text-xl font-bold text-black">Admin Dashboard</h1>
            <p class="text-sm text-gray-600">Welcome, {{ adminName }}</p>
          </div>
          <button
            @click="handleLogout"
            :disabled="loggingOut"
            class="px-4 py-2 bg-black text-white text-sm hover:bg-gray-800 rounded transition-colors flex items-center justify-center min-w-[85px] disabled:opacity-70 disabled:cursor-not-allowed"
          >
            <svg v-if="loggingOut" class="animate-spin h-4 w-4 mr-2" fill="none" viewBox="0 0 24 24">
              <circle cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4" class="opacity-25"></circle>
              <path fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z" class="opacity-75"></path>
            </svg>
            <span>{{ loggingOut ? 'Logging out...' : 'Logout' }}</span>
          </button>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <main class="max-w-7xl mx-auto py-8 px-4">
      <!-- Action Links -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- View Users Link -->
        <div
          @click="viewUsers"
          class="p-6 border border-gray-300 hover:border-black hover:bg-gray-50 text-left group cursor-pointer transition-all"
        >
          <div class="flex items-center">
            <div class="w-10 h-10 bg-black flex items-center justify-center mr-4">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197m13.5 0a4 4 0 11-8 0 4 4 0 018 0z"/>
              </svg>
            </div>
            <div>
              <h3 class="font-semibold text-black">View Users</h3>
              <p class="text-sm text-gray-600 mt-1">Manage all registered users</p>
            </div>
          </div>
        </div>

        <!-- View Sellers Link -->
        <div
          @click="viewSellers"
          class="p-6 border border-gray-300 hover:border-black hover:bg-gray-50 text-left group cursor-pointer transition-all"
        >
          <div class="flex items-center">
            <div class="w-10 h-10 bg-black flex items-center justify-center mr-4">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4"/>
              </svg>
            </div>
            <div>
              <h3 class="font-semibold text-black">View Sellers</h3>
              <p class="text-sm text-gray-600 mt-1">Manage seller accounts</p>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import AdminLogin from '../services/AdminLogin';

export default {
  name: "AdminDashboard",
  data() {
    return {
      adminName: '',
      loggingOut: false
    }
  },
  mounted() {
    // Direct check without calling service method
    const token = localStorage.getItem("admin_jwt");
    if (!token) {
      this.$router.push('/nepalibazar');
      return;
    }
    
    // Get admin name
    this.adminName = localStorage.getItem("admin_name") || 'Admin';
  },
  methods: {
    async handleLogout() {
      this.loggingOut = true;
      
      try {
        // Call logout service
        AdminLogin.logout();
        
        // Force clear localStorage to ensure logout
        localStorage.removeItem("admin_jwt");
        localStorage.removeItem("admin_role");
        localStorage.removeItem("admin_name");
        localStorage.removeItem("admin_permission");
        
        // Small delay for visual feedback
        await new Promise(resolve => setTimeout(resolve, 300));
        
        // Force redirect to login page
        window.location.href = '/nepalibazar';
        // OR if you want to use router:
        // this.$router.push('/nepalibazar');
        
      } catch (error) {
        console.error('Logout error:', error);
        // Even if error, force redirect
        window.location.href = '/nepalibazar';
      } finally {
        this.loggingOut = false;
      }
    },
    
    viewUsers() {
      this.$router.push('/admin/users');
    },
    
    viewSellers() {
      this.$router.push('/admin/sellers');
    }
  }
}
</script>

<style scoped>
/* Hover effect */
div:hover {
  background-color: #f9fafb;
  transition: all 0.2s ease;
}

/* Cursor pointer */
.cursor-pointer {
  cursor: pointer;
}

/* Animation for spinner */
@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

.animate-spin {
  animation: spin 1s linear infinite;
}

/* Smooth transitions */
.transition-all {
  transition: all 0.2s ease;
}

/* Button hover effect */
button:hover:not(:disabled) {
  transform: translateY(-1px);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>