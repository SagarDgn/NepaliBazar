<template>
  <div class="min-h-screen bg-gradient-to-br ">
    <Hero />
    <Navbar />
    
    
    <div class="container mx-auto px-4 py-8 max-w-6xl">
      <div class="flex flex-col lg:flex-row items-center justify-center gap-8 lg:gap-16">
        <!-- Left Side - Image -->
        <div class="lg:w-1/2 flex justify-center">
          <div class="relative">
            <img
              src="../assets/signupPic.PNG"
              alt="Welcome back to our platform"
              class="rounded-3xl shadow-2xl w-full max-w-lg transform hover:scale-105 transition-transform duration-500"
            />
            <!-- Floating decoration elements -->
            <div class="absolute -top-4 -left-4 w-24 h-24 bg-blue-200 rounded-full opacity-50 animate-pulse"></div>
            <div class="absolute -bottom-4 -right-4 w-16 h-16 bg-purple-200 rounded-full opacity-50 animate-pulse delay-1000"></div>
            <!-- Welcome Back Badge -->
            <div class="absolute top-4 right-4 bg-white/90 backdrop-blur-sm rounded-2xl p-3 shadow-lg">
              <div class="flex items-center space-x-2 text-red-600">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"/>
                </svg>
                <span class="text-sm font-semibold">Welcome Back</span>
              </div>
            </div>
          </div>
        </div>

        <!-- Right Side - Login Form -->
        <div class="lg:w-1/2 flex justify-center">
          <div class="bg-white/80 backdrop-blur-sm rounded-3xl shadow-2xl p-8 w-full max-w-md border border-white/20">
            <!-- Header -->
            <div class="text-center mb-8">
              <h1 class="text-3xl font-bold bg-gradient-to-r from-red-600 to-orange-600 bg-clip-text text-transparent mb-2">
                Welcome Back
              </h1>
              <p class="text-gray-600">Sign in to your account to continue</p>
            </div>

            <!-- Quick Stats -->
            <div class="bg-blue-50/50 rounded-2xl p-4 border border-blue-100 mb-6">
              <div class="flex items-center justify-between text-sm">
                
                <div class="text-center">
                  <div class="text-blue-600 font-bold">99.9%</div>
                  <div class="text-blue-500 text-xs">Uptime</div>
                </div>
                <div class="text-center">
                  <div class="text-blue-600 font-bold">24/7</div>
                  <div class="text-blue-500 text-xs">Support</div>
                </div>
              </div>
            </div>

            <!-- Form -->
            <form class="space-y-6" @submit.prevent="handleLogin">
              <!-- Email Input -->
              <div class="space-y-2">
                <label class="text-sm font-medium text-gray-700 flex items-center">
                  <svg class="w-4 h-4 mr-2 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                  </svg>
                  Email Address
                </label>
                <div class="relative">
                  <input
                    v-model="email"
                    type="email"
                    placeholder="Enter your email"
                    class="w-full px-4 py-3 pl-11 border border-gray-300 rounded-2xl focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 outline-none bg-white/50"
                    :class="{ 'border-red-500 focus:ring-red-500': emailError }"
                  />
                  <div class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"/>
                    </svg>
                  </div>
                </div>
                <p v-if="emailError" class="text-red-500 text-sm flex items-center mt-1">
                  <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                  {{ emailError }}
                </p>
              </div>

              <!-- Password Input -->
              <div class="space-y-2">
                <div class="flex items-center justify-between">
                  <label class="text-sm font-medium text-gray-700 flex items-center">
                    <svg class="w-4 h-4 mr-2 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"/>
                    </svg>
                    Password
                  </label>
                  <router-link 
                    to="/forgot-password" 
                    class="text-xs text-blue-600 hover:text-blue-700 hover:underline transition-colors"
                  >
                  
                  </router-link>
                </div>
                <div class="relative">
                  <input
                    v-model="password"
                    :type="showPassword ? 'text' : 'password'"
                    placeholder="Enter your password"
                    class="w-full px-4 py-3 pl-11 pr-10 border border-gray-300 rounded-2xl focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 outline-none bg-white/50"
                    :class="{ 'border-red-500 focus:ring-red-500': passwordError }"
                  />
                  <div class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"/>
                    </svg>
                  </div>
                  <button
                    type="button"
                    @click="showPassword = !showPassword"
                    class="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600 transition-colors"
                  >
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path v-if="showPassword" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
                      <path v-if="showPassword" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"/>
                      <path v-if="!showPassword" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L6.59 6.59m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21"/>
                    </svg>
                  </button>
                </div>
                <p v-if="passwordError" class="text-red-500 text-sm flex items-center mt-1">
                  <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                  {{ passwordError }}
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
                  <span>{{ loading ? " In..." : "Log In to Your Account" }}</span>
                </div>
              </button>

              <!-- Social Login (Optional) -->
             
              
            </form>

            <!-- Sign Up Redirect -->
            <div class="text-center mt-6 pt-6 border-t border-gray-200">
              <p class="text-gray-600 text-sm">
                Don't have an account?
                <router-link 
                  to="/usersignup" 
                  class="text-blue-600 hover:text-blue-700 font-semibold transition-colors duration-200 ml-1 hover:underline"
                >
                  Create new account
                </router-link>
              </p>
            </div>

            <!-- Security Assurance -->
            <div class="mt-4 text-center">
              <p class="text-xs text-gray-500 flex items-center justify-center">
                <svg class="w-4 h-4 mr-1 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
                </svg>
                Your login is secure and encrypted
              </p>
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
import UserAuthService from "../services/UserAuthService";
import Footer from "../components/ui/Footer.vue";

export default {
  name: "LoginUser",
  components: { Hero, Navbar, Footer },

  data() {
    return {
      email: "",
      password: "",
      loading: false,
      showPassword: false,

      emailError: "",
      passwordError: "",
    };
  },

  methods: {
    isValidate() {
      let valid = true;

      if (!this.email) {
        this.emailError = "Email is required for login";
        valid = false;
      } else {
        this.emailError = "";
      }

      if (!this.password) {
        this.passwordError = "Password is required for login";
        valid = false;
      } else {
        this.passwordError = "";
      }
      return valid;
    },

    async handleLogin() {
  if (!this.isValidate()) return;

  this.loading = true;

  try {
    const userData = {
      emailPhone: this.email,
      password: this.password,
    };

    const response = await UserAuthService.login(userData);
    
    if (response.code === "0") {
      toast.success("Welcome back! Login successful");
      setTimeout(() => {
        this.$router.push({ name: "Home" });
      }, 1000);
    } 
    else if (response.code === "-1") {
     
      toast.error(response.message || "Invalid username or password");
    }
    else {
     
      toast.error(response.message || "Login failed. Please try again.");
    }
  } catch (error) {
    console.log("Login error:", error);
    
    
    if (error.response && error.response.data) {
      
      const backendData = error.response.data;
      
      if (backendData.code === "-1") {
        toast.error(backendData.message || "Invalid username or password");
      } else {
        toast.error(backendData.message || "Login failed. Please try again.");
      }
    } 
    else if (error.request) {
   
      toast.error("Unable to connect to server. Please check your internet connection.");
    }
    else {
     
      toast.error("An unexpected error occurred. Please try again.");
    }
  } finally {
    this.loading = false;
  }
},
  },
};
</script>

<style scoped>
/* Custom styles for login page */
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

/* Smooth transitions for form elements */
input, button {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}
</style>