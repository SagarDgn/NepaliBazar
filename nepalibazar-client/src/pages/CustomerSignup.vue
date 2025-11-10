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
              <h1 class="text-2xl font-bold text-gray-900 mb-2">Create your account</h1>
              <p class="text-gray-600">Join thousands of shoppers worldwide</p>
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
                  Email address
                </label>
                <input
                  v-model="email"
                  id="email"
                  type="email"
                  placeholder="Enter your email"
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
              <div class="flex items-start space-x-3">
                <input
                  type="checkbox"
                  id="terms"
                  class="mt-1 w-4 h-4 text-gray-900 border-gray-300 rounded focus:ring-gray-900"
                />
                <label for="terms" class="text-sm text-gray-600">
                  I agree to the 
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
                  <span>{{ loading ? "Creating Account..." : "Create Account" }}</span>
                </div>
              </button>

              <!-- Login Redirect -->
              <div class="text-center pt-4 border-t border-gray-200">
                <p class="text-gray-600 text-sm">
                  Already have an account?
                  <router-link 
                    to="/loginuser" 
                    class="text-gray-900 hover:text-gray-700 font-semibold transition-colors duration-200 ml-1 hover:underline"
                  >
                    Sign In
                  </router-link>
                </p>
              </div>
            </form>
          </div>
        </div>

        <!-- Right Side - Benefits -->
        <div class="lg:w-1/2 w-full bg-gray-50 rounded-2xl p-8 lg:p-12">
          <div class="max-w-md mx-auto">
            <h2 class="text-2xl font-bold text-gray-900 mb-8">Why join us?</h2>
            
            <div class="space-y-6">
              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Fast & Free Shipping</h3>
                  <p class="text-gray-600 text-sm">Free delivery on orders. Enjoy quick shipping worldwide.</p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Secure Shopping</h3>
                  <p class="text-gray-600 text-sm">Your data is protected with bank-level security encryption.</p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Exclusive Member Deals</h3>
                  <p class="text-gray-600 text-sm">Get access to special discounts and early sale access.</p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18.364 5.636l-3.536 3.536m0 5.656l3.536 3.536M9.172 9.172L5.636 5.636m3.536 9.192L5.636 18.364M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Easy Returns</h3>
                  <p class="text-gray-600 text-sm">30-day return policy. No questions asked.</p>
                </div>
              </div>
            </div>

            <!-- Testimonial -->
            
          </div>
        </div>
      </div>
    </div>

    <!-- Trust Indicators -->
    <div class="bg-gray-50 border-t border-gray-200 py-8">
      <div class="container mx-auto px-4">
        <div class="text-center mb-6">
          <p class="text-gray-600 text-sm">Trusted by millions of shoppers worldwide</p>
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
import { sentOtp } from "../services/UsersignupService";
import Navbar from "../components/ui/Navbar.vue";
import Hero from "../components/ui/Hero.vue";
import UserAuthService from "../services/UserAuthService";

export default {
  components: {Navbar,Hero ,Footer },
  name: "CustomerSignup",

  data() {
    return {
      email: "",
      emailError: "",
      loading: false,
    };
  },

  methods: {

     async handleGoogleLogin(response) {
    const idToken = response.credential;
    if (!idToken) return;

    try {
      const result = await UserAuthService.googleLogin(idToken);
      if (result.code === "0") {
        toast.success("Logged in with Google successfully!");
        this.$router.push({ name: "Home" });
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

    // Optional: automatically prompt login
    // google.accounts.id.prompt();
  },
    isValidGmail(email) {
      const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
      return gmailRegex.test(email);
    },

    async handleSentOtp() {
      if (!this.email) {
        this.emailError = "Email is required";
        return;
      }

      if (!this.isValidGmail(this.email)) {
        this.emailError = "Please enter a valid Gmail address";
        return;
      }

      this.emailError = ""; 
      this.loading = true;

      try {
        const response = await sentOtp({ emailPhone: this.email });
        if (response.data.code === "0") {
          toast.success("OTP sent successfully! Check your email.", { autoClose: 3000 });
          sessionStorage.setItem("signupEmail", this.email);
          this.$router.push({ name: "CustomerVerification" });
        } else {
          toast.error(response.data.message || "This email is already registered!");
        }
      } catch (error) {
        console.error("Signup error: ", error);
        toast.error(error.response?.data?.message || "Unable to send OTP. Please try again.");
      } finally {
        this.loading = false;
      }
    },
  },
   mounted() {
    const script = document.createElement("script");
    script.src = "https://accounts.google.com/gsi/client";
    script.async = true;
    script.defer = true;
    script.onload = this.initGoogleSignIn;
    document.head.appendChild(script);
    
   
    
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