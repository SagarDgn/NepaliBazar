<template>
  <div class="min-h-screen ">
    <Hero/>
    <Navbar />
    
    <!-- Hero Section -->
    <section class="relative bg-white overflow-hidden">
      <div class="container mx-auto px-4 py-16">
        <div class="flex flex-col lg:flex-row items-center">
          <!-- Text Content -->
          <div class="lg:w-1/2 text-center lg:text-left mb-12 lg:mb-0">
            <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-800 mb-6">
              Our Story from the
              <span class="text-transparent bg-clip-text bg-gradient-to-r from-green-600 to-orange-600">
                Heart of Nepal
              </span>
            </h1>
            <p class="text-xl text-gray-600 mb-8 leading-relaxed">
              Connecting authentic Nepali craftsmanship with the world. 
              We're passionate about preserving our cultural heritage while 
              empowering local artisans across every region of Nepal.
            </p>
          </div>
          
          <!-- Nepal Map Illustration -->
          <div class="lg:w-1/2 relative">
            <div class="relative w-full max-w-lg mx-auto">
              <div class="bg-gradient-to-br from-green-50 to-orange-50 rounded-3xl p-8 shadow-2xl border border-green-200">
                <div class="relative h-80">
                  <!-- Map Outline -->
                  <div class="absolute inset-0 bg-gradient-to-br from-white to-green-50 rounded-2xl border-2 border-green-300"></div>
                  
                  <!-- Himalayan Region -->
                  <div class="absolute top-4 left-1/4 w-16 h-16 bg-gradient-to-br from-blue-500 to-cyan-400 rounded-full shadow-lg transform -translate-x-1/2">
                    <div class="absolute -top-2 -left-2 w-6 h-6 bg-white rounded-full flex items-center justify-center shadow-md">
                      <div class="w-3 h-3 bg-blue-500 rounded-full"></div>
                    </div>
                  </div>
                  
                  <!-- Hilly Region -->
                  <div class="absolute top-1/3 left-2/4 w-20 h-20 bg-gradient-to-br from-green-500 to-emerald-400 rounded-full shadow-lg transform -translate-x-1/2">
                    <div class="absolute -top-2 -right-2 w-6 h-6 bg-white rounded-full flex items-center justify-center shadow-md">
                      <div class="w-3 h-3 bg-green-500 rounded-full"></div>
                    </div>
                  </div>
                  
                  <!-- Terai Region -->
                  <div class="absolute bottom-8 left-1/3 w-24 h-16 bg-gradient-to-br from-orange-500 to-yellow-400 rounded-full shadow-lg transform -translate-x-1/2">
                    <div class="absolute -bottom-2 left-1/2 transform -translate-x-1/2 w-6 h-6 bg-white rounded-full flex items-center justify-center shadow-md">
                      <div class="w-3 h-3 bg-orange-500 rounded-full"></div>
                    </div>
                  </div>
                  
                  <!-- Kathmandu Valley -->
                  <div class="absolute top-2/4 left-2/4 w-8 h-8 bg-red-500 rounded-full shadow-lg transform -translate-x-1/2 -translate-y-1/2 animate-pulse">
                    <div class="absolute inset-0 bg-red-400 rounded-full animate-ping"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Main Content -->
    <div class="container mx-auto px-4 py-12 max-w-7xl">
      
      <!-- Mission & Vision Section -->
      <section class="mb-16">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
          <!-- Mission -->
          <div class="bg-white rounded-2xl p-8 shadow-lg border border-green-200">
            <div class="w-16 h-16 bg-green-100 rounded-2xl flex items-center justify-center mb-6">
              <span class="text-2xl">🎯</span>
            </div>
            <h3 class="text-2xl font-bold text-gray-800 mb-4">Our Mission</h3>
            <p class="text-gray-600 leading-relaxed">
              To create sustainable economic opportunities for Nepali artisans by connecting 
              their authentic craftsmanship with global markets. We believe in fair trade, 
              cultural preservation, and empowering local communities across Nepal's diverse regions.
            </p>
          </div>
          
          <!-- Vision -->
          <div class="bg-white rounded-2xl p-8 shadow-lg border border-orange-200">
            <div class="w-16 h-16 bg-orange-100 rounded-2xl flex items-center justify-center mb-6">
              <span class="text-2xl">🌟</span>
            </div>
            <h3 class="text-2xl font-bold text-gray-800 mb-4">Our Vision</h3>
            <p class="text-gray-600 leading-relaxed">
              To become the bridge that showcases Nepal's rich cultural heritage to the world, 
              making authentic Nepali products accessible globally while ensuring artisans 
              receive the recognition and compensation they truly deserve.
            </p>
          </div>
        </div>
      </section>

      <!-- Our Story Section -->
      <section class="mb-16">
        <div class="bg-gradient-to-r from-green-600 to-orange-600 rounded-3xl p-8 text-white">
          <div class="text-center mb-8">
            <h2 class="text-3xl font-bold mb-4">Our Journey</h2>
            <p class="text-green-100 text-lg max-w-3xl mx-auto">
              From a small idea to a platform connecting artisans across Nepal
            </p>
          </div>
          
          <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
            <div class="text-center">
              <div class="text-4xl mb-4">🏔️</div>
              <h4 class="font-bold text-lg mb-2">Started in the Himalayas</h4>
              <p class="text-green-100">Born from a passion to share Nepal's beauty with the world</p>
            </div>
            
            <div class="text-center">
              <div class="text-4xl mb-4">🤝</div>
              <h4 class="font-bold text-lg mb-2">500+ Artisans</h4>
              <p class="text-green-100">Partnering with skilled craftspeople across all regions</p>
            </div>
            
            <div class="text-center">
              <div class="text-4xl mb-4">🌍</div>
              <h4 class="font-bold text-lg mb-2">Global Reach</h4>
              <p class="text-green-100">Delivering authentic Nepali products worldwide</p>
            </div>
          </div>
        </div>
      </section>

      <!-- Values Section -->
      <section class="mb-16">
        <div class="text-center mb-12">
          <h2 class="text-4xl font-bold text-gray-800 mb-4">Our Values</h2>
          <p class="text-xl text-gray-600 max-w-3xl mx-auto">
            The principles that guide everything we do
          </p>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-green-200 text-center">
            <div class="w-12 h-12 bg-green-100 rounded-2xl flex items-center justify-center mx-auto mb-4">
              <span class="text-xl">💚</span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Authenticity</h3>
            <p class="text-gray-600 text-sm">Genuine products made with traditional techniques</p>
          </div>
          
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-blue-200 text-center">
            <div class="w-12 h-12 bg-blue-100 rounded-2xl flex items-center justify-center mx-auto mb-4">
              <span class="text-xl">⚖️</span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Fair Trade</h3>
            <p class="text-gray-600 text-sm">Ensuring artisans receive fair compensation</p>
          </div>
          
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-orange-200 text-center">
            <div class="w-12 h-12 bg-orange-100 rounded-2xl flex items-center justify-center mx-auto mb-4">
              <span class="text-xl">🌱</span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Sustainability</h3>
            <p class="text-gray-600 text-sm">Eco-friendly practices and materials</p>
          </div>
          
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-purple-200 text-center">
            <div class="w-12 h-12 bg-purple-100 rounded-2xl flex items-center justify-center mx-auto mb-4">
              <span class="text-xl">🤲</span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Community</h3>
            <p class="text-gray-600 text-sm">Supporting local communities and traditions</p>
          </div>
        </div>
      </section>

      <!-- Regional Impact Section -->
      <section class="mb-16">
        <div class="text-center mb-12">
          <h2 class="text-4xl font-bold text-gray-800 mb-4">Regional Impact</h2>
          <p class="text-xl text-gray-600 max-w-3xl mx-auto">
            Creating opportunities across Nepal's diverse landscapes
          </p>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-blue-200 text-center">
            <div class=" mx-auto mb-4 bg-blue-100 rounded-2xl flex items-center justify-center">
              <span class="text-2xl">
                <img src="../assets/himalaya.webp"/>
              </span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Himalayan Region</h3>
            <p class="text-gray-600 mb-4">Supporting mountain communities with woolen crafts and herbal products</p>
            <div class="text-blue-600 font-semibold">150+ Artisans</div>
          </div>
          
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-green-200 text-center">
            <div class=" mx-auto mb-4 bg-green-100 rounded-2xl flex items-center justify-center">
              <span class="text-2xl">
                <img src="../assets/hills.webp"/>
              </span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Hilly Region</h3>
            <p class="text-gray-600 mb-4">Empowering hill communities with handicrafts and textiles</p>
            <div class="text-green-600 font-semibold">200+ Artisans</div>
          </div>
          
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-orange-200 text-center">
            <div class=" mx-auto mb-4 bg-orange-100 rounded-2xl flex items-center justify-center">
              <span class="text-2xl">
                <img src="../assets/terai.webp"/>
              </span>
            </div>
            <h3 class="text-xl font-semibold text-gray-800 mb-2">Terai Region</h3>
            <p class="text-gray-600 mb-4">Promoting agricultural products and traditional crafts from the plains</p>
            <div class="text-orange-600 font-semibold">150+ Artisans</div>
          </div>
        </div>
      </section>

      <!-- Call to Action -->
      <section class="text-center">
        <div class="bg-gradient-to-r from-green-600 to-orange-600 rounded-3xl p-12 text-white">
          <h2 class="text-3xl font-bold mb-4">Join Our Journey</h2>
          <p class="text-green-100 text-lg mb-8 max-w-2xl mx-auto">
            Be part of our mission to preserve Nepali heritage and support local artisans. 
            Every purchase makes a difference.
          </p>
          <div class="flex flex-col sm:flex-row gap-4 justify-center">
            <router-link 
              to="/products" 
              class="px-8 py-4 bg-white text-green-600 font-semibold rounded-2xl hover:bg-gray-100 transition-all duration-300 transform hover:scale-105 shadow-lg"
            >
              Shop Nepali Products
            </router-link>
            <router-link 
              to="/contactus" 
              class="px-8 py-4 border border-white text-white font-semibold rounded-2xl hover:bg-white hover:text-green-600 transition-all duration-300"
            >
              Contact Us
            </router-link>
          </div>
        </div>
      </section>
    </div>

    <Footer />
  </div>
</template>

<script>
import Navbar from "../components/ui/Navbar.vue";
import Footer from "../components/ui/Footer.vue";
import Hero from "../components/ui/Hero.vue";

export default {
  name: "AboutUs",
  components: {Hero, Navbar, Footer }
};
</script>

<style scoped>
/* Smooth transitions for all interactive elements */
button, a {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Custom shadow for depth */
.shadow-lg {
  box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
}
</style>