<template>
  <div class="min-h-screen bg-white">
    <!-- Header -->
    <Hero />
    <Navbar />

    <div class="container mx-auto px-4 py-8 max-w-6xl">
      <div class="flex flex-col lg:flex-row items-start justify-between gap-12">
        <!-- Left Side - Form -->
        <div class="lg:w-1/2 w-full max-w-md mx-auto lg:mx-0">
          <div class="py-8">
            <!-- Form Header -->
            <div class="mb-8">
              <h1 class="text-2xl font-bold text-gray-900 mb-2">
                Welcome Back
              </h1>
              <p class="text-gray-600">
                Sign in to your account to continue shopping
              </p>
            </div>

            <!-- Quick Stats -->
            <div class="bg-gray-50 rounded-lg p-4 border border-gray-200 mb-6">
              <div class="flex items-center justify-between text-sm">
                <div class="text-center">
                  <div class="text-gray-900 font-bold">99.9%</div>
                  <div class="text-gray-500 text-xs">Uptime</div>
                </div>
                <div class="text-center">
                  <div class="text-gray-900 font-bold">24/7</div>
                  <div class="text-gray-500 text-xs">Support</div>
                </div>
              </div>
            </div>

            <!-- Social Login -->
            <div class="space-y-3 mb-6">
              <div id="googleLoginButton"></div>

            
            </div>

            <!-- Divider -->
            <div class="relative mb-6">
              <div class="absolute inset-0 flex items-center">
                <div class="w-full border-t border-gray-300"></div>
              </div>
              <div class="relative flex justify-center text-sm">
                <span class="px-2 bg-white text-gray-500"
                  >Or continue with email</span
                >
              </div>
            </div>

            <!-- Form -->
            <form class="space-y-6" @submit.prevent="handleLogin">
              <!-- Email Input -->
              <div>
                <label
                  for="email"
                  class="block text-sm font-medium text-gray-700 mb-2"
                >
                  Email address
                </label>
                <input
                  v-model="email"
                  id="email"
                  type="email"
                  placeholder="Enter your email"
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 outline-none"
                  :class="{
                    'border-red-500 focus:ring-red-500 focus:border-red-500':
                      emailError,
                  }"
                  @blur="validateEmail"
                />
                <p
                  v-if="emailError"
                  class="text-red-500 text-sm mt-2 flex items-center"
                >
                  <svg
                    class="w-4 h-4 mr-1"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                    />
                  </svg>
                  {{ emailError }}
                </p>
              </div>

              <!-- Password Input -->
              <div>
                <div class="flex items-center justify-between mb-2">
                  <label
                    for="password"
                    class="block text-sm font-medium text-gray-700"
                  >
                    Password
                  </label>
                  <router-link
                    to="/forgot-password"
                    class="text-sm text-gray-600 hover:text-gray-900 hover:underline transition-colors"
                  >
                    Forgot password?
                  </router-link>
                </div>
                <div class="relative">
                  <input
                    v-model="password"
                    id="password"
                    :type="showPassword ? 'text' : 'password'"
                    placeholder="Enter your password"
                    class="w-full px-4 py-3 pr-10 border border-gray-300 rounded-lg focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 outline-none"
                    :class="{
                      'border-red-500 focus:ring-red-500': passwordError,
                    }"
                    @blur="validatePassword"
                  />
                  <button
                    type="button"
                    @click="showPassword = !showPassword"
                    class="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600 transition-colors"
                  >
                    <svg
                      class="w-5 h-5"
                      fill="none"
                      stroke="currentColor"
                      viewBox="0 0 24 24"
                    >
                      <path
                        v-if="showPassword"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                      />
                      <path
                        v-if="showPassword"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"
                      />
                      <path
                        v-if="!showPassword"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L6.59 6.59m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21"
                      />
                    </svg>
                  </button>
                </div>
                <p
                  v-if="passwordError"
                  class="text-red-500 text-sm mt-2 flex items-center"
                >
                  <svg
                    class="w-4 h-4 mr-1"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                    />
                  </svg>
                  {{ passwordError }}
                </p>
              </div>

              <!-- Remember Me -->

              <!-- Submit Button -->
              <button
                type="submit"
                :disabled="loading"
                class="w-full py-3 px-4 bg-black text-white font-semibold rounded-lg shadow-sm transition-all duration-300 disabled:opacity-50 disabled:cursor-not-allowed"
              >
                <div class="flex items-center justify-center space-x-2">
                  <svg
                    v-if="loading"
                    class="animate-spin h-5 w-5 text-white"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                  >
                    <circle
                      class="opacity-25"
                      cx="12"
                      cy="12"
                      r="10"
                      stroke="currentColor"
                      stroke-width="4"
                    ></circle>
                    <path
                      class="opacity-75"
                      fill="currentColor"
                      d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
                    ></path>
                  </svg>
                  <span>{{
                    loading ? "Signing In..." : "Sign In to Your Account"
                  }}</span>
                </div>
              </button>

              <!-- Sign Up Redirect -->
              <div class="text-center pt-4 border-t border-gray-200">
                <p class="text-gray-600 text-sm">
                  Don't have an account?
                  <router-link
                    to="/usersignup"
                    class="text-gray-900 hover:text-gray-700 font-semibold transition-colors duration-200 ml-1 hover:underline"
                  >
                    Create new account
                  </router-link>
                </p>
              </div>
            </form>

            <!-- Security Assurance -->
            <div
              class="mt-6 p-4 bg-green-50 rounded-lg border border-green-200"
            >
              <div class="flex items-start space-x-3">
                <svg
                  class="w-5 h-5 text-green-600 flex-shrink-0 mt-0.5"
                  fill="none"
                  stroke="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"
                  />
                </svg>
                <p class="text-sm text-green-700">
                  <strong>Secure login.</strong> Your information is protected
                  with bank-level encryption.
                </p>
              </div>
            </div>
          </div>
        </div>

        <!-- Right Side - Benefits -->
        <div class="lg:w-1/2 w-full bg-gray-50 rounded-2xl p-8 lg:p-12">
          <div class="max-w-md mx-auto">
            <h2 class="text-2xl font-bold text-gray-900 mb-8">Why Sign In?</h2>

            <div class="space-y-6">
              <div class="flex items-start space-x-4">
                <div
                  class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0"
                >
                  <svg
                    class="w-5 h-5 text-gray-700"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"
                    />
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">
                    Personalized Experience
                  </h3>
                  <p class="text-gray-600 text-sm">
                    Get recommendations based on your shopping preferences and
                    history.
                  </p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div
                  class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0"
                >
                  <svg
                    class="w-5 h-5 text-gray-700"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                    />
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">
                    Exclusive Member Deals
                  </h3>
                  <p class="text-gray-600 text-sm">
                    Access special discounts, early sale notifications, and
                    member-only offers.
                  </p>
                </div>
              </div>

              <div class="flex items-start space-x-4">
                <div
                  class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0"
                >
                  <svg
                    class="w-5 h-5 text-gray-700"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M18.364 5.636l-3.536 3.536m0 5.656l3.536 3.536M9.172 9.172L5.636 5.636m3.536 9.192L5.636 18.364M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                    />
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-1">Easy Returns</h3>
                  <p class="text-gray-600 text-sm">
                    Quick and hassle-free returns with your saved preferences
                    and address.
                  </p>
                </div>
              </div>
            </div>

            <!-- Trust Badges -->
            <div class="mt-8 pt-8 border-t border-gray-200">
              <p class="text-sm text-gray-600 mb-4 text-center">
                Trusted by millions worldwide
              </p>
              <div class="flex justify-center space-x-8 opacity-70">
                <div class="text-center">
                  <div class="w-10 h-10 mx-auto mb-2 text-gray-600">
                    <svg fill="currentColor" viewBox="0 0 20 20">
                      <path
                        fill-rule="evenodd"
                        d="M2.166 4.999A11.954 11.954 0 0010 1.944 11.954 11.954 0 0017.834 5c.11.65.166 1.32.166 2.001 0 5.225-3.34 9.67-8 11.317C5.34 16.67 2 12.225 2 7c0-.682.057-1.35.166-2.001zm11.541 3.708a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"
                        clip-rule="evenodd"
                      />
                    </svg>
                  </div>
                  <span class="text-xs text-gray-500">Secure</span>
                </div>
                <div class="text-center">
                  <div class="w-10 h-10 mx-auto mb-2 text-gray-600">
                    <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"
                      />
                    </svg>
                  </div>
                  <span class="text-xs text-gray-500">Verified</span>
                </div>
                <div class="text-center">
                  <div class="w-10 h-10 mx-auto mb-2 text-gray-600">
                    <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"
                      />
                    </svg>
                  </div>
                  <span class="text-xs text-gray-500">Encrypted</span>
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
          <p class="text-gray-600 text-sm">
            Trusted by millions of shoppers worldwide
          </p>
        </div>
        <div
          class="flex flex-wrap justify-center items-center gap-8 opacity-60"
        >
          <div class="text-gray-400 font-bold text-lg">Stripe</div>
        </div>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import { toast } from "vue3-toastify";
import UserAuthService from "../services/UserAuthService";
import Footer from "../components/ui/Footer.vue";
import Hero from "../components/ui/Hero.vue";
import Navbar from "../components/ui/Navbar.vue";

export default {
  name: "LoginUser",
  components: { Hero, Navbar, Footer },

  data() {
    return {
      email: "",
      password: "",
      rememberMe: false,
      loading: false,
      showPassword: false,

      emailError: "",
      passwordError: "",
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

    validateEmail() {
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      const sqlInjectionPattern =
        /(\b(SELECT|INSERT|DELETE|UPDATE|DROP|ALTER|CREATE|EXEC)\b|--|;)/i;

      if (!this.email) {
        this.emailError = "Email is required for login";
        return false;
      } else if (!emailPattern.test(this.email)) {
        this.emailError = "Please enter a valid email address";
        return false;
      } else if (sqlInjectionPattern.test(this.email)) {
        this.emailError = "Invalid characters detected";
        return false;
      } else {
        this.emailError = "";
        return true;
      }
    },

    validatePassword() {
      const sqlInjectionPattern =
        /(\b(SELECT|INSERT|DELETE|UPDATE|DROP|ALTER|CREATE|EXEC)\b|--|;)/i;

      if (!this.password) {
        this.passwordError = "Password is required for login";
        return false;
      } else if (sqlInjectionPattern.test(this.password)) {
        this.passwordError = "Invalid characters detected";
        return false;
      } else {
        this.passwordError = "";
        return true;
      }
    },

    isValidate() {
      const validations = [this.validateEmail(), this.validatePassword()];

      return validations.every((valid) => valid);
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

          // Store remember me preference
          if (this.rememberMe) {
            localStorage.setItem("rememberMe", "true");
          }

          setTimeout(() => {
            this.$router.push({ name: "Home" });
          }, 1000);
        } else if (response.code === "-1") {
          toast.error(response.message || "Invalid username or password");
        } else {
          toast.error(response.message || "Login failed. Please try again.");
        }
      } catch (error) {
        console.log("Login error:", error);

        if (error.response && error.response.data) {
          const backendData = error.response.data;

          if (backendData.code === "-1") {
            toast.error(backendData.message || "Invalid username or password");
          } else {
            toast.error(
              backendData.message || "Login failed. Please try again."
            );
          }
        } else if (error.request) {
          toast.error(
            "Unable to connect to server. Please check your internet connection."
          );
        } else {
          toast.error("An unexpected error occurred. Please try again.");
        }
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
    // Check if user wanted to be remembered
    if (localStorage.getItem("rememberMe") === "true") {
      this.rememberMe = true;
    }
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
button,
input,
a {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}
</style>
