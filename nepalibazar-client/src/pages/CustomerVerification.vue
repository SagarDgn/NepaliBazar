<template>
  <div class="min-h-screen ">
    <Hero/>
    <Navbar/>
    <!-- Header -->
   

    <div class="container mx-auto px-4 py-8 max-w-6xl">
      <div class="flex flex-col lg:flex-row items-start justify-between gap-12">
        <!-- Left Side - Form -->
        <div class="lg:w-1/2 w-full max-w-lg mx-auto lg:mx-0">
          <div class="  p-8">
            <!-- Progress Steps -->
            <div class="flex items-center justify-between mb-8">
              <div class="flex items-center">
                <div class="w-8 h-8 bg-green-500 rounded-full flex items-center justify-center text-white text-sm font-bold">
                  ✓
                </div>
                <span class="ml-2 text-sm text-green-600 font-medium">Email Sent</span>
              </div>
              <div class="flex-1 h-1 bg-green-500 mx-4"></div>
              <div class="flex items-center">
                <div class="w-8 h-8 bg-gray-900 rounded-full flex items-center justify-center text-white text-sm font-bold">
                  2
                </div>
                <span class="ml-2 text-sm text-gray-900 font-medium">Complete Profile</span>
              </div>
            </div>

            <!-- Form Header -->
            <div class="mb-8">
              <h1 class="text-2xl font-bold text-gray-900 mb-2">Complete Your Profile</h1>
              <p class="text-gray-600">Just a few more details to get started</p>
              <div class="mt-3 text-sm text-blue-600 bg-blue-50 rounded-lg p-3">
                <span class="font-medium">Verifying:</span> {{ email }}
              </div>
            </div>

            <!-- Form -->
            <form class="space-y-6" @submit.prevent="handleSignup">
              <!-- Username -->
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">
                  Username
                </label>
                <div class="relative">
                  <input
                    v-model="userName"
                    type="text"
                    placeholder="Choose a username"
                    class="w-full px-4 py-3 pl-11 border border-gray-300 rounded-lg focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 outline-none"
                    :class="{ 'border-red-500 focus:ring-red-500': userNameError }"
                    @blur="validateUsername"
                  />
                  <div class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"/>
                    </svg>
                  </div>
                </div>
                <div v-if="userNameError" class="text-red-500 text-sm mt-2 flex items-start">
                  <svg class="w-4 h-4 mr-1 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                  {{ userNameError }}
                </div>
                <div v-else-if="userName && !userNameError" class="text-green-500 text-sm mt-2 flex items-center">
                  <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
                  </svg>
                  Username available
                </div>
              </div>

              <!-- Password -->
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">
                  Password
                </label>
                <div class="relative">
                  <input
                    v-model="password"
                    :type="showPassword ? 'text' : 'password'"
                    placeholder="Create a strong password"
                    class="w-full px-4 py-3 pl-11 pr-10 border border-gray-300 rounded-lg focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 outline-none"
                    :class="{ 'border-red-500 focus:ring-red-500': passwordError }"
                    @input="validatePassword"
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
                
                <!-- Password Strength Indicator -->
                <div v-if="password" class="mt-3">
                  <div class="flex items-center justify-between text-xs text-gray-600 mb-2">
                    <span>Password strength</span>
                    <span :class="passwordStrengthClass">{{ passwordStrengthText }}</span>
                  </div>
                  <div class="w-full bg-gray-200 rounded-full h-2">
                    <div 
                      class="h-2 rounded-full transition-all duration-500"
                      :class="passwordStrengthBar"
                      :style="{ width: passwordStrength + '%' }"
                    ></div>
                  </div>
                </div>

                <div v-if="passwordError" class="text-red-500 text-sm mt-2 flex items-start">
                  <svg class="w-4 h-4 mr-1 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                  {{ passwordError }}
                </div>
              </div>

              <!-- Address -->
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">
                  Shipping Address
                </label>
                <div class="relative">
                  <input
                    v-model="address"
                    type="text"
                    placeholder="Enter your complete address"
                    class="w-full px-4 py-3 pl-11 border border-gray-300 rounded-lg focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 outline-none"
                    :class="{ 'border-red-500 focus:ring-red-500': addressError }"
                    @blur="validateAddress"
                  />
                  <div class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"/>
                    </svg>
                  </div>
                </div>
                <div v-if="addressError" class="text-red-500 text-sm mt-2 flex items-start">
                  <svg class="w-4 h-4 mr-1 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                  {{ addressError }}
                </div>
              </div>

              <!-- OTP -->
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">
                  Verification Code
                </label>
                <div class="relative">
                  <input
                    v-model="otp"
                    type="text"
                    placeholder="Enter 6-digit code"
                    maxlength="6"
                    class="w-full px-4 py-3 pl-11 border border-gray-300 rounded-lg focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 outline-none text-center text-lg font-mono tracking-widest"
                    :class="{ 'border-red-500 focus:ring-red-500': otpError }"
                    @input="validateOtp"
                  />
                  <div class="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 7a2 2 0 012 2m4 0a6 6 0 01-7.743 5.743L11 17H9v2H7v2H4a1 1 0 01-1-1v-2.586a1 1 0 01.293-.707l5.964-5.964A6 6 0 1121 9z"/>
                    </svg>
                  </div>
                </div>
                <div v-if="otpError" class="text-red-500 text-sm mt-2 flex items-start">
                  <svg class="w-4 h-4 mr-1 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                  </svg>
                  {{ otpError }}
                </div>
                <div v-else-if="otp && otp.length === 6" class="text-green-500 text-sm mt-2 flex items-center">
                  <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
                  </svg>
                  Valid OTP format
                </div>
              </div>

              <!-- Terms and Conditions -->
              <div class="flex items-start space-x-3 p-4 bg-gray-50 rounded-lg border border-gray-200">
                <input
                  type="checkbox"
                  id="terms"
                  v-model="termsAccepted"
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
                :disabled="loading || !termsAccepted"
                class="w-full py-3 px-4 bg-black text-white font-semibold rounded-lg shadow-sm transition-all duration-300 disabled:opacity-50 disabled:cursor-not-allowed"
              >
                <div class="flex items-center justify-center space-x-2">
                  <svg v-if="loading" class="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  <span>{{ loading ? "Creating Account..." : "Complete Registration" }}</span>
                </div>
              </button>
            </form>

            <!-- Security Note -->
            <div class="mt-6 p-4 bg-blue-50 rounded-lg border border-blue-200">
              <div class="flex items-start space-x-3">
                <svg class="w-5 h-5 text-blue-600 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
                </svg>
                <p class="text-sm text-blue-700">
                  <strong>Your security is our priority.</strong> All data is encrypted and protected with bank-level security.
                </p>
              </div>
            </div>
          </div>
        </div>

        <!-- Right Side - Benefits -->
        <div class="lg:w-1/2 w-full bg-gray-50">
          <div class=" rounded-xl shadow-sm  p-8 bg-gray-50">
            <h2 class="text-xl font-bold text-gray-900 mb-6">Welcome to NepaliBazar</h2>
            
            <div class="space-y-6 ">
              <!-- Account Benefits -->
              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-2">Faster Checkout</h3>
                  <p class="text-gray-600 text-sm">Save your details for quick and easy purchases. .</p>
                </div>
              </div>

              <!-- <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-2">Order Tracking</h3>
                  <p class="text-gray-600 text-sm">Track your orders in real-time and get notifications for every status update.</p>
                </div>
              </div> -->

              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900 mb-2">Exclusive Offers</h3>
                  <p class="text-gray-600 text-sm">Get access to member-only deals, early sale access, and personalized recommendations.</p>
                </div>
              </div>
            </div>

            <!-- Trust Badges -->
            <div class="mt-8 pt-8 border-t border-gray-200">
              <p class="text-sm text-gray-600 mb-4 text-center">Trusted by millions worldwide</p>
              <div class="flex justify-center space-x-8 opacity-70">
                <div class="text-center">
                  <div class="w-10 h-10 mx-auto mb-2 text-gray-600">
                    <svg fill="currentColor" viewBox="0 0 20 20">
                      <path fill-rule="evenodd" d="M2.166 4.999A11.954 11.954 0 0010 1.944 11.954 11.954 0 0017.834 5c.11.65.166 1.32.166 2.001 0 5.225-3.34 9.67-8 11.317C5.34 16.67 2 12.225 2 7c0-.682.057-1.35.166-2.001zm11.541 3.708a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"/>
                    </svg>
                  </div>
                  <span class="text-xs text-gray-500">Secure</span>
                </div>
                <div class="text-center">
                  <div class="w-10 h-10 mx-auto mb-2 text-gray-600">
                    <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"/>
                    </svg>
                  </div>
                  <span class="text-xs text-gray-500">Verified</span>
                </div>
                <div class="text-center">
                  <div class="w-10 h-10 mx-auto mb-2 text-gray-600">
                    <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"/>
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

    <Footer />
  </div>
</template>

<script>
import { toast } from 'vue3-toastify';
import { signupUser } from '../services/UsersignupService';
import Footer from '../components/ui/Footer.vue';
import Hero from '../components/ui/Hero.vue';
import Navbar from '../components/ui/Navbar.vue';

export default {
  name: "CustomerVerification",
  components: {Hero,Navbar ,Footer },

  data() {
    return {
      email: sessionStorage.getItem("signupEmail") || "",
      userName: "",
      password: "",
      address: "",
      otp: "",
      termsAccepted: false,
      loading: false,
      showPassword: false,

      userNameError: "",
      passwordError: "",
      addressError: "",
      otpError: "",

      passwordStrength: 0,
      passwordStrengthText: "",
      passwordStrengthClass: "",
      passwordStrengthBar: ""
    };
  },

  methods: {
    validateUsername() {
      // const usernamePattern = /^[a-zA-Z0-9_]{3,20}$/;
      const sqlInjectionPattern = /(\b(SELECT|INSERT|DELETE|UPDATE|DROP|ALTER|CREATE|EXEC)\b|--|;)/i;

      if (!this.userName) {
        this.userNameError = "Username is required";
        return false;
      // } else if (!usernamePattern.test(this.userName)) {
      //   this.userNameError = "Username must be 3-20 characters and can only contain letters, numbers, or underscores";
      //   return false;
      } else if (sqlInjectionPattern.test(this.userName)) {
        this.userNameError = "Invalid characters detected";
        return false;
      } else {
        this.userNameError = "";
        return true;
      }
    },

    validatePassword() {
      const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
      const sqlInjectionPattern = /(\b(SELECT|INSERT|DELETE|UPDATE|DROP|ALTER|CREATE|EXEC)\b|--|;)/i;

      // Calculate password strength
      this.calculatePasswordStrength();

      if (!this.password) {
        this.passwordError = "Password is required";
        return false;
      } else if (!passwordPattern.test(this.password)) {
        this.passwordError = "Password must be at least 8 characters with a letter, number, and special character";
        return false;
      } else if (sqlInjectionPattern.test(this.password)) {
        this.passwordError = "Invalid characters detected";
        return false;
      } else {
        this.passwordError = "";
        return true;
      }
    },

    calculatePasswordStrength() {
      let strength = 0;
      
      // Length check
      if (this.password.length >= 8) strength += 25;
      if (this.password.length >= 12) strength += 15;
      
      // Character variety checks
      if (/[A-Z]/.test(this.password)) strength += 15;
      if (/[a-z]/.test(this.password)) strength += 15;
      if (/[0-9]/.test(this.password)) strength += 15;
      if (/[@$!%*#?&]/.test(this.password)) strength += 15;

      this.passwordStrength = Math.min(strength, 100);

      // Set strength text and colors
      if (this.passwordStrength < 40) {
        this.passwordStrengthText = "Weak";
        this.passwordStrengthClass = "text-red-500";
        this.passwordStrengthBar = "bg-red-500";
      } else if (this.passwordStrength < 70) {
        this.passwordStrengthText = "Fair";
        this.passwordStrengthClass = "text-yellow-500";
        this.passwordStrengthBar = "bg-yellow-500";
      } else if (this.passwordStrength < 90) {
        this.passwordStrengthText = "Good";
        this.passwordStrengthClass = "text-blue-500";
        this.passwordStrengthBar = "bg-blue-500";
      } else {
        this.passwordStrengthText = "Strong";
        this.passwordStrengthClass = "text-green-500";
        this.passwordStrengthBar = "bg-green-500";
      }
    },

    validateAddress() {
      const addressPattern = /^[a-zA-Z0-9\s,.'-]{5,100}$/;
      const sqlInjectionPattern = /(\b(SELECT|INSERT|DELETE|UPDATE|DROP|ALTER|CREATE|EXEC)\b|--|;)/i;

      if (!this.address) {
        this.addressError = "Address is required";
        return false;
      } else if (!addressPattern.test(this.address)) {
        this.addressError = "Please enter a valid address (5-100 characters)";
        return false;
      } else if (sqlInjectionPattern.test(this.address)) {
        this.addressError = "Invalid characters detected";
        return false;
      } else {
        this.addressError = "";
        return true;
      }
    },

    validateOtp() {
      const otpPattern = /^[0-9]{6}$/;
      const sqlInjectionPattern = /(\b(SELECT|INSERT|DELETE|UPDATE|DROP|ALTER|CREATE|EXEC)\b|--|;)/i;

      if (!this.otp) {
        this.otpError = "OTP is required";
        return false;
      } else if (!otpPattern.test(this.otp)) {
        this.otpError = "OTP must be exactly 6 digits";
        return false;
      } else if (sqlInjectionPattern.test(this.otp)) {
        this.otpError = "Invalid characters detected";
        return false;
      } else {
        this.otpError = "";
        return true;
      }
    },

    validateForm() {
      const validations = [
        this.validateUsername(),
        this.validatePassword(),
        this.validateAddress(),
        this.validateOtp()
      ];

      if (!this.termsAccepted) {
        toast.error("Please accept the Terms of Service and Privacy Policy");
        return false;
      }

      return validations.every(valid => valid);
    },

    async handleSignup() {
      if (!this.validateForm()) return;
      this.loading = true;

      try {
        const userData = {
          emailPhone: this.email,
          userName: this.userName,
          password: this.password,
          address: this.address,
          otp: this.otp,
        };

        const response = await signupUser(userData);

        if (response.data.code === "0") {
          toast.success("Account created successfully! Redirecting to login...");
          sessionStorage.removeItem("signupEmail");
          setTimeout(() => {
            this.$router.push({ name: "LoginUser" });
          }, 2000);
        } else {
          toast.error(response.data.message || "Registration failed. Please try again.");
        }
      } catch (error) {
        console.error("Signup failed", error);
        toast.error(error.response?.data?.message || "Unable to complete registration. Please try again.");
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