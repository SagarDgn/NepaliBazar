<template>
  <div class="min-h-screen bg-white">
    <Hero/>
    <Navbar />
    
    <!-- Custom Notification -->
    <div
      v-if="showNotification"
      class="fixed top-4 right-4 z-50 transform transition-all duration-500"
      :class="notificationClass"
    >
      <div class="flex items-center space-x-3 px-6 py-4 rounded-2xl shadow-2xl backdrop-blur-sm border" :class="notificationStyle">
        <div class="flex-shrink-0">
          <div class="w-8 h-8 rounded-full flex items-center justify-center animate-pulse" :class="notificationIconStyle">
            <svg v-if="notificationType === 'success'" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
            </svg>
            <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
          </div>
        </div>
        <div class="flex-1 min-w-0">
          <p class="text-sm font-semibold" :class="notificationTextStyle">
            {{ notificationMessage }}
          </p>
        </div>
        <button
          @click="hideNotification"
          class="flex-shrink-0 text-gray-400 hover:text-gray-600 transition-colors"
        >
          <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
          </svg>
        </button>
      </div>
    </div>

    <!-- Hero Section -->
    <section class="relative bg-white overflow-hidden border-b border-gray-200">
      <div class="container mx-auto px-4 py-16">
        <div class="text-center max-w-3xl mx-auto">
          <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-800 mb-6">
            Get In Touch
            <span class="text-transparent bg-clip-text bg-gradient-to-r from-green-600 to-orange-600">
              With Us
            </span>
          </h1>
          <p class="text-xl text-gray-600 mb-8 leading-relaxed">
            We'd love to hear from you. Send us a message and we'll respond as soon as possible.
          </p>
        </div>
      </div>
    </section>

    <!-- Main Content -->
    <div class="container mx-auto px-4 py-12 max-w-7xl">
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-12">
        <!-- Contact Form -->
        <div class="bg-white rounded-2xl p-8 shadow-lg border border-gray-200">
          <h2 class="text-2xl font-bold text-gray-800 mb-6">Send us a Message</h2>

          <form @submit.prevent="validateAndSubmit" class="space-y-6">
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <!-- First Name -->
              <div>
                <label for="firstName" class="block text-sm font-medium text-gray-700 mb-2">
                  First Name *
                </label>
                <input
                  v-model="formData.firstName"
                  @blur="validateField('firstName')"
                  @input="clearFieldError('firstName')"
                  type="text"
                  id="firstName"
                  :class="['w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 transition-all duration-200', 
                          errors.firstName ? 'border-red-500' : 'border-gray-300']"
                  placeholder="Your first name"
                >
                <p v-if="errors.firstName" class="text-red-500 text-xs mt-1">{{ errors.firstName }}</p>
              </div>

              <!-- Last Name -->
              <div>
                <label for="lastName" class="block text-sm font-medium text-gray-700 mb-2">
                  Last Name *
                </label>
                <input
                  v-model="formData.lastName"
                  @blur="validateField('lastName')"
                  @input="clearFieldError('lastName')"
                  type="text"
                  id="lastName"
                  :class="['w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 transition-all duration-200', 
                          errors.lastName ? 'border-red-500' : 'border-gray-300']"
                  placeholder="Your last name"
                >
                <p v-if="errors.lastName" class="text-red-500 text-xs mt-1">{{ errors.lastName }}</p>
              </div>
            </div>

            <!-- Email -->
            <div>
              <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
                Email Address *
              </label>
              <input
                v-model="formData.email"
                @blur="validateField('email')"
                @input="clearFieldError('email')"
                type="email"
                id="email"
                :class="['w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 transition-all duration-200', 
                        errors.email ? 'border-red-500' : 'border-gray-300']"
                placeholder="your.email@example.com"
              >
              <p v-if="errors.email" class="text-red-500 text-xs mt-1">{{ errors.email }}</p>
            </div>

            <!-- Phone -->
            <div>
              <label for="phone" class="block text-sm font-medium text-gray-700 mb-2">
                Phone Number
              </label>
              <input
                v-model="formData.phone"
                @blur="validateField('phone')"
                @input="clearFieldError('phone')"
                type="tel"
                id="phone"
                :class="['w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 transition-all duration-200', 
                        errors.phone ? 'border-red-500' : 'border-gray-300']"
                placeholder="+977 98XXXXXXX"
              >
              <p v-if="errors.phone" class="text-red-500 text-xs mt-1">{{ errors.phone }}</p>
            </div>

            <!-- Subject -->
            <div>
              <label for="subject" class="block text-sm font-medium text-gray-700 mb-2">
                Subject *
              </label>
              <select
                v-model="formData.subject"
                @blur="validateField('subject')"
                @change="clearFieldError('subject')"
                id="subject"
                :class="['w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 transition-all duration-200', 
                        errors.subject ? 'border-red-500' : 'border-gray-300']"
              >
                <option value="" disabled>Select a subject</option>
                <option value="general">General Inquiry</option>
                <option value="products">Product Information</option>
                <option value="partnership">Partnership Opportunity</option>
                <option value="support">Customer Support</option>
                <option value="artisan">Become an Artisan</option>
                <option value="other">Other</option>
              </select>
              <p v-if="errors.subject" class="text-red-500 text-xs mt-1">{{ errors.subject }}</p>
            </div>

            <!-- Message -->
            <div>
              <label for="message" class="block text-sm font-medium text-gray-700 mb-2">
                Message *
              </label>
              <textarea
                v-model="formData.message"
                @blur="validateField('message')"
                @input="clearFieldError('message')"
                id="message"
                rows="6"
                :class="['w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 transition-all duration-200', 
                        errors.message ? 'border-red-500' : 'border-gray-300']"
                placeholder="Tell us how we can help you..."
              ></textarea>
              <div class="flex justify-between items-center mt-1">
                <p v-if="errors.message" class="text-red-500 text-xs">{{ errors.message }}</p>
                <p class="text-gray-500 text-xs ml-auto">
                  {{ formData.message.length }}/1000 characters
                </p>
              </div>
            </div>

            <!-- Submit Button -->
            <div>
              <button
                type="submit"
                :disabled="isLoading"
                class="w-full px-8 py-4 bg-gradient-to-r from-green-600 to-orange-600 text-white font-semibold rounded-2xl hover:from-green-700 hover:to-orange-700 transition-all duration-300 transform hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none shadow-lg"
              >
                <span v-if="!isLoading" class="flex items-center justify-center space-x-2">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                  </svg>
                  <span>Send Message</span>
                </span>
                <span v-else class="flex items-center justify-center space-x-2">
                  <svg class="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  <span>Sending...</span>
                </span>
              </button>
            </div>
          </form>
        </div>

        <!-- Contact Information -->
        <div class="space-y-8">
          <!-- Contact Details -->
          <div class="bg-white rounded-2xl p-8 shadow-lg border border-gray-200">
            <h2 class="text-2xl font-bold text-gray-800 mb-6">Contact Information</h2>
            
            <div class="space-y-6">
              <!-- Email -->
              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-green-100 rounded-2xl flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-800 mb-1">Email</h3>
                  <p class="text-gray-600">We'll respond quickly</p>
                  <p class="text-green-600 font-medium">sagardada2535@gmail.com</p>
                </div>
              </div>

              <!-- Phone -->
              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-blue-100 rounded-2xl flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-800 mb-1">Phone</h3>
                  <p class="text-gray-600">Mon to Fri, 9AM to 6PM</p>
                  <p class="text-blue-600 font-medium">+977-9709518369</p>
                </div>
              </div>

              <!-- Address -->
              <div class="flex items-start space-x-4">
                <div class="w-12 h-12 bg-orange-100 rounded-2xl flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-orange-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"/>
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-semibold text-gray-800 mb-1">Address</h3>
                  <p class="text-gray-600">Visit our office</p>
                  <p class="text-orange-600 font-medium">Kathmandu, Nepal</p>
                </div>
              </div>
            </div>
          </div>

          <!-- Why Contact Us -->
          <div class="bg-gradient-to-r from-green-600 to-orange-600 rounded-2xl p-8 text-white">
            <h3 class="text-xl font-bold mb-4">Why Contact Us?</h3>
            <ul class="space-y-3">
              <li class="flex items-center">
                <span class="w-2 h-2 bg-white rounded-full mr-3"></span>
                Get information about our products
              </li>
              <li class="flex items-center">
                <span class="w-2 h-2 bg-white rounded-full mr-3"></span>
                Partner with us as an artisan
              </li>
              <li class="flex items-center">
                <span class="w-2 h-2 bg-white rounded-full mr-3"></span>
                Share your feedback and suggestions
              </li>
              <li class="flex items-center">
                <span class="w-2 h-2 bg-white rounded-full mr-3"></span>
                Get support for your orders
              </li>
            </ul>
          </div>

          <!-- Response Time -->
          <div class="bg-white rounded-2xl p-6 shadow-lg border border-gray-200 text-center">
            <div class="w-16 h-16 bg-green-100 rounded-2xl flex items-center justify-center mx-auto mb-4">
              <span class="text-2xl">⚡</span>
            </div>
            <h3 class="text-lg font-semibold text-gray-800 mb-2">Quick Response</h3>
            <p class="text-gray-600 text-sm">We typically respond within 24 hours</p>
          </div>
        </div>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import Navbar from "../components/ui/Navbar.vue";
import Footer from "../components/ui/Footer.vue";
import Hero from "../components/ui/Hero.vue";

export default {
  name: "ContactUs",
  components: { Navbar, Hero, Footer },
  data() {
    return {
      isLoading: false,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success', // 'success' or 'error'
      notificationTimeout: null,
      formData: {
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        subject: '',
        message: ''
      },
      errors: {
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        subject: '',
        message: ''
      },
      web3formsAccessKey: '60cca8f7-fbf4-4534-9335-41dfe07bf8b0'
    };
  },
  computed: {
    notificationClass() {
      const baseClasses = 'transition-all duration-500 ease-in-out';
      if (this.showNotification) {
        return `${baseClasses} translate-x-0 opacity-100`;
      } else {
        return `${baseClasses} translate-x-full opacity-0`;
      }
    },
    notificationStyle() {
      return this.notificationType === 'success' 
        ? 'bg-green-50 border-green-200 text-green-800' 
        : 'bg-red-50 border-red-200 text-red-800';
    },
    notificationIconStyle() {
      return this.notificationType === 'success' 
        ? 'bg-green-100 text-green-600' 
        : 'bg-red-100 text-red-600';
    },
    notificationTextStyle() {
      return this.notificationType === 'success' 
        ? 'text-green-800' 
        : 'text-red-800';
    }
  },
  methods: {
    showCustomNotification(message, type = 'success') {
      this.notificationMessage = message;
      this.notificationType = type;
      this.showNotification = true;

      // Clear any existing timeout
      if (this.notificationTimeout) {
        clearTimeout(this.notificationTimeout);
      }

      // Auto hide after 5 seconds
      this.notificationTimeout = setTimeout(() => {
        this.hideNotification();
      }, 5000);
    },

    hideNotification() {
      this.showNotification = false;
      if (this.notificationTimeout) {
        clearTimeout(this.notificationTimeout);
        this.notificationTimeout = null;
      }
    },

    validateField(field) {
      this.errors[field] = '';
      
      switch (field) {
        case 'firstName':
          if (!this.formData.firstName.trim()) {
            this.errors.firstName = 'First name is required';
          } else if (this.formData.firstName.trim().length < 2) {
            this.errors.firstName = 'First name must be at least 2 characters';
          } else if (!/^[a-zA-Z\s]+$/.test(this.formData.firstName.trim())) {
            this.errors.firstName = 'First name can only contain letters and spaces';
          }
          break;
          
        case 'lastName':
          if (!this.formData.lastName.trim()) {
            this.errors.lastName = 'Last name is required';
          } else if (this.formData.lastName.trim().length < 2) {
            this.errors.lastName = 'Last name must be at least 2 characters';
          } else if (!/^[a-zA-Z\s]+$/.test(this.formData.lastName.trim())) {
            this.errors.lastName = 'Last name can only contain letters and spaces';
          }
          break;
          
        case 'email':
          if (!this.formData.email.trim()) {
            this.errors.email = 'Email is required';
          } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.formData.email)) {
            this.errors.email = 'Please enter a valid email address';
          }
          break;
          
        case 'phone':
          if (this.formData.phone.trim() && !/^[\+]?[0-9\s\-\(\)]{10,}$/.test(this.formData.phone.replace(/\s/g, ''))) {
            this.errors.phone = 'Please enter a valid phone number';
          }
          break;
          
        case 'subject':
          if (!this.formData.subject) {
            this.errors.subject = 'Please select a subject';
          }
          break;
          
        case 'message':
          if (!this.formData.message.trim()) {
            this.errors.message = 'Message is required';
          } else if (this.formData.message.trim().length < 5) {
            this.errors.message = 'Message must be at least 5 characters';
          } else if (this.formData.message.length > 1000) {
            this.errors.message = 'Message must be less than 1000 characters';
          }
          break;
      }
    },
    
    clearFieldError(field) {
      this.errors[field] = '';
    },
    
    validateForm() {
      // Validate all fields
      Object.keys(this.errors).forEach(field => {
        this.validateField(field);
      });
      
      // Check if there are any errors
      return !Object.values(this.errors).some(error => error !== '');
    },
    
    async validateAndSubmit() {
      if (!this.validateForm()) {
        this.showCustomNotification('Please fll the form before submitting.', 'error');
        return;
      }
      
      await this.submitForm();
    },
    
    async submitForm() {
      this.isLoading = true;

      try {
        const response = await fetch('https://api.web3forms.com/submit', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            access_key: this.web3formsAccessKey,
            subject: `Contact Form: ${this.formData.subject}`,
            name: `${this.formData.firstName} ${this.formData.lastName}`,
            email: this.formData.email,
            phone: this.formData.phone,
            message: this.formData.message,
            from_name: 'NepaliBazar Contact Form',
            'First Name': this.formData.firstName,
            'Last Name': this.formData.lastName,
            'Subject Type': this.formData.subject
          })
        });

        const result = await response.json();

        if (result.success) {
          this.showCustomNotification(' Thank you! Your message has been sent successfully. We\'ll get back to you soon!', 'success');
          this.resetForm();
        } else {
          this.showCustomNotification(' Failed to send message. Please try again later.', 'error');
          console.error('Form submission failed:', result);
        }
      } catch (error) {
        this.showCustomNotification(' Network error. Please check your connection and try again.', 'error');
        console.error('Error submitting form:', error);
      } finally {
        this.isLoading = false;
      }
    },

    resetForm() {
      this.formData = {
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        subject: '',
        message: ''
      };
      // Clear all errors
      Object.keys(this.errors).forEach(key => {
        this.errors[key] = '';
      });
    }
  }
};
</script>

<style scoped>
/* Smooth transitions for all interactive elements */
button, a, input, textarea, select {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Custom shadow for depth */
.shadow-lg {
  box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
}
</style>