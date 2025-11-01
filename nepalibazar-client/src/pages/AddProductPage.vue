<template>
  <div class="min-h-screen flex flex-col">
    <!-- Header -->
     
    <div class="p-8 border-b border-gray-200/30 dark:border-gray-700/30 bg-gradient-to-r from-white to-gray-50 dark:from-gray-800 dark:to-gray-700">
      <div class="flex items-center space-x-4">
        <div class="p-3 bg-gradient-to-r from-green-500 to-orange-500 rounded-2xl shadow-lg">
          <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path>
          </svg>
        </div>
        <div>
          <h1 class="text-3xl font-bold bg-gradient-to-r from-green-600 to-orange-600 bg-clip-text text-transparent">
            {{ isEditing ? 'Edit Product' : 'Add New Product' }}
          </h1>
          <p class="text-gray-600 dark:text-gray-400 mt-2">
            {{ isEditing ? 'Update your product details' : 'Add a new product to your store' }}
          </p>
        </div>
      </div>
    </div>

    <!-- Product Form -->
    <div class="flex-1 p-8">
      <form @submit.prevent="validateAndSubmit" class="max-w-4xl mx-auto">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <!-- Left Column - Basic Info -->
          <div class="space-y-6">
            <!-- Product Name -->
            <div class="group">
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3 flex items-center space-x-2">
                <svg class="w-4 h-4 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                </svg>
                <span>Product Name *</span>
              </label>
              <input
                v-model="productForm.name"
                type="text"
                :class="[
                  'w-full px-4 py-3 bg-white dark:bg-gray-800 border-2 rounded-2xl focus:ring-4 transition-all duration-300 placeholder-gray-400 dark:placeholder-gray-500',
                  errors.name ? 'border-red-500 focus:border-red-500 focus:ring-red-200 dark:focus:ring-red-900/30' : 'border-gray-200 dark:border-gray-700 focus:border-green-500 focus:ring-green-200 dark:focus:ring-green-900/30'
                ]"
                placeholder="Enter product name"
                @blur="validateField('name')"
              >
              <p v-if="errors.name" class="text-red-500 text-xs mt-2 flex items-center space-x-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
                <span>{{ errors.name }}</span>
              </p>
            </div>

            <!-- Price & Discount -->
            <div class="grid grid-cols-2 gap-4">
              <div class="group">
                <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3 flex items-center space-x-2">
                  <svg class="w-4 h-4 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1"></path>
                  </svg>
                  <span>Price (Rs.) *</span>
                </label>
                <input
                  v-model.number="productForm.price"
                  type="number"
                  step="0.01"
                  min="0"
                  :class="[
                    'w-full px-4 py-3 bg-white dark:bg-gray-800 border-2 rounded-2xl focus:ring-4 transition-all duration-300',
                    errors.price ? 'border-red-500 focus:border-red-500 focus:ring-red-200 dark:focus:ring-red-900/30' : 'border-gray-200 dark:border-gray-700 focus:border-blue-500 focus:ring-blue-200 dark:focus:ring-blue-900/30'
                  ]"
                  placeholder="0.00"
                  @blur="validateField('price')"
                >
                <p v-if="errors.price" class="text-red-500 text-xs mt-2 flex items-center space-x-1">
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                  </svg>
                  <span>{{ errors.price }}</span>
                </p>
              </div>

              <div class="group">
                <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3 flex items-center space-x-2">
                  <svg class="w-4 h-4 text-orange-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 14l6-6m-5.5.5h.01m4.99 5h.01M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16l3.5-2 3.5 2 3.5-2 3.5 2z"></path>
                  </svg>
                  <span>Discount (%)</span>
                </label>
                <input
                  v-model.number="productForm.discount"
                  type="number"
                  min="0"
                  max="100"
                  :class="[
                    'w-full px-4 py-3 bg-white dark:bg-gray-800 border-2 rounded-2xl focus:ring-4 transition-all duration-300',
                    errors.discount ? 'border-red-500 focus:border-red-500 focus:ring-red-200 dark:focus:ring-red-900/30' : 'border-gray-200 dark:border-gray-700 focus:border-orange-500 focus:ring-orange-200 dark:focus:ring-orange-900/30'
                  ]"
                  placeholder="0"
                  @blur="validateField('discount')"
                >
                <p v-if="errors.discount" class="text-red-500 text-xs mt-2 flex items-center space-x-1">
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                  </svg>
                  <span>{{ errors.discount }}</span>
                </p>
              </div>
            </div>

            <!-- Quantity -->
            <div class="group">
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3 flex items-center space-x-2">
                <svg class="w-4 h-4 text-purple-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4"></path>
                </svg>
                <span>Quantity *</span>
              </label>
              <input
                v-model.number="productForm.quantity"
                type="number"
                min="0"
                :class="[
                  'w-full px-4 py-3 bg-white dark:bg-gray-800 border-2 rounded-2xl focus:ring-4 transition-all duration-300',
                  errors.quantity ? 'border-red-500 focus:border-red-500 focus:ring-red-200 dark:focus:ring-red-900/30' : 'border-gray-200 dark:border-gray-700 focus:border-purple-500 focus:ring-purple-200 dark:focus:ring-purple-900/30'
                ]"
                placeholder="0"
                @blur="validateField('quantity')"
              >
              <p v-if="errors.quantity" class="text-red-500 text-xs mt-2 flex items-center space-x-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
                <span>{{ errors.quantity }}</span>
              </p>
            </div>

            <!-- Image URL -->
            <div class="group">
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3 flex items-center space-x-2">
                <svg class="w-4 h-4 text-pink-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                </svg>
                <span>Image URL</span>
              </label>
              <input
                v-model="productForm.imageUrl"
                type="url"
                :class="[
                  'w-full px-4 py-3 bg-white dark:bg-gray-800 border-2 rounded-2xl focus:ring-4 transition-all duration-300',
                  errors.imageUrl ? 'border-red-500 focus:border-red-500 focus:ring-red-200 dark:focus:ring-red-900/30' : 'border-gray-200 dark:border-gray-700 focus:border-pink-500 focus:ring-pink-200 dark:focus:ring-pink-900/30'
                ]"
                placeholder="https://example.com/image.jpg"
                @blur="validateField('imageUrl')"
              >
              <p v-if="errors.imageUrl" class="text-red-500 text-xs mt-2 flex items-center space-x-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
                <span>{{ errors.imageUrl }}</span>
              </p>
            </div>
          </div>

          <!-- Right Column - Description -->
          <div class="space-y-6">
            <!-- Description -->
            <div class="group h-full">
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3 flex items-center space-x-2">
                <svg class="w-4 h-4 text-indigo-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 8h10M7 12h4m1 8l-4-4H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-3l-4 4z"></path>
                </svg>
                <span>Description *</span>
              </label>
              <textarea
                v-model="productForm.description"
                rows="12"
                :class="[
                  'w-full h-48 px-4 py-3 bg-white dark:bg-gray-800 border-2 rounded-2xl focus:ring-4 transition-all duration-300 resize-none',
                  errors.description ? 'border-red-500 focus:border-red-500 focus:ring-red-200 dark:focus:ring-red-900/30' : 'border-gray-200 dark:border-gray-700 focus:border-indigo-500 focus:ring-indigo-200 dark:focus:ring-indigo-900/30'
                ]"
                placeholder="Describe your product features, benefits, and specifications..."
                @blur="validateField('description')"
              ></textarea>
              <div class="flex justify-between text-xs mt-2">
                <span :class="errors.description ? 'text-red-500' : 'text-gray-500 dark:text-gray-400'">
                  Be descriptive to attract more customers
                </span>
                <span :class="productForm.description.length > 500 ? 'text-red-500' : 'text-gray-500 dark:text-gray-400'">
                  {{ productForm.description.length }}/500
                </span>
              </div>
              <p v-if="errors.description" class="text-red-500 text-xs mt-2 flex items-center space-x-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
                <span>{{ errors.description }}</span>
              </p>
            </div>

            <!-- Preview Card -->
            <div v-if="productForm.name" class="bg-gradient-to-br from-gray-50 to-green-50 dark:from-gray-700 dark:to-gray-600 rounded-2xl p-4 border-2 border-dashed border-gray-200 dark:border-gray-600">
              <h3 class="text-sm font-semibold text-gray-700 dark:text-gray-300 mb-3">Preview</h3>
              <div class="flex space-x-3">
                <div class="w-16 h-16 bg-gradient-to-br from-green-200 to-orange-200 dark:from-green-600 dark:to-orange-600 rounded-xl flex items-center justify-center">
                  <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4"></path>
                  </svg>
                </div>
                <div class="flex-1">
                  <h4 class="font-semibold text-gray-800 dark:text-white text-sm">{{ productForm.name }}</h4>
                  <p class="text-green-600 dark:text-green-400 font-bold text-lg mt-1">
                    Rs.{{ calculateDiscountedPrice(productForm.price, productForm.discount) }}
                    <span v-if="productForm.discount > 0" class="text-sm text-gray-500 line-through ml-2">
                      Rs.{{ productForm.price }}
                    </span>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Form Actions -->
        <div class="flex space-x-4 pt-8 mt-8 border-t border-gray-200 dark:border-gray-700">
          <button
            type="submit"
            :disabled="submitting"
            class="px-8 py-4 bg-gradient-to-r from-green-500 to-orange-500 hover:from-green-600 hover:to-orange-600 disabled:from-gray-400 disabled:to-gray-500 text-white font-semibold rounded-2xl transition-all duration-300 transform hover:scale-105 shadow-lg flex items-center space-x-3 min-w-40 justify-center"
          >
            <svg v-if="submitting" class="animate-spin -ml-1 mr-2 h-5 w-5 text-white" fill="none" viewBox="0 0 24 24">
              <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
              <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
            </svg>
            <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
            </svg>
            <span>{{ submitting ? 'Processing...' : (isEditing ? 'Update Product' : 'Create Product') }}</span>
          </button>
          
          <router-link
            to="/sellerdashboard/products"
            class="px-8 py-4 bg-gradient-to-r from-gray-100 to-gray-200 dark:from-gray-700 dark:to-gray-600 hover:from-gray-200 hover:to-gray-300 dark:hover:from-gray-600 dark:hover:to-gray-500 text-gray-700 dark:text-gray-300 font-semibold rounded-2xl transition-all duration-300 transform hover:scale-105 shadow-lg flex items-center space-x-3"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
            <span>Cancel</span>
          </router-link>
        </div>
      </form>
    </div>

    <!-- Footer -->
    <SellerFooter />
  </div>
</template>

<script>
import SellerFooter from '../components/ui/SellerFooter.vue';
import SellerHeader from '../components/ui/SellerHeader.vue';
import ProductService from '../services/ProductService';

export default {
  name: 'AddProduct',
  components: { SellerFooter,SellerHeader },
  data() {
    return {
      submitting: false,
      isEditing: false,
      productForm: {
        id: null,
        name: '',
        description: '',
        price: 0,
        discount: 0,
        quantity: 0,
        imageUrl: ''
      },
      errors: {
        name: '',
        description: '',
        price: '',
        discount: '',
        quantity: '',
        imageUrl: ''
      },
      showAllErrors: false
    }
  },
  mounted() {
    // Check if we're in edit mode
    if (this.$route.query.edit && this.$route.state?.productData) {
      this.isEditing = true
      this.productForm = { ...this.$route.state.productData }
    }
  },
  methods: {
    calculateDiscountedPrice(price, discount) {
      return (price * (1 - discount / 100)).toFixed(2)
    },

    validateField(field) {
      this.errors[field] = ''

      switch (field) {
        case 'name':
          if (!this.productForm.name.trim()) {
            this.errors.name = 'Product name is required'
          } else if (this.productForm.name.length < 3) {
            this.errors.name = 'Product name must be at least 3 characters'
          } else if (this.productForm.name.length > 100) {
            this.errors.name = 'Product name must be less than 100 characters'
          }
          break

        case 'description':
          if (!this.productForm.description.trim()) {
            this.errors.description = 'Product description is required'
          } else if (this.productForm.description.length < 10) {
            this.errors.description = 'Description must be at least 10 characters'
          } else if (this.productForm.description.length > 500) {
            this.errors.description = 'Description must be less than 500 characters'
          }
          break

        case 'price':
          if (this.productForm.price === '' || this.productForm.price === null) {
            this.errors.price = 'Price is required'
          } else if (this.productForm.price < 0) {
            this.errors.price = 'Price cannot be negative'
          } else if (this.productForm.price > 1000000) {
            this.errors.price = 'Price seems too high'
          }
          break

        case 'discount':
          if (this.productForm.discount < 0) {
            this.errors.discount = 'Discount cannot be negative'
          } else if (this.productForm.discount > 100) {
            this.errors.discount = 'Discount cannot exceed 100%'
          }
          break

        case 'quantity':
          if (this.productForm.quantity === '' || this.productForm.quantity === null) {
            this.errors.quantity = 'Quantity is required'
          } else if (this.productForm.quantity < 0) {
            this.errors.quantity = 'Quantity cannot be negative'
          } else if (this.productForm.quantity > 100000) {
            this.errors.quantity = 'Quantity seems too high'
          }
          break

        case 'imageUrl':
          if (this.productForm.imageUrl && !this.isValidUrl(this.productForm.imageUrl)) {
            this.errors.imageUrl = 'Please enter a valid URL'
          }
          break
      }
    },

    isValidUrl(string) {
      try {
        new URL(string)
        return true
      } catch (_) {
        return false
      }
    },

    validateAllFields() {
      // Validate all required fields
      this.validateField('name')
      this.validateField('description')
      this.validateField('price')
      this.validateField('quantity')
      this.validateField('discount')
      this.validateField('imageUrl')

      // Check if any errors exist
      const hasErrors = Object.values(this.errors).some(error => error !== '')
      
      if (hasErrors) {
        // Show notification for first error found
        const firstError = Object.values(this.errors).find(error => error !== '')
        if (firstError) {
          this.$parent.showToast(`Please fix the form errors: ${firstError}`, 'error')
        }
      }

      return !hasErrors
    },

    async validateAndSubmit() {
      // Validate all fields when submit is clicked
      if (!this.validateAllFields()) {
        // Scroll to first error
        this.scrollToFirstError()
        return
      }

      await this.submitProduct()
    },

    scrollToFirstError() {
      // Find first element with error and scroll to it
      const firstErrorElement = this.$el.querySelector('.border-red-500')
      if (firstErrorElement) {
        firstErrorElement.scrollIntoView({ 
          behavior: 'smooth', 
          block: 'center' 
        })
        firstErrorElement.focus()
      }
    },

    async submitProduct() {
      this.submitting = true
      try {
        const productData = {
          productName: this.productForm.name.trim(),
          aboutProduct: this.productForm.description.trim(),
          price: parseFloat(this.productForm.price),
          discount: parseInt(this.productForm.discount) || 0,
          quantity: parseInt(this.productForm.quantity),
          image: this.productForm.imageUrl.trim() || null
        }

        if (this.isEditing) {
          await ProductService.updateProduct(this.productForm.id, productData)
          this.$parent.showToast('Product updated successfully!', 'success')
        } else {
          await ProductService.addProduct(productData)
          this.$parent.showToast('Product added successfully!', 'success')
        }

        this.$router.push('/sellerdashboard/products')
      } catch (error) {
        console.error('Error submitting product:', error)
        this.$parent.showToast('Failed to submit product: ' + error.message, 'error')
      } finally {
        this.submitting = false
      }
    }
  }
}
</script>