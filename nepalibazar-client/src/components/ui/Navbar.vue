<template>
  <nav class="flex flex-row h-25 items-center border-b border-gray-500">
    <!-- navbar image -->
    <div class="mx-15 mt-4">
      <router-link to="/">
        <img src="../../assets/logo.PNG" alt="company logo" width="200">
      </router-link>
    </div> 
    
    <!-- navbar items -->
    <div class="gap-5 flex flex-row mx-10 mt-4">
      <div 
        class="hover hover:underline font-normal" 
        v-for="(items, index) in navitems" 
        :key="index"
      >
        <router-link :to="items.link">{{ items.name }}</router-link>   
      </div>
    </div>

    <!-- search bar -->
    <div class="mx-20 mt-4 flex items-center space-x-4">
      <!-- Wishlist Icon -->
      <router-link to="/wishlist" class="p-2 hover:bg-gray-100 rounded-full transition-colors">
        <Heart class="w-6 h-6 text-gray-700" />
      </router-link>
      
      <!-- Cart Icon -->
      <router-link to="/mycart" class="p-2 hover:bg-gray-100 rounded-full transition-colors relative">
        <ShoppingBagIcon class="w-6 h-6 text-gray-700" />
        <!-- Cart Item Count Badge -->
        <span 
          v-if="cartItemCount > 0"
          class="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-5 h-5 flex items-center justify-center font-semibold"
        >
          {{ cartItemCount }}
        </span>
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
        { name: 'Sign Up', link: '/usersignup' },
        { name: 'Become a seller', link: '/sellersignup' }
      ],
      cartItemCount: 0
    }
  },
  mounted() {
    // You can update this from your cart service
    this.updateCartCount();
  },
  methods: {
    updateCartCount() {
      // Example: Fetch cart count from your cart service or Vuex store
      // This is a placeholder - implement based on your cart management
      const cartItems = JSON.parse(localStorage.getItem('cartItems') || '[]');
      this.cartItemCount = cartItems.reduce((total, item) => total + item.quantity, 0);
      
      // If you have a cart service, you can use:
      // CartService.getCartItems().then(result => {
      //   if (result.success) {
      //     this.cartItemCount = result.data.totalItems || 0;
      //   }
      // });
    }
  }
}
</script>

<style scoped>
.hover:hover {
  text-decoration: underline;
}

/* Smooth transitions for icons */
.router-link-active {
  transition: all 0.2s ease-in-out;
}

/* Pulse animation for cart badge when count updates */
@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.1); }
  100% { transform: scale(1); }
}

.bg-red-500 {
  animation: pulse 0.5s ease-in-out;
}
</style>