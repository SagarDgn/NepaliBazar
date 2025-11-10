<template>
  <div class="min-h-screen bg-gray-50">
    <Hero/>
    <Navbar />
    
    <!-- Custom Notification -->
    <div
      v-if="showNotification"
      class="fixed top-4 right-4 z-50 transform transition-all duration-500"
      :class="notificationClass"
    >
      <div class="flex items-center space-x-3 px-4 py-3 rounded-lg shadow-lg bg-white border border-gray-200">
        <div class="flex-shrink-0">
          <div class="w-6 h-6 rounded-full flex items-center justify-center" :class="notificationIconStyle">
            <svg v-if="notificationType === 'success'" class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
            </svg>
            <svg v-else class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
          </div>
        </div>
        <div class="flex-1 min-w-0">
          <p class="text-sm font-medium" :class="notificationTextStyle">
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
    <section class="bg-white border-b border-gray-200">
      <div class="container mx-auto px-4 py-12">
        <div class="text-center max-w-2xl mx-auto">
          <h1 class="text-3xl font-bold text-gray-900 mb-4">Contact Us</h1>
          <p class="text-gray-600 text-lg">
            Have questions? We're here to help. Send us a message and we'll respond as soon as possible.
          </p>
        </div>
      </div>
    </section>

    <!-- Main Content -->
    <div class="container mx-auto px-4 py-12 max-w-6xl">
      <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <!-- Contact Information -->
        <div class="lg:col-span-1 space-y-6">
          <!-- Contact Details Card -->
          <div class="bg-white rounded-lg p-6 shadow-sm border border-gray-200">
            <h2 class="text-lg font-semibold text-gray-900 mb-6">Contact Information</h2>
            
            <div class="space-y-5">
              <!-- Email -->
              <div class="flex items-start space-x-3">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-medium text-gray-900 text-sm mb-1">Email</h3>
                  <p class="text-gray-600 text-sm">sagardada2535@gmail.com</p>
                </div>
              </div>

              <!-- Phone -->
              <div class="flex items-start space-x-3">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-medium text-gray-900 text-sm mb-1">Phone</h3>
                  <p class="text-gray-600 text-sm">+977-9709518369</p>
                </div>
              </div>

              <!-- Address -->
              <div class="flex items-start space-x-3">
                <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-5 h-5 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"/>
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"/>
                  </svg>
                </div>
                <div>
                  <h3 class="font-medium text-gray-900 text-sm mb-1">Address</h3>
                  <p class="text-gray-600 text-sm">Kathmandu, Nepal</p>
                </div>
              </div>
            </div>
          </div>

          <!-- Support Hours -->
          <div class="bg-white rounded-lg p-6 shadow-sm border border-gray-200">
            <h3 class="text-lg font-semibold text-gray-900 mb-4">Support Hours</h3>
            <div class="space-y-2 text-sm">
              <div class="flex justify-between">
                <span class="text-gray-600">Monday - Friday</span>
                <span class="font-medium text-gray-900">9:00 AM - 6:00 PM</span>
              </div>
              <div class="flex justify-between">
                <span class="text-gray-600">Saturday</span>
                <span class="font-medium text-gray-900">10:00 AM - 4:00 PM</span>
              </div>
              <div class="flex justify-between">
                <span class="text-gray-600">Sunday</span>
                <span class="font-medium text-gray-900">Closed</span>
              </div>
            </div>
          </div>

          <!-- Quick Response -->
          <div class="bg-white rounded-lg p-6 shadow-sm border border-gray-200">
            <div class="flex items-center space-x-3 mb-3">
              <div class="w-8 h-8 bg-gray-100 rounded-lg flex items-center justify-center">
                <svg class="w-4 h-4 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z"/>
                </svg>
              </div>
              <h3 class="font-semibold text-gray-900 text-sm">Quick Response</h3>
            </div>
            <p class="text-gray-600 text-sm">We typically respond to all inquiries within 24 hours.</p>
          </div>
        </div>

        <!-- Contact Form -->
        <div class="lg:col-span-2">
          <div class="bg-white rounded-lg p-8 shadow-sm border border-gray-200">
            <div class="mb-8">
              <h2 class="text-xl font-semibold text-gray-900 mb-2">Send us a Message</h2>
              <p class="text-gray-600 text-sm">Fill out the form below and we'll get back to you soon.</p>
            </div>

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
                    :class="['w-full px-3 py-2.5 border rounded-md focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 text-sm', 
                            errors.firstName ? 'border-red-500' : 'border-gray-300']"
                    placeholder="Enter your first name"
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
                    :class="['w-full px-3 py-2.5 border rounded-md focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 text-sm', 
                            errors.lastName ? 'border-red-500' : 'border-gray-300']"
                    placeholder="Enter your last name"
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
                  :class="['w-full px-3 py-2.5 border rounded-md focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 text-sm', 
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
                  :class="['w-full px-3 py-2.5 border rounded-md focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 text-sm', 
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
                  :class="['w-full px-3 py-2.5 border rounded-md focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 text-sm', 
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
                  rows="5"
                  :class="['w-full px-3 py-2.5 border rounded-md focus:ring-2 focus:ring-gray-900 focus:border-gray-900 transition-all duration-200 text-sm', 
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
                  class="w-full px-6 py-3 bg-black text-white font-medium rounded-md  transition-all duration-200 disabled:opacity-50 disabled:cursor-not-allowed text-sm"
                >
                  <span v-if="!isLoading" class="flex items-center justify-center space-x-2">
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"/>
                    </svg>
                    <span>Send Message</span>
                  </span>
                  <span v-else class="flex items-center justify-center space-x-2">
                    <svg class="animate-spin h-4 w-4 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                      <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                      <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                    </svg>
                    <span>Sending...</span>
                  </span>
                </button>
              </div>
            </form>
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
  components: {Hero, Navbar, Footer },
  data() {
    return {
      isLoading: false,
      showNotification: false,
      notificationMessage: '',
      notificationType: 'success',
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
        ? 'bg-gray-100 text-gray-900' 
        : 'bg-gray-100 text-gray-900';
    },
    notificationIconStyle() {
      return this.notificationType === 'success' 
        ? 'bg-gray-200 text-gray-700' 
        : 'bg-gray-200 text-gray-700';
    },
    notificationTextStyle() {
      return this.notificationType === 'success' 
        ? 'text-gray-900' 
        : 'text-gray-900';
    }
  },
  methods: {
    showCustomNotification(message, type = 'success') {
      this.notificationMessage = message;
      this.notificationType = type;
      this.showNotification = true;

      if (this.notificationTimeout) {
        clearTimeout(this.notificationTimeout);
      }

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
      Object.keys(this.errors).forEach(field => {
        this.validateField(field);
      });
      
      return !Object.values(this.errors).some(error => error !== '');
    },
    
    async validateAndSubmit() {
      if (!this.validateForm()) {
        this.showCustomNotification('Please fill the form before submitting.', 'error');
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
          this.showCustomNotification('Thank you! Your message has been sent successfully.', 'success');
          this.resetForm();
        } else {
          this.showCustomNotification('Failed to send message. Please try again later.', 'error');
          console.error('Form submission failed:', result);
        }
      } catch (error) {
        this.showCustomNotification('Network error. Please check your connection and try again.', 'error');
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
  transition: all 0.2s ease-in-out;
}

/* Custom shadow for depth */
.shadow-sm {
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
}
</style>