<template>
  <div class="min-h-screen bg-gradient-to-br  ">
    <Hero />
    <Navbar />
    
    
    <div class="container mx-auto px-4 py-8 max-w-6xl">
      <div class="flex flex-col lg:flex-row items-center justify-center gap-8 lg:gap-16">
        <!-- Left Side - Image -->
        <div class="lg:w-1/2 flex justify-center">
          <div class="relative">
            <img
              src="../assets/signupPic.PNG"
              alt="Start your selling journey"
              class="rounded-3xl shadow-2xl w-full max-w-lg transform hover:scale-105 transition-transform duration-500"
            />
            <!-- Floating decoration elements -->
            <div class="absolute -top-4 -left-4 w-24 h-24 bg-orange-200 rounded-full opacity-50 animate-pulse"></div>
            <div class="absolute -bottom-4 -right-4 w-16 h-16 bg-amber-200 rounded-full opacity-50 animate-pulse delay-1000"></div>
            <!-- Seller Badge -->
            <div class="absolute top-4 right-4 bg-white/90 backdrop-blur-sm rounded-2xl p-3 shadow-lg">
              <div class="flex items-center space-x-2 text-red-600">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                </svg>
                <span class="text-sm font-semibold">Seller</span>
              </div>
            </div>
          </div>
        </div>

        <!-- Right Side - Seller Signup Form -->
        <div class="lg:w-1/2 flex justify-center">
          <div class="bg-white/80 backdrop-blur-sm rounded-3xl shadow-2xl p-8 w-full max-w-md border border-white/20">
            <!-- Header -->
            <div class="text-center mb-8">
              <h1 class="text-3xl font-bold bg-gradient-to-r from-red-600 to-red-600 bg-clip-text text-transparent mb-2">
                Start Selling With Us
              </h1>
              <p class="text-gray-600">Join our marketplace and grow your business</p>
            </div>

            <!-- Business Benefits -->
            <div class="bg-orange-50/50 rounded-2xl p-4 border border-orange-100 mb-6">
              <h3 class="text-sm font-semibold text-orange-800 mb-2 flex items-center">
                <svg class="w-4 h-4 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
                </svg>
                Seller Benefits
              </h3>
              <ul class="text-xs text-orange-600 space-y-1">
                <li class="flex items-center">
                  <span class="w-1 h-1 bg-orange-500 rounded-full mr-2"></span>
                  Reach thousands of customers
                </li>
                <li class="flex items-center">
                  <span class="w-1 h-1 bg-orange-500 rounded-full mr-2"></span>
                  Easy inventory management
                </li>
                <li class="flex items-center">
                  <span class="w-1 h-1 bg-orange-500 rounded-full mr-2"></span>
                  Secure payments & analytics
                </li>
              </ul>
            </div>

            <!-- Form -->
            <form class="space-y-6" @submit.prevent="handleSentOtp">
              <!-- Email Input -->
              <div class="space-y-2">
                <label class="text-sm font-medium text-gray-700 flex items-center">
                  <svg class="w-4 h-4 mr-2 text-orange-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                  </svg>
                  Business Email
                </label>
                <div class="relative">
                  <input
                    v-model="email"
                    type="email"
                    placeholder="Enter your business Gmail"
                    class="w-full px-4 py-3 pl-11 border border-gray-300 rounded-2xl focus:ring-2 focus:ring-orange-500 focus:border-transparent transition-all duration-200 outline-none bg-white/50"
                    :class="{ 'border-red-500 focus:ring-red-500': emailError }"
                  />
                  <div class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"/>
                    </svg>
                  </div>
                </div>
                <p v-if="emailError" class="text-red-500 text-sm flex items-center mt-1 animate-pulse">
                  <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                  {{ emailError }}
                </p>
              </div>

              <!-- Submit Button -->
              <button
                type="submit"
                :disabled="loading"
                class="w-full py-3 px-4 bg-gradient-to-r from-red-600 to-red-600 hover:from-red-700 hover:to-red-700 text-white font-semibold rounded-2xl shadow-lg transition-all duration-300 transform hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none"
              >
                <div class="flex items-center justify-center space-x-2">
                  <svg v-if="loading" class="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  <span>{{ loading ? "Sending Verification..." : "Get Seller Verification" }}</span>
                </div>
              </button>

              <!-- Seller Requirements -->
             
            </form>

            <!-- Login Redirect -->
            <div class="text-center mt-6 pt-6 border-t border-gray-200">
              <p class="text-gray-600 text-sm">
                Already have a seller account?
                <router-link 
                  to="/sellerlogin" 
                  class="text-orange-600 hover:text-orange-700 font-semibold transition-colors duration-200 ml-1 hover:underline"
                >
                  Sign In to Seller Center
                </router-link>
              </p>
            </div>

            <!-- Trust Badges for Sellers -->
            <div class="flex justify-center space-x-6 mt-6 pt-6 border-t border-gray-200">
              <div class="text-center">
                <div class="w-8 h-8 mx-auto mb-1 text-green-500">
                  <svg fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M2.166 4.999A11.954 11.954 0 0010 1.944 11.954 11.954 0 0017.834 5c.11.65.166 1.32.166 2.001 0 5.225-3.34 9.67-8 11.317C5.34 16.67 2 12.225 2 7c0-.682.057-1.35.166-2.001zm11.541 3.708a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"/>
                  </svg>
                </div>
                <span class="text-xs text-gray-500">Verified</span>
              </div>
              <div class="text-center">
                <div class="w-8 h-8 mx-auto mb-1 text-blue-500">
                  <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
                  </svg>
                </div>
                <span class="text-xs text-gray-500">Secure</span>
              </div>
              <div class="text-center">
                <div class="w-8 h-8 mx-auto mb-1 text-purple-500">
                  <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1"/>
                  </svg>
                </div>
                <span class="text-xs text-gray-500">Payments</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <Footer />
  </div>
</template>

<script>
import { toast } from "vue3-toastify";
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";
import { sentOtp } from "../services/SellersignupService";
import Footer from "../components/ui/Footer.vue";

export default {
  components: { Hero, Navbar, Footer },
  name: "Sellersignup",

  data() {
    return {
      email: "",
      emailError: "",
      loading: false
    };
  },

  methods: {
    isValidGmail(email) {
      const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
      return gmailRegex.test(email);
    },

    async handleSentOtp() {
      if (!this.email) {
        this.emailError = "Business email is required";
        return;
      }

      if (!this.isValidGmail(this.email)) {
        this.emailError = "Please enter a valid Gmail address";
        toast.error("Invalid Gmail format");
        return;
      }

      this.emailError = "";
      this.loading = true;

      try {
        const response = await sentOtp({ emailPhone: this.email });
        if (response.data.code == "0") {
          toast.success("📧 Verification code sent to your email!", { autoClose: 3000 });
          sessionStorage.setItem("signupEmail", this.email);
          this.$router.push({ name: "SellerVerification" });
        } else {
          toast.error(response.data.message || "This email is already registered as a seller!");
        }
      } catch (error) {
        console.error("Seller signup error:", error);
        toast.error(error.response?.data?.message || "Unable to send verification. Please try again.");
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
/* Custom styles for seller theme */
@keyframes gradientShift {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

.bg-gradient-to-r {
  background-size: 200% 200%;
  animation: gradientShift 3s ease infinite;
}

/* Custom scrollbar */
::-webkit-scrollbar {
  width: 6px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}

/* Professional business animation */
@keyframes professionalPulse {
  0%, 100% { transform: scale(1); }
  50% { transform: scale(1.02); }
}

.bg-gradient-to-r:hover {
  animation: professionalPulse 2s ease-in-out infinite;
}
</style>