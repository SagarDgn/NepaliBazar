<template>
  <div>
    <Hero/>
    <Navbar/>
    <div class="p-6">
      <h1 class="text-2xl font-bold mb-4 text-center text-red-400">My Wishlist</h1>

      <div v-if="loading" class="text-gray-500">Loading wishlist...</div>

      <div v-else>
        <div v-if="wishlistProducts.length === 0" class="text-gray-500 text-center py-8">
          Your wishlist is empty.
        </div>

        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 h-full">
          <ProductCard
            v-for="product in wishlistProducts"
            :key="product.id"
            :product="mapProduct(product)"
            @wishlist="handleToggleWishlist"
          />
        </div>
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import ProductCard from "../components/ProductCard.vue";
import Footer from "../components/ui/Footer.vue";
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";
import WishlistService from "../services/WishlistService";

export default {
  name: "WishlistPage",
  components: { ProductCard, Navbar, Hero, Footer },
  data() {
    return {
      wishlistProducts: [],
      loading: false,
    };
  },
  async mounted() {
    await this.fetchWishlist();
  },
methods: {
  async fetchWishlist() {
    this.loading = true;
    try {
      const products = await WishlistService.getWishlistProducts();
      this.wishlistProducts = products;
    } catch (error) {
      console.error("Error fetching wishlist:", error);
    } finally {
      this.loading = false;
    }
  },

  // async handleToggleWishlist({ id }) {
  //   try {
  //     const result = await WishlistService.toggleWishlist(id);
  //     if (result.success) {
  //       if (!result.isWishlisted) {
  //         this.wishlistProducts = this.wishlistProducts.filter(
  //           (p) => p.id !== id
  //         );
  //         console.log("Product removed from wishlist");
  //       } else {
  //         console.log("Product was added to wishlist - unexpected");
  //       }
  //     } else {
  //       console.error("Failed to toggle wishlist:", result.message);
  //     }
  //   } catch (error) {
  //     console.error("Error toggling wishlist:", error);
  //   }
  // },
  async handleToggleWishlist({ id, wishlisted }) {
  console.log(" [WishlistPage] Received wishlist event - product:", id, "new state:", wishlisted);
  
  // Just handle the UI update - NO API call here
  if (!wishlisted) {
    console.log(" [WishlistPage] Removing product from UI:", id);
    console.log(" [WishlistPage] Before removal:", this.wishlistProducts.map(p => p.id));
    
    this.wishlistProducts = this.wishlistProducts.filter((p) => p.id !== id);
    
    console.log("[WishlistPage] After removal:", this.wishlistProducts.map(p => p.id));
    console.log(" [WishlistPage] Product removed from UI");
  } else {
    console.log(" [WishlistPage] Product added to wishlist - unexpected in wishlist page");
  }
},

 
  mapProduct(product) {
    return {
      id: product.id,
      name: product.productName,
      imageUrl: product.image,
      price: product.price,
      sellerName: product.sellerName || "",
      wishlisted: true // Force true since we're in wishlist page
    };
  },
},

  
};
</script>

<style scoped>
/* Add any custom styles here */
</style>