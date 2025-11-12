<template>
  <nav class="flex flex-row h-25 items-center border-b border-gray-200 bg-white shadow-sm px-8 ">
    <!-- Logo -->
    <div class="mr-16 relative">
      <router-link to="/" class="group">
        <img 
          src="../../assets/logo.PNG" 
          alt="company logo" 
          width="200" 
          class="transform  transition-transform duration-300"
        >
        <!-- Black line under logo -->
        <!-- <div class="absolute bottom-0 left-0 w-full h-1 bg-gray-900 rounded-full mt-1"></div> -->
      </router-link>
    </div> 
    
    <!-- Navigation Items -->
    <div class="gap-10 flex flex-row mr-auto">
      <div 
        class="relative group" 
        v-for="(items, index) in navitems" 
        :key="index"
      >
        <router-link 
          :to="items.link" 
          class="text-gray-700 hover:text-gray-900 text-black text-lg transition-all duration-300 py-2 px-1"
          :class="{ 'text-gray-900 font-semibold': $route.path === items.link }"
        >
          {{ items.name }}
          <!-- Hover line -->
          <div class="absolute bottom-0 left-0 w-0 h-0.5 bg-black group-hover:w-full transition-all duration-300 rounded-full"></div>
          <!-- Active line -->
          <div 
            v-if="$route.path === items.link"
            class="absolute bottom-0 left-0 w-full h-0.5 bg-black rounded-full"
          ></div>
        </router-link>   
      </div>
    </div>

    <!-- Icons Section -->
    <div class="flex items-center space-x-4">
      <!-- Dark/Light Mode Toggle -->
     

      <!-- Wishlist Icon -->
      <router-link 
        to="/wishlist" 
        class="p-3 hover:bg-gray-100 rounded-xl transition-all duration-300 group relative"
      >
        <Heart class="w-7 h-7 text-black group-hover:text-gray-900 transition-colors duration-300" />
        <div 
          v-if="wishlistItemCount > 0"
          class="absolute -top-1 -right-1 w-3 h-3 bg-gray-900 rounded-full"
        ></div>
      </router-link>
      
      <!-- Cart Icon -->
      <router-link 
        to="/mycart" 
        class="p-3 hover:bg-gray-100 rounded-xl transition-all duration-300 group relative"
      >
        <ShoppingBagIcon class="w-7 h-7 text-black group-hover:text-gray-900 transition-colors duration-300" />
        <!-- Cart Item Count Badge -->
        <span 
          v-if="cartItemCount > 0"
          class="absolute -top-2 -right-2 bg-black text-white text-sm rounded-full w-6 h-6 flex items-center justify-center font-bold shadow-lg"
        >
          {{ cartItemCount }}
        </span>
      </router-link>

      <!-- User Account -->
      <router-link 
        to="/profile" 
        class="p-3 hover:bg-gray-100 rounded-xl transition-all duration-300"
      >
        <svg class="w-6 h-6 text-black" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"/>
        </svg>
      </router-link>
    </div>
  </nav>
</template>

<script>
import { Heart, ShoppingBagIcon } from 'lucide-vue-next';

export default {
  name: "Navbar",
  components: { Heart, ShoppingBagIcon },
  data() {
    return {
      navitems: [
        { name: 'Home', link: '/' },
        { name: 'About Us', link: '/aboutus' },
        { name: 'Contact Us', link: '/contactus' },
        { name: 'Sign Up', link: '/loginuser' },
        { name: 'Become a seller', link: '/sellerlogin' }
      ],
      cartItemCount: 0,
      wishlistItemCount: 0,
      isDarkMode: false
    }
  },
  mounted() {
    this.updateCartCount();
    this.updateWishlistCount();
    this.initializeDarkMode();
  },
  methods: {
    updateCartCount() {
      const cartItems = JSON.parse(localStorage.getItem('cartItems') || '[]');
      this.cartItemCount = cartItems.reduce((total, item) => total + item.quantity, 0);
    },

    updateWishlistCount() {
      const wishlistItems = JSON.parse(localStorage.getItem('wishlistItems') || '[]');
      this.wishlistItemCount = wishlistItems.length;
    },

    initializeDarkMode() {
      // Check if dark mode is enabled in localStorage or system preference
      const savedMode = localStorage.getItem('darkMode');
      const systemPrefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
      
      if (savedMode !== null) {
        this.isDarkMode = savedMode === 'true';
      } else {
        this.isDarkMode = systemPrefersDark;
      }
      
      this.applyDarkMode();
    },

    toggleDarkMode() {
      this.isDarkMode = !this.isDarkMode;
      this.applyDarkMode();
      localStorage.setItem('darkMode', this.isDarkMode.toString());
    },

    applyDarkMode() {
      if (this.isDarkMode) {
        document.documentElement.classList.add('dark');
      } else {
        document.documentElement.classList.remove('dark');
      }
    }
  }
}
</script>

<style scoped>
/* Remove the active link underline from CSS since we're handling it differently */
.router-link-active::after {
  display: none;
}

/* Smooth transitions for all interactive elements */
nav * {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Logo hover effect */
/* .group:hover img {
  filter: brightness(0.9);
} */
</style>