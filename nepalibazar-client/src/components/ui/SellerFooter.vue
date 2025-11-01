<template>
  <footer class="bg-black text-white pt-12 pb-6 mt-16">
    <div class="max-w-7xl mx-auto px-6">
      <!-- Main Footer Content -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mb-10">
        
        <!-- Brand Column -->
        <div class="lg:col-span-1">
          <div class="flex items-center space-x-3 mb-6">
            <div class="w-10 h-10 bg-gradient-to-r from-green-500 to-orange-500 rounded-lg flex items-center justify-center">
              <span class="text-white font-bold text-lg">S</span>
            </div>
            <h2 class="text-2xl font-bold bg-gradient-to-r from-green-400 to-orange-400 bg-clip-text text-transparent">
              Seller Center
            </h2>
          </div>
          <p class="text-gray-300 text-sm leading-relaxed mb-6">
            Your professional dashboard for managing products and growing your business on NepaliBazar.
          </p>
        </div>

        <!-- Seller Links -->
        <div>
          <h3 class="text-lg font-semibold text-white mb-6 relative">
            Seller Links
            <div class="absolute bottom-0 left-0 w-12 h-0.5 bg-gradient-to-r from-green-500 to-orange-500"></div>
          </h3>
          <ul class="space-y-3">
            <li>
              <router-link to="/sellerdashboard/products" class="text-gray-300 hover:text-green-400 transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-green-500 rounded-full"></span>
                <span>My Products</span>
              </router-link>
            </li>
            <li>
              <router-link to="/sellerdashboard/add-product" class="text-gray-300 hover:text-green-400 transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-green-500 rounded-full"></span>
                <span>Add Product</span>
              </router-link>
            </li>
            <li>
              <router-link to="/sellerdashboard/analytics" class="text-gray-300 hover:text-green-400 transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-green-500 rounded-full"></span>
                <span>Analytics</span>
              </router-link>
            </li>
             <li>
              <router-link to="/contactus" class="text-gray-300 hover:text-green-400 transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-green-500 rounded-full"></span>
                <span>Contact us</span>
              </router-link>
            </li>
          </ul>
        </div>

        <!-- Support -->
        <div>
          <h3 class="text-lg font-semibold text-white mb-6 relative">
            Support
            <div class="absolute bottom-0 left-0 w-12 h-0.5 bg-gradient-to-r from-green-500 to-orange-500"></div>
          </h3>
          <div class="space-y-4">
            <div class="flex items-start space-x-3">
              <div class="w-10 h-10 bg-gradient-to-r from-green-500 to-orange-500 rounded-lg flex items-center justify-center flex-shrink-0">
                <span class="text-white text-lg">✉️</span>
              </div>
              <div>
                <p class="text-gray-300 text-sm">Seller Support</p>
                <p class="text-white font-medium">sagardada2535@gmail.com</p>
              </div>
            </div>
            
            <div class="flex items-start space-x-3">
              <div class="w-10 h-10 bg-gradient-to-r from-green-500 to-orange-500 rounded-lg flex items-center justify-center flex-shrink-0">
                <span class="text-white text-lg">🕒</span>
              </div>
              <div>
                <p class="text-gray-300 text-sm">Support Hours</p>
                <p class="text-white font-medium">Sun-Fri: 9AM - 6PM</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Bottom Bar -->
      <div class="pt-6 border-t border-gray-800">
        <div class="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">
          <div class="text-gray-400 text-sm">
            © {{ new Date().getFullYear() }} NepaliBazar Seller Center. All rights reserved.
          </div>
          <div class="flex items-center space-x-2 text-gray-400 text-sm">
            <span class="w-2 h-2 bg-green-500 rounded-full animate-pulse"></span>
            <span>Seller Account Active</span>
            <span class="text-lg">🇳🇵</span>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "SellerFooter",
};
</script>

<style scoped>
footer {
  font-family: 'Inter', sans-serif;
}

/* Smooth transitions for all interactive elements */
a, button {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Custom gradient animation for brand logo */
@keyframes gradientShift {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

.bg-gradient-to-r.from-green-500.to-orange-500 {
  background-size: 200% 200%;
  animation: gradientShift 3s ease infinite;
}
</style>