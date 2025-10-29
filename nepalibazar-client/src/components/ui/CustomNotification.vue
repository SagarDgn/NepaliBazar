<template>
  <div
    v-if="show"
    class="fixed top-4 right-4 z-50 transform transition-all duration-500 translate-x-0 opacity-100"
  >
    <div class="flex items-center space-x-3 px-6 py-4 rounded-xl shadow-2xl backdrop-blur-sm border" :class="notificationStyle">
      <div class="flex-shrink-0">
        <div class="w-8 h-8 rounded-full flex items-center justify-center animate-pulse" :class="notificationIconStyle">
          <svg v-if="type === 'success'" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
          </svg>
          <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
          </svg>
        </div>
      </div>
      <div class="flex-1 min-w-0">
        <p class="text-sm font-semibold" :class="notificationTextStyle">
          {{ message }}
        </p>
      </div>
      <button
        @click="$emit('hide')"
        class="flex-shrink-0 text-gray-400 hover:text-gray-600 transition-colors"
      >
        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
        </svg>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CustomNotification",
  props: {
    show: {
      type: Boolean,
      default: false
    },
    message: {
      type: String,
      default: ''
    },
    type: {
      type: String,
      default: 'success',
      validator: (value) => ['success', 'error'].includes(value)
    }
  },
  emits: ['hide'],
  computed: {
    notificationStyle() {
      return this.type === 'success' 
        ? 'bg-green-50 border-green-200 text-green-800' 
        : 'bg-red-50 border-red-200 text-red-800';
    },
    notificationIconStyle() {
      return this.type === 'success' 
        ? 'bg-green-100 text-green-600' 
        : 'bg-red-100 text-red-600';
    },
    notificationTextStyle() {
      return this.type === 'success' 
        ? 'text-green-800' 
        : 'text-red-800';
    }
  }
};
</script>