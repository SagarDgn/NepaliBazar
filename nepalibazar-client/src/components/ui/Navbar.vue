<template>
  <nav class="flex flex-row h-25 items-center border-b border-gray-200 bg-white shadow-sm px-8 ">

    <!-- Logo -->
    <div class="mr-16">
      <router-link to="/">
        <img 
          src="../../assets/logo.PNG" 
          alt="company logo" 
          width="180"
          class="cursor-pointer"
        >
      </router-link>
    </div>

    <!-- Navigation Items -->
    <div class="gap-10 flex flex-row mr-auto">
      <div 
        class="relative group" 
        v-for="(item, index) in navitems" 
        :key="index"
      >
        <router-link
          :to="item.link"
          class="text-gray-700 hover:text-black text-lg transition-all duration-300 py-2 px-1"
          :class="{ 'text-black font-semibold': $route.path === item.link }"
        >
          {{ item.name }}

          <div class="absolute bottom-0 left-0 w-0 h-0.5 bg-black group-hover:w-full transition-all duration-300"></div>

          <div 
            v-if="$route.path === item.link"
            class="absolute bottom-0 left-0 w-full h-0.5 bg-black"
          ></div>
        </router-link>
      </div>
    </div>

    <!-- Icons + Account -->
    <div class="flex items-center space-x-5">

      <!-- Wishlist -->
      <router-link 
        to="/wishlist"
        class="relative p-3 hover:bg-gray-100 rounded-xl transition-all duration-300"
      >
        <Heart class="w-7 h-7 text-black" />
        <div 
          v-if="wishlistItemCount > 0"
          class="absolute -top-1 -right-1 w-3 h-3 bg-black rounded-full"
        ></div>
      </router-link>

      <!-- Cart -->
      <router-link 
        to="/mycart"
        class="relative p-3 hover:bg-gray-100 rounded-xl transition-all duration-300"
      >
        <ShoppingBagIcon class="w-7 h-7 text-black" />
        <span 
          v-if="cartItemCount > 0"
          class="absolute -top-2 -right-2 bg-black text-white text-xs rounded-full w-5 h-5 flex items-center justify-center font-bold"
        >
          {{ cartItemCount }}
        </span>
      </router-link>

      <!-- ACCOUNT SECTION -->
      <div class="relative">
        
        <!-- NOT LOGGED IN -->
        <router-link 
          v-if="!isLoggedIn"
          to="/loginuser"
          class="p-3 hover:bg-gray-100 rounded-xl transition-all duration-300 text-lg "
        >
          Sign Up
        </router-link>

        <!-- LOGGED IN -->
        <div v-else class="relative">
          <div 
            class="flex items-center gap-2 p-3 cursor-pointer hover:bg-gray-100 rounded-xl"
            @click="showDropdown = !showDropdown"
          >
            <!-- FIRST LETTER AVATAR -->
            <div 
              class="w-9 h-9 rounded-full bg-black text-white flex items-center justify-center font-bold text-lg"
            >
              {{ firstLetter }}
            </div>
            <!-- Username -->
            <span class="font-semibold">{{ userName }}</span>
          </div>

          <!-- Dropdown -->
          <div 
            v-if="showDropdown"
            class="absolute right-0 mt-2 w-40 bg-white shadow-lg rounded-lg transition-all duration-300 p-2 z-50"
          >
            <router-link to="/profile" class="block py-2 hover:text-black">Profile</router-link>
            <router-link to="/settings" class="block py-2 hover:text-black">Settings</router-link>
            <router-link to="/orders" class="block py-2 hover:text-black">My orders</router-link>
            <button @click="logout" class="w-full text-left py-2 text-red-600 hover:text-red-800">Logout</button>
          </div>
        </div>

      </div>

    </div>
  </nav>
</template>

<script>
import { Heart, ShoppingBagIcon } from "lucide-vue-next";
import auth from "../../services/UserAuthService"; // adjust path

export default {
  name: "Navbar",
  components: { Heart, ShoppingBagIcon },

  data() {
    return {
      navitems: [
        { name: "Home", link: "/" },
        { name: "About Us", link: "/aboutus" },
        { name: "Contact Us", link: "/contactus" },
        { name: "Login as seller", link: "/sellerlogin" } // always visible
      ],

      cartItemCount: 0,
      wishlistItemCount: 0,
      showDropdown: false,
    };
  },

  computed: {
    isLoggedIn() {
      return auth.isAuthenticated();
    },

    userName() {
      return localStorage.getItem("buyer_name");
    },

    firstLetter() {
      return this.userName ? this.userName.charAt(0).toUpperCase() : "";
    }
  },

  mounted() {
    this.updateCartCount();
    this.updateWishlistCount();
  },

  methods: {
    updateCartCount() {
      const cartItems = JSON.parse(localStorage.getItem("cartItems") || "[]");
      this.cartItemCount = cartItems.reduce((t, i) => t + i.quantity, 0);
    },

    updateWishlistCount() {
      const wishlistItems = JSON.parse(localStorage.getItem("wishlistItems") || "[]");
      this.wishlistItemCount = wishlistItems.length;
    },

    logout() {
      auth.logout();
      localStorage.removeItem("buyer_name");
      this.showDropdown = false;
      this.$router.push("/loginuser");
    }
  }
};
</script>

<style scoped>
nav * {
  transition: all 0.3s ease;
}
</style>
