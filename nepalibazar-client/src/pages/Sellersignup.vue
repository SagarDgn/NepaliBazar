<template>
  <div class="min-h-screen bg-white">
    <!-- Header -->
  <Hero/>
  <Navbar/>


    <div class="container mx-auto px-4 py-8 max-w-6xl">
      <div class="flex flex-col lg:flex-row items-start justify-between gap-12">
        <!-- Left Side - Form -->
        <div class="lg:w-1/2 w-full max-w-md mx-auto lg:mx-0">
          <div class="py-8">
            <!-- Progress Steps -->
            <div class="flex items-center justify-between mb-8">
              <div class="flex items-center">
                <div class="w-8 h-8 rounded-full bg-gray-900 text-white flex items-center justify-center text-sm font-medium">
                  1
                </div>
                <span class="ml-2 text-sm font-medium text-gray-900">Account</span>
              </div>
              <div class="flex-1 h-0.5 bg-gray-200 mx-4"></div>
              <div class="flex items-center">
                <div class="w-8 h-8 rounded-full bg-gray-200 text-gray-500 flex items-center justify-center text-sm font-medium">
                  2
                </div>
                <span class="ml-2 text-sm font-medium text-gray-500">Verification</span>
              </div>
            </div>

            <!-- Form Header -->
            <div class="mb-8">
              <h1 class="text-2xl font-bold text-gray-900 mb-2">Start Your Seller Journey</h1>
              <p class="text-gray-600">Join our marketplace and grow your business</p>
            </div>

            <!-- Social Signup -->
            <div class="space-y-3 mb-6">
              <div id="googleLoginButton"></div>
              
              
            </div>

            <!-- Divider -->
            <div class="relative mb-6">
              <div class="absolute inset-0 flex items-center">
                <div class="w-full border-t border-gray-300"></div>
              </div>
              <div class="relative flex justify-center text-sm">
                <span class="px-2 bg-white text-gray-500">Or continue with email</span>
              </div>
            </div>

            <!-- Form -->
            <form class="space-y-6" @submit.prevent="handleSentOtp">
              <!-- Email Input -->
              <div>
                <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
                  Business Email
                </label>
                <input
                  v-model="email"
                  id="email"
                  type="email"
                  placeholder="Enter your business email"
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 outline-none"
                  :class="{ 'border-red-500 focus:ring-red-500 focus:border-red-500': emailError }"
                />
                <p v-if="emailError" class="text-red-500 text-sm mt-2 flex items-center">
                  <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                  {{ emailError }}
                </p>
              </div>

              <!-- Terms and Conditions -->
              <div class="flex items-start space-x-3 p-4 bg-gray-50 rounded-lg border border-gray-200">
                <input
                  type="checkbox"
                  id="terms"
                  class="mt-1 w-4 h-4 text-gray-900 border-gray-300 rounded focus:ring-gray-900"
                />
                <label for="terms" class="text-sm text-gray-600">
                  I agree to the 
                  <a href="#" class="text-gray-900 hover:underline font-medium">Seller Agreement</a>, 
                  <a href="#" class="text-gray-900 hover:underline font-medium">Terms of Service</a> 
                  and 
                  <a href="#" class="text-gray-900 hover:underline font-medium">Privacy Policy</a>
                </label>
              </div>

              <!-- Submit Button -->
              <button
                type="submit"
                :disabled="loading"
                class="w-full py-3 px-4 bg-black text-white font-semibold rounded-lg shadow-sm transition-all duration-300 disabled:opacity-50 disabled:cursor-not-allowed"
              >
                <div class="flex items-center justify-center space-x-2">
                  <svg v-if="loading" class="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  <span>{{ loading ? "Sending Verification..." : "Get Seller Verification" }}</span>
                </div>
              </button>

              <!-- Login Redirect -->
              <div class="text-center pt-4 border-t border-gray-200">
                <p class="text-gray-600 text-sm">
                  Already have a seller account?
                  <router-link 
                    to="/sellerlogin" 
                    class="text-gray-900 hover:text-gray-700 font-semibold transition-colors duration-200 ml-1 hover:underline"
                  >
                    Sign In to Seller Center
                  </router-link>
                </p>
              </div>
            </form>
          </div>
        </div>

        <!-- Right Side - Benefits -->
        <div class="lg:w-1/2 w-full bg-gray-50 rounded-2xl p-8 lg:p-12">
          <div class="max-w-md mx-auto">
            <h2 class="text-2xl font-bold text-gray-900 mb-8">Why Sell With Us?</h2>
            
            <div class="space-y-6">
              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Large Customer Base</h3>
                  <p class="text-gray-600 text-sm">Access thousands of active buyers looking for authentic Nepali products.</p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Secure Payments</h3>
                  <p class="text-gray-600 text-sm">Get paid securely with multiple payment options and reliable transactions.</p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Business Analytics</h3>
                  <p class="text-gray-600 text-sm">Track your sales, customer behavior, and business growth with detailed analytics.</p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18.364 5.636l-3.536 3.536m0 5.656l3.536 3.536M9.172 9.172L5.636 5.636m3.536 9.192L5.636 18.364M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Easy Returns & Support</h3>
                  <p class="text-gray-600 text-sm">Dedicated seller support and streamlined return management system.</p>
                </div>
              </div>
            </div>

            <!-- Seller Stats -->
            <div class="mt-8 p-6 bg-white rounded-xl border border-gray-200">
              <h4 class="font-semibold text-gray-900 mb-4 text-center">Our Seller Community</h4>
              <div class="grid grid-cols-3 gap-4 text-center">
                <div>
                  <div class="text-2xl font-bold text-gray-900">500+</div>
                  <div class="text-xs text-gray-500">Active Sellers</div>
                </div>
                <div>
                  <div class="text-2xl font-bold text-gray-900">50K+</div>
                  <div class="text-xs text-gray-500">Monthly Orders</div>
                </div>
                <div>
                  <div class="text-2xl font-bold text-gray-900">95%</div>
                  <div class="text-xs text-gray-500">Satisfaction</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Trust Indicators -->
    <div class="bg-gray-50 border-t border-gray-200 py-8">
      <div class="container mx-auto px-4">
        <div class="text-center mb-6">
          <p class="text-gray-600 text-sm">Trusted by sellers worldwide</p>
        </div>
        <div class="flex flex-wrap justify-center items-center gap-8 opacity-60">
          <div class="text-gray-400 font-bold text-lg">Stripe</div>
          
        </div>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import { toast } from "vue3-toastify";
import Footer from "../components/ui/Footer.vue";
import { sentOtp } from "../services/SellersignupService";
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";
import SellerAuthService from "../services/SellerAuthService";

export default {
  components: {Hero,Navbar, Footer },
  name: "Sellersignup",

  data() {
    return {
      email: "",
      emailError: "",
      loading: false
    };
  },
   mounted() {
     const script = document.createElement("script");
    script.src = "https://accounts.google.com/gsi/client";
    script.async = true;
    script.defer = true;
    script.onload = this.initGoogleSignIn;
    document.head.appendChild(script);
    if (SellerAuthService.isAuthenticated()) {
      this.$router.push({ name: "SellerDashboard" });
    }
     if (localStorage.getItem('sellerRememberMe') === 'true') {
      this.rememberMe = true;
    }
  },

  methods: {
    
     async handleGoogleLogin(response) {
    const idToken = response.credential;
    if (!idToken) return;

    try {
      const result = await SellerAuthService.googleLogin(idToken);
      if (result.code === "0") {
        toast.success("Logged in with Google successfully!");
        this.$router.push({ name: "SellerDashboard" });
      } else {
        toast.error(result.message || "Google login failed");
      }
    } catch (error) {
      toast.error("Google login error. Try again.");
    }
  },

  initGoogleSignIn() {
    /* global google */
    google.accounts.id.initialize({
      client_id: "819481703907-espu7bdv7nntjvn3jn0lvjtl1ncpleru.apps.googleusercontent.com",
      callback: this.handleGoogleLogin,
    });

    // Render the Google button
    google.accounts.id.renderButton(
      document.getElementById("googleLoginButton"),
      {
        theme: "outline",
        size: "large",
        width: "100%",
      }
    );
  },
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

/* Smooth transitions */
button, input, a {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}
</style>