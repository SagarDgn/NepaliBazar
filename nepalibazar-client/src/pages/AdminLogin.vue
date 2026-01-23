<template>
  <div class="min-h-screen bg-white flex items-center justify-center px-4">
    <div class="w-full max-w-md">
      <!-- Login Card -->
      <div class="border border-gray-300 p-8">
        <!-- Header -->
        <div class="text-center mb-8">
          <div class="w-12 h-12 bg-black flex items-center justify-center mx-auto mb-4">
            <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"/>
            </svg>
          </div>
          <h1 class="text-2xl font-bold text-black mb-2">Admin Login</h1>
          <p class="text-gray-600 text-sm">Nepalibazar Management System</p>
        </div>

        <!-- Login Form -->
        <form class="space-y-6" @submit.prevent="handleLogin">
          <!-- Username Input -->
          <div>
            <label class="block text-sm font-medium text-black mb-2">Username</label>
            <input
              v-model="loginData.userName"
              type="text"
              required
              placeholder="Enter admin username"
              class="w-full px-4 py-3 border border-gray-400 text-black placeholder-gray-500 focus:outline-none focus:border-black focus:ring-1 focus:ring-black transition-colors"
              :class="{'bg-gray-50 cursor-not-allowed': loading}"
              :disabled="loading"
              @input="clearError"
            />
          </div>

          <!-- Password Input with Show/Hide -->
          <div>
            <div class="flex justify-between items-center mb-2">
              <label class="block text-sm font-medium text-black">Password</label>
            </div>
            <div class="relative">
              <input
                v-model="loginData.password"
                :type="showPassword ? 'text' : 'password'"
                required
                placeholder="Enter password"
                class="w-full px-4 py-3 border border-gray-400 text-black placeholder-gray-500 focus:outline-none focus:border-black focus:ring-1 focus:ring-black transition-colors pr-10"
                :class="{'bg-gray-50 cursor-not-allowed': loading}"
                :disabled="loading"
                @input="clearError"
              />
              <!-- Show/Hide Password Button -->
              <button
                type="button"
                @click="togglePasswordVisibility"
                class="absolute inset-y-0 right-0 flex items-center pr-3 text-gray-600 hover:text-black focus:outline-none"
                :disabled="loading"
              >
                <svg v-if="showPassword" class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L6.59 6.59m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21"/>
                </svg>
                <svg v-else class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"/>
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"/>
                </svg>
              </button>
            </div>
          </div>

          <!-- Remember Me & Forgot Password -->
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <input
                v-model="rememberMe"
                id="remember"
                type="checkbox"
                class="h-4 w-4 border-gray-400 text-black focus:ring-black focus:ring-offset-0 rounded"
                :disabled="loading"
              />
              <label for="remember" class="ml-2 text-sm text-gray-700">Remember me</label>
            </div>
            
          </div>

          <!-- Error Message -->
          <div v-if="loginError" class="p-3 border border-gray-400 bg-gray-50 text-black text-sm rounded">
            <div class="flex items-start">
              <svg class="w-4 h-4 mr-2 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
              </svg>
              <span>{{ loginError }}</span>
            </div>
          </div>

          <!-- Success Message -->
          <div v-if="loginSuccess" class="p-3 border border-gray-400 bg-gray-50 text-black text-sm rounded">
            <div class="flex items-start">
              <svg class="w-4 h-4 mr-2 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
              </svg>
              <span>{{ loginSuccess }}</span>
            </div>
          </div>

          <!-- Login Button -->
          <button
            type="submit"
            :disabled="loading || !loginData.userName.trim() || !loginData.password.trim()"
            class="w-full py-3 bg-black text-white font-medium hover:bg-gray-900 transition-colors disabled:opacity-50 disabled:cursor-not-allowed focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-black rounded"
          >
            <div class="flex items-center justify-center">
              <svg v-if="loading" class="animate-spin h-5 w-5 mr-2" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              <span>{{ loading ? 'Signing in...' : 'Sign In' }}</span>
            </div>
          </button>
        </form>

        <!-- Footer -->
        <div class="mt-8 pt-6 border-t border-gray-300">
          <p class="text-xs text-center text-gray-500">© {{Date()}}Nepalibazar. Admin Access Only.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AdminLogin from '../services/AdminLogin';

export default {
  name: "AdminLoginPage",
  
  data() {
    return {
      loginData: {
        userName: '',
        password: ''
      },
      rememberMe: false,
      showPassword: false,
      loading: false,
      loginError: '',
      loginSuccess: ''
    }
  },

  mounted() {
    // Check if already authenticated
    if (AdminLogin.isAuthenticated && AdminLogin.isAuthenticated()) {
      this.$router.push({ name: 'AdminDashboard' })
    }
    
    // Load remembered username
    if (AdminLogin.getRememberedUser) {
      const rememberedUser = AdminLogin.getRememberedUser()
      if (rememberedUser) {
        this.loginData.userName = rememberedUser
        this.rememberMe = true
      }
    }
  },

  methods: {
    togglePasswordVisibility() {
      if (!this.loading) {
        this.showPassword = !this.showPassword;
      }
    },

    clearError() {
      this.loginError = '';
      this.loginSuccess = '';
    },

    handleForgotPassword() {
      this.loginError = '';
      this.loginSuccess = 'Password reset feature coming soon.';
      setTimeout(() => {
        this.loginSuccess = '';
      }, 3000);
    },

    async handleLogin() {
      // Basic validation
      if (!this.loginData.userName.trim()) {
        this.loginError = 'Username is required';
        return;
      }

      if (!this.loginData.password.trim()) {
        this.loginError = 'Password is required';
        return;
      }

      this.loginError = '';
      this.loginSuccess = '';
      this.loading = true;

      try {
        const loginPayload = {
          userName: this.loginData.userName.trim(),
          password: this.loginData.password.trim(),
          rememberMe: this.rememberMe
        }

        const result = await AdminLogin.login(loginPayload);

        if (result && result.code === "0") {
          this.loginSuccess = 'Login successful! Redirecting...';
          
          // Redirect to dashboard after 1.5 seconds
          setTimeout(() => {
            this.$router.push({ name: 'AdminDashboard' })
          }, 1500);
        } else {
          this.loginError = (result && result.message) || "Invalid username or password";
        }
      } catch (error) {
        console.error('Login error:', error);
        this.loginError = error.message || 'Login failed. Please try again.';
      } finally {
        this.loading = false;
      }
    }
  }
}
</script>

<style scoped>
/* Custom spinner animation */
@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

.animate-spin {
  animation: spin 1s linear infinite;
}

/* Password toggle button focus style */
button:focus {
  outline: 2px solid transparent;
  outline-offset: 2px;
}

/* Smooth transitions */
input, button {
  transition: all 0.2s ease;
}

/* Input focus enhancement */
input:focus {
  box-shadow: 0 0 0 3px rgba(0, 0, 0, 0.1);
}
</style>