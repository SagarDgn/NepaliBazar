<template>
  <footer class="bg-black text-white pt-16 pb-8 mt-20">
    <div class="max-w-7xl mx-auto px-6">
      <!-- Main Footer Content -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mb-12">
        
        <!-- Brand Column -->
        <div class="lg:col-span-1">
          <div class="flex items-center space-x-3 mb-6">
            <div class="w-10 h-10 bg-gradient-to-r from-green-500 to-orange-500 rounded-lg flex items-center justify-center">
              <span class="text-white font-bold text-lg">N</span>
            </div>
            <h2 class="text-2xl font-bold bg-gradient-to-r from-green-400 to-orange-400 bg-clip-text text-transparent">
              NepaliBazar
            </h2>
          </div>
          <p class="text-gray-300 text-sm leading-relaxed mb-6">
            Your trusted online marketplace connecting authentic Nepali products with buyers worldwide. 
            Supporting local artisans and preserving cultural heritage.
          </p>
        </div>

        <!-- Quick Links -->
        <div>
          <h3 class="text-lg font-semibold text-white mb-6 relative">
            Quick Links
            <div class="absolute bottom-0 left-0 w-12 h-0.5 bg-gradient-to-r from-green-500 to-orange-500"></div>
          </h3>
          <ul class="space-y-3">
            <li>
              <router-link to="/products" class="text-gray-300 hover:text-green-400 transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-green-500 rounded-full"></span>
                <span>All Products</span>
              </router-link>
            </li>
            <li>
              <router-link to="/aboutus" class="text-gray-300 hover:text-green-400 transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-green-500 rounded-full"></span>
                <span>About Us</span>
              </router-link>
            </li>
            <li>
              <router-link to="/contactus" class="text-gray-300 hover:text-green-400 transition-colors duration-200 flex items-center space-x-2">
                <span class="w-1.5 h-1.5 bg-green-500 rounded-full"></span>
                <span>Contact</span>
              </router-link>
            </li>
          </ul>
        </div>

        <!-- Contact Info -->
        <div>
          <h3 class="text-lg font-semibold text-white mb-6 relative">
            Contact Info
            <div class="absolute bottom-0 left-0 w-12 h-0.5 bg-gradient-to-r from-green-500 to-orange-500"></div>
          </h3>
          <div class="space-y-4">
            <div class="flex items-start space-x-3">
              <div class="w-10 h-10 bg-gradient-to-r rounded-lg flex items-center justify-center flex-shrink-0">
                <span class="text-white text-lg">📞</span>
              </div>
              <div>
                <p class="text-gray-300 text-sm">Call Us</p>
                <p class="text-white font-medium">+977 9709518369</p>
              </div>
            </div>
            
            <div class="flex items-start space-x-3">
              <div class="w-10 h-10 bg-gradient-to-r rounded-lg flex items-center justify-center flex-shrink-0">
                <span class="text-white text-lg">✉️</span>
              </div>
              <div>
                <p class="text-gray-300 text-sm">Email Us</p>
                <p class="text-white font-medium">sagardada2535@gmail.com</p>
              </div>
            </div>
            
            <div class="flex items-start space-x-3">
              <div class="w-10 h-10 bg-gradient-to-r rounded-lg flex items-center justify-center flex-shrink-0">
                <span class="text-white text-lg">🕒</span>
              </div>
              <div>
                <p class="text-gray-300 text-sm">Business Hours</p>
                <p class="text-white font-medium">Sun-Fri: 9AM - 6PM</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Bottom Bar -->
      <div class="pt-8 border-t border-gray-800">
        <div class="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">
          <div class="text-gray-400 text-sm">
            © {{ new Date().getFullYear() }} NepaliBazar. All rights reserved.
          </div>
          <!-- <div class="flex space-x-6 text-sm text-gray-400">
            <a href="#" class="hover:text-green-400 transition-colors duration-200">Privacy Policy</a>
            <a href="#" class="hover:text-green-400 transition-colors duration-200">Terms of Service</a>
            <a href="#" class="hover:text-green-400 transition-colors duration-200">Cookie Policy</a>
          </div> -->
          <div class="flex items-center space-x-2 text-gray-400 text-sm">
            <span class="w-2 h-2 bg-green-500 rounded-full animate-pulse"></span>
            <span>Proudly Made in Nepal</span>
            <span class="text-lg">🇳🇵</span>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer",
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