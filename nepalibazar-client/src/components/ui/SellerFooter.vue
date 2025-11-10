<template>
  <footer class="bg-black text-white pt-12 pb-6 mt-16">
    <div class="max-w-7xl mx-auto px-6">
      <!-- Main Footer Content -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mb-10">
        
        <!-- Brand Column -->
        <div class="lg:col-span-1">
          <div class="flex items-center space-x-3 mb-6">
            <div class="w-10 h-10 bg-gray-800 rounded-lg flex items-center justify-center border border-gray-700">
              <span class="text-white font-bold text-lg">S</span>
            </div>
            <h2 class="text-2xl font-bold text-white">
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
            <div class="absolute bottom-0 left-0 w-12 h-0.5 bg-gray-600"></div>
          </h3>
          <ul class="space-y-3">
            <li>
              <router-link to="/sellerdashboard/products" class="text-gray-300 hover:text-white transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-gray-500 rounded-full"></span>
                <span>My Products</span>
              </router-link>
            </li>
            <li>
              <router-link to="/sellerdashboard/add-product" class="text-gray-300 hover:text-white transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-gray-500 rounded-full"></span>
                <span>Add Product</span>
              </router-link>
            </li>
            <li>
              <router-link to="/sellerdashboard/analytics" class="text-gray-300 hover:text-white transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-gray-500 rounded-full"></span>
                <span>Analytics</span>
              </router-link>
            </li>
             <li>
              
            </li>
          </ul>
        </div>

        <!-- Support -->
        <div>
          <h3 class="text-lg font-semibold text-white mb-6 relative">
            Support
            <div class="absolute bottom-0 left-0 w-12 h-0.5 bg-gray-600"></div>
          </h3>
          <div class="space-y-4">
            <div class="flex items-start space-x-3">
              <div class="w-10 h-10 bg-gray-800 rounded-lg flex items-center justify-center flex-shrink-0 border border-gray-700">
                <svg class="w-5 h-5 text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                </svg>
              </div>
              <div>
                <p class="text-gray-300 text-sm">Seller Support</p>
                <p class="text-white font-medium">sagardada2535@gmail.com</p>
              </div>
            </div>
            
            <div class="flex items-start space-x-3">
              <div class="w-10 h-10 bg-gray-800 rounded-lg flex items-center justify-center flex-shrink-0 border border-gray-700">
                <svg class="w-5 h-5 text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
                </svg>
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
      <div class="pt-6 border-t border-gray-700">
        <div class="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">
          <div class="text-gray-400 text-sm">
            © {{ new Date().getFullYear() }} NepaliBazar Seller Center. All rights reserved.
          </div>
          <div class="flex items-center space-x-2 text-gray-400 text-sm">
            <span class="w-2 h-2 bg-gray-500 rounded-full"></span>
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
</style>