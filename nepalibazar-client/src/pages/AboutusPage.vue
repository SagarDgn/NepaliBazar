<template>
  <div class="min-h-screen bg-white">
    <Hero/>
    <Navbar />
    
    <!-- Hero Section with Border Lines -->
    <section class="relative bg-white border-b border-gray-200">
      <div class="container mx-auto px-6 py-20">
        <!-- Decorative Lines -->
        <div class="absolute top-0 left-0 w-full h-px bg-gradient-to-r from-transparent via-gray-300 to-transparent"></div>
        <div class="absolute bottom-0 left-0 w-full h-px bg-gradient-to-r from-transparent via-gray-300 to-transparent"></div>
        
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
          <!-- Text Content -->
          <div class="relative">
            <!-- Side Border Line -->
            <div class="absolute -left-6 top-0 bottom-0 w-px bg-gray-200 hidden lg:block"></div>
            
            <div class="space-y-6">
              <div class="flex items-center space-x-4 mb-6">
                <div class="w-12 h-px bg-gray-400"></div>
                <span class="text-sm font-semibold text-gray-500 uppercase tracking-wider">Our Story</span>
              </div>
              
              <h1 class="text-5xl md:text-6xl font-bold text-gray-900 leading-tight">
                Crafting Legacy from the 
                <span class="text-gray-700">Heart of Nepal</span>
              </h1>
              
              <p class="text-xl text-gray-600 leading-relaxed border-l-4 border-gray-300 pl-6 py-2">
                Bridging ancient Nepali craftsmanship with modern global markets. 
                We preserve cultural heritage while creating sustainable opportunities 
                for artisans across every region.
              </p>
            </div>
          </div>

          <!-- Nepal Map Visualization -->
          <div class="relative">
            <div class="bg-gradient-to-br from-gray-50 to-white rounded-2xl p-8 border border-gray-200 shadow-sm">
              <div class="relative h-80">
                <!-- Base Map Container -->
                <div class="absolute inset-0 bg-gradient-to-br from-blue-50 to-green-50 rounded-xl border border-gray-300"></div>
                
                <!-- Regional Markers -->
                <div class="absolute top-8 left-1/4 transform -translate-x-1/2">
                  <div class="relative">
                    <div class="w-16 h-16 bg-white rounded-full border-2 border-gray-700 shadow-lg flex items-center justify-center">
                      <div class="w-8 h-8 bg-gray-700 rounded-full flex items-center justify-center">
                        <div class="w-3 h-3 bg-white rounded-full"></div>
                      </div>
                    </div>
                    <div class="absolute -bottom-8 left-1/2 transform -translate-x-1/2 text-center">
                      <span class="text-sm font-bold text-gray-800 bg-white px-3 py-1 rounded-full border border-gray-300 shadow-sm">Himalayan</span>
                    </div>
                  </div>
                </div>

                <div class="absolute top-1/3 left-2/3 transform -translate-x-1/2">
                  <div class="relative">
                    <div class="w-20 h-20 bg-white rounded-full border-2 border-gray-600 shadow-lg flex items-center justify-center">
                      <div class="w-10 h-10 bg-gray-600 rounded-full flex items-center justify-center">
                        <div class="w-4 h-4 bg-white rounded-full"></div>
                      </div>
                    </div>
                    <div class="absolute -bottom-8 left-1/2 transform -translate-x-1/2 text-center">
                      <span class="text-sm font-bold text-gray-800 bg-white px-3 py-1 rounded-full border border-gray-300 shadow-sm">Hilly</span>
                    </div>
                  </div>
                </div>

                <div class="absolute bottom-16 left-1/3 transform -translate-x-1/2">
                  <div class="relative">
                    <div class="w-24 h-18 bg-white rounded-full border-2 border-gray-500 shadow-lg flex items-center justify-center">
                      <div class="w-12 h-8 bg-gray-500 rounded-full flex items-center justify-center">
                        <div class="w-4 h-4 bg-white rounded-full"></div>
                      </div>
                    </div>
                    <div class="absolute -top-8 left-1/2 transform -translate-x-1/2 text-center">
                      <span class="text-sm font-bold text-gray-800 bg-white px-3 py-1 rounded-full border border-gray-300 shadow-sm">Terai</span>
                    </div>
                  </div>
                </div>

                <!-- Kathmandu Valley -->
                <div class="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                  <div class="relative">
                    <div class="w-8 h-8 bg-gray-900 rounded-full border-2 border-white shadow-xl flex items-center justify-center animate-pulse">
                      <div class="w-4 h-4 bg-gray-800 rounded-full"></div>
                    </div>
                    <div class="absolute -top-8 left-1/2 transform -translate-x-1/2 text-center">
                      <span class="text-sm font-black text-gray-900 bg-yellow-100 px-3 py-1 rounded-full border border-yellow-300 shadow-sm">Kathmandu</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Mission & Vision Section -->
    <section class="relative bg-gray-50 py-20">
      <div class="container mx-auto px-6">
        <!-- Section Border -->
        <div class="absolute top-0 left-1/2 transform -translate-x-1/2 w-24 h-1 bg-gray-300 rounded-full"></div>
        
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 max-w-6xl mx-auto">
          <!-- Mission Card -->
          <div class="relative bg-white rounded-2xl p-8 border border-gray-200 shadow-sm">
            <div class="absolute -top-4 -left-4 w-8 h-8 bg-blue-500 rounded-full"></div>
            <div class="absolute -bottom-4 -right-4 w-6 h-6 bg-green-500 rounded-full"></div>
            
            <div class="relative z-10">
              <div class="flex items-center mb-6 pb-4 border-b border-gray-200">
                <div class="w-12 h-12 bg-blue-50 rounded-lg flex items-center justify-center mr-4 border border-blue-100">
                  <svg class="w-6 h-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z"/>
                  </svg>
                </div>
                <h3 class="text-2xl font-bold text-gray-900">Our Mission</h3>
              </div>
              <p class="text-gray-600 leading-relaxed text-lg border-l-2 border-blue-200 pl-6">
                To create sustainable economic opportunities for Nepali artisans by connecting 
                their authentic craftsmanship with global markets through fair trade practices 
                and cultural preservation.
              </p>
            </div>
          </div>

          <!-- Vision Card -->
          <div class="relative bg-white rounded-2xl p-8 border border-gray-200 shadow-sm">
            <div class="absolute -top-4 -right-4 w-8 h-8 bg-purple-500 rounded-full"></div>
            <div class="absolute -bottom-4 -left-4 w-6 h-6 bg-orange-500 rounded-full"></div>
            
            <div class="relative z-10">
              <div class="flex items-center mb-6 pb-4 border-b border-gray-200">
                <div class="w-12 h-12 bg-purple-50 rounded-lg flex items-center justify-center mr-4 border border-purple-100">
                  <svg class="w-6 h-6 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"/>
                  </svg>
                </div>
                <h3 class="text-2xl font-bold text-gray-900">Our Vision</h3>
              </div>
              <p class="text-gray-600 leading-relaxed text-lg border-l-2 border-purple-200 pl-6">
                To become the premier global platform that showcases Nepal's rich cultural heritage, 
                making authentic Nepali products accessible worldwide while ensuring artisans receive 
                the recognition and compensation they deserve.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Stats Section -->
    <section class="relative bg-white py-20 border-t border-b border-gray-200">
      <div class="container mx-auto px-6">
        <div class="text-center mb-16">
          <div class="inline-flex items-center space-x-4 mb-6">
            <div class="w-16 h-px bg-gray-300"></div>
            <span class="text-sm font-semibold text-gray-500 uppercase tracking-wider">Our Impact</span>
            <div class="w-16 h-px bg-gray-300"></div>
          </div>
          <h2 class="text-4xl md:text-5xl font-bold text-gray-900 mb-4">Building Bridges, Creating Impact</h2>
          <p class="text-xl text-gray-600 max-w-2xl mx-auto">
            Connecting Nepali craftsmanship with global opportunities
          </p>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-3 gap-8 max-w-4xl mx-auto">
          <div class="text-center group">
            <div class="relative bg-white rounded-2xl p-8 border-2 border-gray-100 hover:border-blue-200 transition-all duration-300 group-hover:shadow-lg">
              <div class="w-20 h-20 bg-blue-50 rounded-full flex items-center justify-center mx-auto mb-6 border-2 border-blue-100 group-hover:border-blue-200 transition-colors">
                <svg class="w-10 h-10 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0z"/>
                </svg>
              </div>
              <div class="text-4xl font-bold text-gray-900 mb-2">500+</div>
              <div class="text-lg font-semibold text-gray-700 mb-3">Skilled Artisans</div>
              <div class="w-12 h-1 bg-blue-500 mx-auto rounded-full"></div>
              <p class="text-gray-600 mt-4 text-sm">Partnering with craftspeople across all regions of Nepal</p>
            </div>
          </div>

          <div class="text-center group">
            <div class="relative bg-white rounded-2xl p-8 border-2 border-gray-100 hover:border-green-200 transition-all duration-300 group-hover:shadow-lg">
              <div class="w-20 h-20 bg-green-50 rounded-full flex items-center justify-center mx-auto mb-6 border-2 border-green-100 group-hover:border-green-200 transition-colors">
                <svg class="w-10 h-10 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3.055 11H5a2 2 0 012 2v1a2 2 0 002 2 2 2 0 012 2v2.945M8 3.935V5.5A2.5 2.5 0 0010.5 8h.5a2 2 0 012 2 2 2 0 104 0 2 2 0 012-2h1.064M15 20.488V18a2 2 0 012-2h3.064M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                </svg>
              </div>
              <div class="text-4xl font-bold text-gray-900 mb-2">50+</div>
              <div class="text-lg font-semibold text-gray-700 mb-3">Countries Reached</div>
              <div class="w-12 h-1 bg-green-500 mx-auto rounded-full"></div>
              <p class="text-gray-600 mt-4 text-sm">Delivering authentic Nepali products worldwide</p>
            </div>
          </div>

          <div class="text-center group">
            <div class="relative bg-white rounded-2xl p-8 border-2 border-gray-100 hover:border-purple-200 transition-all duration-300 group-hover:shadow-lg">
              <div class="w-20 h-20 bg-purple-50 rounded-full flex items-center justify-center mx-auto mb-6 border-2 border-purple-100 group-hover:border-purple-200 transition-colors">
                <svg class="w-10 h-10 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                </svg>
              </div>
              <div class="text-4xl font-bold text-gray-900 mb-2">5x</div>
              <div class="text-lg font-semibold text-gray-700 mb-3">Income Growth</div>
              <div class="w-12 h-1 bg-purple-500 mx-auto rounded-full"></div>
              <p class="text-gray-600 mt-4 text-sm">Increasing artisan earnings through fair trade</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Values Section -->
    <section class="relative bg-gray-50 py-20">
      <div class="container mx-auto px-6">
        <div class="text-center mb-16">
          <div class="inline-flex items-center space-x-4 mb-6">
            <div class="w-16 h-px bg-gray-300"></div>
            <span class="text-sm font-semibold text-gray-500 uppercase tracking-wider">Our Values</span>
            <div class="w-16 h-px bg-gray-300"></div>
          </div>
          <h2 class="text-4xl md:text-5xl font-bold text-gray-900 mb-4">The Principles That Guide Us</h2>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 max-w-6xl mx-auto">
          <div class="bg-white rounded-xl p-6 border-2 border-gray-100 hover:border-blue-200 transition-all duration-300 group hover:shadow-lg">
            <div class="w-14 h-14 bg-blue-50 rounded-lg flex items-center justify-center mb-4 border-2 border-blue-100 group-hover:border-blue-200">
              <svg class="w-7 h-7 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
              </svg>
            </div>
            <h3 class="text-xl font-bold text-gray-900 mb-3">Authenticity</h3>
            <div class="w-8 h-1 bg-blue-500 rounded-full mb-4"></div>
            <p class="text-gray-600 text-sm leading-relaxed">Genuine products crafted with traditional techniques passed down through generations</p>
          </div>

          <div class="bg-white rounded-xl p-6 border-2 border-gray-100 hover:border-green-200 transition-all duration-300 group hover:shadow-lg">
            <div class="w-14 h-14 bg-green-50 rounded-lg flex items-center justify-center mb-4 border-2 border-green-100 group-hover:border-green-200">
              <svg class="w-7 h-7 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
              </svg>
            </div>
            <h3 class="text-xl font-bold text-gray-900 mb-3">Fair Trade</h3>
            <div class="w-8 h-1 bg-green-500 rounded-full mb-4"></div>
            <p class="text-gray-600 text-sm leading-relaxed">Ensuring artisans receive fair compensation and recognition for their exceptional work</p>
          </div>

          <div class="bg-white rounded-xl p-6 border-2 border-gray-100 hover:border-purple-200 transition-all duration-300 group hover:shadow-lg">
            <div class="w-14 h-14 bg-purple-50 rounded-lg flex items-center justify-center mb-4 border-2 border-purple-100 group-hover:border-purple-200">
              <svg class="w-7 h-7 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"/>
              </svg>
            </div>
            <h3 class="text-xl font-bold text-gray-900 mb-3">Sustainability</h3>
            <div class="w-8 h-1 bg-purple-500 rounded-full mb-4"></div>
            <p class="text-gray-600 text-sm leading-relaxed">Eco-friendly practices and materials that respect our environment and future generations</p>
          </div>

          <div class="bg-white rounded-xl p-6 border-2 border-gray-100 hover:border-orange-200 transition-all duration-300 group hover:shadow-lg">
            <div class="w-14 h-14 bg-orange-50 rounded-lg flex items-center justify-center mb-4 border-2 border-orange-100 group-hover:border-orange-200">
              <svg class="w-7 h-7 text-orange-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0z"/>
              </svg>
            </div>
            <h3 class="text-xl font-bold text-gray-900 mb-3">Community</h3>
            <div class="w-8 h-1 bg-orange-500 rounded-full mb-4"></div>
            <p class="text-gray-600 text-sm leading-relaxed">Supporting local communities and preserving cultural traditions for future generations</p>
          </div>
        </div>
      </div>
    </section>

    <!-- Regional Impact Section -->
    <section class="relative bg-white py-20 border-t border-gray-200">
      <div class="container mx-auto px-6">
        <div class="text-center mb-16">
          <div class="inline-flex items-center space-x-4 mb-6">
            <div class="w-16 h-px bg-gray-300"></div>
            <span class="text-sm font-semibold text-gray-500 uppercase tracking-wider">Regional Impact</span>
            <div class="w-16 h-px bg-gray-300"></div>
          </div>
          <h2 class="text-4xl md:text-5xl font-bold text-gray-900 mb-4">Empowering Nepal's Diverse Regions</h2>
          <p class="text-xl text-gray-600 max-w-2xl mx-auto">
            Creating sustainable opportunities across every landscape
          </p>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-3 gap-8 max-w-6xl mx-auto">
          <div class="group">
            <div class="bg-white rounded-2xl overflow-hidden border-2 border-gray-100 hover:border-blue-200 transition-all duration-300 group-hover:shadow-xl">
              <div class="h-48 bg-gradient-to-br from-blue-50 to-cyan-100 relative overflow-hidden">
                <div class="absolute inset-0  opacity-20"></div>
                <img src="../assets/himalaya.webp"/>
                <div class="absolute bottom-4 left-4">
                  <span class="bg-white text-blue-700 px-4 py-2 rounded-full text-sm font-bold border border-blue-200">Himalayan Region</span>
                </div>
              </div>
              <div class="p-6">
                <h3 class="text-xl font-bold text-gray-900 mb-3">Mountain Communities</h3>
                <p class="text-gray-600 mb-4 leading-relaxed">Supporting high-altitude artisans with woolen crafts, herbal products, and traditional Thangka paintings</p>
                <div class="flex items-center justify-between border-t border-gray-100 pt-4">
                  <span class="text-lg font-bold text-blue-600">150+ Artisans</span>
                  <div class="w-12 h-1 bg-blue-500 rounded-full"></div>
                </div>
              </div>
            </div>
          </div>

          <div class="group">
           
            <div class="bg-white rounded-2xl overflow-hidden border-2 border-gray-100 hover:border-green-200 transition-all duration-300 group-hover:shadow-xl">
              <div class="h-48 bg-gradient-to-br from-green-50 to-emerald-100 relative overflow-hidden">
                <div class="absolute inset-0">
                  <img src="../assets/hills.webp"/>
                </div>
                <div class="absolute bottom-4 left-4">
                   
                  <span class="bg-white text-green-700 px-4 py-2 rounded-full text-sm font-bold border border-green-200">Hilly Region</span>
                </div>
              </div>
              <div class="p-6">
                <h3 class="text-xl font-bold text-gray-900 mb-3">Hill Communities</h3>
                <p class="text-gray-600 mb-4 leading-relaxed">Empowering mid-hill artisans with handmade paper, wood carvings, and traditional textiles</p>
                <div class="flex items-center justify-between border-t border-gray-100 pt-4">
                  <span class="text-lg font-bold text-green-600">200+ Artisans</span>
                  <div class="w-12 h-1 bg-green-500 rounded-full"></div>
                </div>
              </div>
            </div>
          </div>

          <div class="group">
            <div class="bg-white rounded-2xl overflow-hidden border-2 border-gray-100 hover:border-orange-200 transition-all duration-300 group-hover:shadow-xl">
              <div class="h-48 bg-gradient-to-br from-orange-50 to-amber-100 relative overflow-hidden">
                <div class="absolute inset-0  ">
                  <img src="../assets/terai.webp"/>
                </div>
                <div class="absolute bottom-4 left-4">
                  <span class="bg-white text-orange-700 px-4 py-2 rounded-full text-sm font-bold border border-orange-200">Terai Region</span>
                </div>
              </div>
              <div class="p-6">
                <h3 class="text-xl font-bold text-gray-900 mb-3">Plains Communities</h3>
                <p class="text-gray-600 mb-4 leading-relaxed">Promoting agricultural products, Madhubani paintings, and traditional crafts from Nepal's southern plains</p>
                <div class="flex items-center justify-between border-t border-gray-100 pt-4">
                  <span class="text-lg font-bold text-orange-600">150+ Artisans</span>
                  <div class="w-12 h-1 bg-orange-500 rounded-full"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA Section -->
    <section class="relative bg-gray-900 py-20">
      <div class="container mx-auto px-6">
        <div class="max-w-4xl mx-auto text-center">
          <div class="inline-flex items-center space-x-4 mb-6">
            <div class="w-16 h-px bg-gray-600"></div>
            <span class="text-sm font-semibold text-gray-400 uppercase tracking-wider">Join Our Mission</span>
            <div class="w-16 h-px bg-gray-600"></div>
          </div>
          
          <h2 class="text-4xl md:text-5xl font-bold text-white mb-6">Be Part of Nepal's Cultural Renaissance</h2>
          <p class="text-xl text-gray-300 mb-8 leading-relaxed max-w-2xl mx-auto">
            Every purchase preserves heritage, supports communities, and creates opportunities for Nepali artisans to thrive in the global marketplace.
          </p>
          
          <div class="flex flex-col sm:flex-row gap-4 justify-center items-center">
            <router-link 
              to="/products" 
              class="px-8 py-4 bg-white text-gray-900 font-bold rounded-lg hover:bg-gray-100 transition-all duration-200 text-lg border-2 border-white hover:border-gray-300 shadow-lg hover:shadow-xl"
            >
              Explore Nepali Crafts
            </router-link>
            <router-link 
              to="/contactus" 
              class="px-8 py-4 border-2 border-white text-white font-bold rounded-lg hover:bg-white hover:text-gray-900 transition-all duration-200 text-lg"
            >
              Partner With Us
            </router-link>
          </div>
        </div>
      </div>
    </section>

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
/* Custom animations and transitions */
.group:hover .group-hover\:scale-105 {
  transform: scale(1.05);
}

/* Smooth transitions for all interactive elements */
button, a, .group {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Custom shadow depths */
.shadow-sm {
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
}

.shadow-lg {
  box-shadow: 0 10px 25px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
}

.shadow-xl {
  box-shadow: 0 20px 40px -10px rgba(0, 0, 0, 0.15), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
}
</style>