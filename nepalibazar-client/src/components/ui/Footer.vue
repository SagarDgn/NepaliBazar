<template>
  <footer class="bg-black text-white pt-12 pb-8 border-t border-gray-800">
    <div class="max-w-7xl mx-auto px-6">
      <!-- Main Footer Content -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8 mb-12">
        
        <!-- Brand Column -->
        <div class="lg:col-span-1">
          <div class="flex items-center space-x-3 mb-6">
            <div class="w-10 h-10 bg-gray-800 rounded-lg flex items-center justify-center border border-gray-700">
              <span class="text-white font-bold text-lg">N</span>
            </div>
            <h2 class="text-xl font-bold text-white">
              NepaliBazar
            </h2>
          </div>
          <p class="text-gray-400 text-light leading-relaxed mb-6">
            Your trusted online marketplace connecting authentic Nepali products with buyers worldwide. 
            Supporting local artisans and preserving cultural heritage.
          </p>
        </div>

        <!-- Quick Links -->
        <div>
          <h3 class="text-base font-semibold text-white mb-6">
            Quick Links
          </h3>
          <ul class="space-y-3">
            <li>
              <router-link to="/products" class="text-gray-400 hover:text-white transition-colors duration-200 flex items-center space-x-2 text-light">
                <span class="w-1.5 h-1.5 bg-gray-600 rounded-full"></span>
                <span>All Products</span>
              </router-link>
            </li>
            <li>
              <router-link to="/aboutus" class="text-gray-400 hover:text-white transition-colors duration-200 flex items-center space-x-2 text-light">
                <span class="w-1.5 h-1.5 bg-gray-600 rounded-full"></span>
                <span>About Us</span>
              </router-link>
            </li>
            <li>
              <router-link to="/contactus" class="text-gray-400 hover:text-white transition-colors duration-200 flex items-center space-x-2 text-light">
                <span class="w-1.5 h-1.5 bg-gray-600 rounded-full"></span>
                <span>Contact</span>
              </router-link>
            </li>
          </ul>
        </div>

        <!-- Customer Service -->
        <div>
          <h3 class="text-base font-semibold text-white mb-6">
            Customer Service
          </h3>
          <ul class="space-y-3">
            <li>
              <a href="#" class="text-gray-400 hover:text-white transition-colors duration-200 flex items-center space-x-2 text-light">
                <span class="w-1.5 h-1.5 bg-gray-600 rounded-full"></span>
                <span>Shipping Info</span>
              </a>
            </li>
            <li>
              <a href="#" class="text-gray-400 hover:text-white transition-colors duration-200 flex items-center space-x-2 text-light">
                <span class="w-1.5 h-1.5 bg-gray-600 rounded-full"></span>
                <span>Returns</span>
              </a>
            </li>
            <li>
              <a href="#" class="text-gray-400 hover:text-white transition-colors duration-200 flex items-center space-x-2 text-light">
                <span class="w-1.5 h-1.5 bg-gray-600 rounded-full"></span>
                <span>FAQ</span>
              </a>
            </li>
          </ul>
        </div>

        <!-- Contact Info -->
        <div>
          <h3 class="text-base font-semibold text-white mb-6">
            Contact Info
          </h3>
          <div class="space-y-4">
            <div class="flex items-start space-x-3">
              <div class="w-8 h-8 bg-gray-800 rounded flex items-center justify-center flex-shrink-0 border border-gray-700">
                <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z"/>
                </svg>
              </div>
              <div>
                <p class="text-gray-400 text-sm">Phone</p>
                <p class="text-white font-medium text-sm">+977 9709518369</p>
              </div>
            </div>
            
            <div class="flex items-start space-x-3">
              <div class="w-8 h-8 bg-gray-800 rounded flex items-center justify-center flex-shrink-0 border border-gray-700">
                <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                </svg>
              </div>
              <div>
                <p class="text-gray-400 text-sm">Email</p>
                <p class="text-white font-medium text-sm">sagardada2535@gmail.com</p>
              </div>
            </div>
            
            <div class="flex items-start space-x-3">
              <div class="w-8 h-8 bg-gray-800 rounded flex items-center justify-center flex-shrink-0 border border-gray-700">
                <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
                </svg>
              </div>
              <div>
                <p class="text-gray-400 text-sm">Business Hours</p>
                <p class="text-white font-medium text-sm">Sun-Fri: 9AM - 6PM</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Bottom Bar -->
      <div class="pt-8 border-t border-gray-800">
        <div class="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">
          <div class="text-gray-500 text-sm">
            © {{ new Date().getFullYear() }} NepaliBazar. All rights reserved.
          </div>
          
          <div class="flex items-center space-x-4 text-gray-500 text-sm">
            <span class="flex items-center space-x-2">
              <span class="w-2 h-2 bg-gray-600 rounded-full"></span>
              <span>Proudly Made in Nepal</span>
              <span class="text-base">🇳🇵</span>
            </span>
          </div>
          
          <!-- <div class="flex space-x-6 text-sm text-gray-500">
            <a href="#" class="hover:text-white transition-colors duration-200">Privacy</a>
            <a href="#" class="hover:text-white transition-colors duration-200">Terms</a>
            <a href="#" class="hover:text-white transition-colors duration-200">Cookies</a>
          </div> -->
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
  transition: all 0.2s ease-in-out;
}
</style>