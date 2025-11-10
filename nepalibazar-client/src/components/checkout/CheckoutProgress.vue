<template>
  <div class="bg-white shadow border-b">
    <div class="container mx-auto px-4 py-6">
      <div class="flex items-center justify-center">
        <div class="flex items-center space-x-8">
          <!-- Step 1: Shipping -->
          <div class="flex items-center">
            <div class="flex items-center justify-center w-12 h-12 rounded-full border-2"
                 :class="currentStep >= 1 ? 'bg-black border-black text-white' : 'bg-white border-gray-300 text-gray-500'">
              <span class="text-sm font-semibold">1</span>
            </div>
            <span class="ml-3 font-medium" :class="currentStep >= 1 ? 'text-gray-800' : 'text-gray-500'">
              Shipping
            </span>
          </div>
          
          <div class="w-16 h-0.5" :class="currentStep >= 2 ? 'bg-gray-800' : 'bg-gray-300'"></div>
          
          <!-- Step 2: Payment -->
          <div class="flex items-center">
            <div class="flex items-center justify-center w-12 h-12 rounded-full border-2"
                 :class="currentStep >= 2 ? 'bg-black border-black text-white' : 'bg-white border-gray-300 text-gray-500'">
              <span class="text-sm font-semibold">2</span>
            </div>
            <span class="ml-3 font-medium" :class="currentStep >= 2 ? 'text-gray-800' : 'text-gray-500'">
              Payment
            </span>
          </div>
          
          <div class="w-16 h-0.5" :class="currentStep >= 3 ? 'bg-gray-800' : 'bg-gray-300'"></div>
          
          <!-- Step 3: Confirmation -->
          <div class="flex items-center">
            <div class="flex items-center justify-center w-12 h-12 rounded-full border-2"
                 :class="currentStep >= 3 ? 'bg-black border-black text-white' : 'bg-white border-gray-300 text-gray-500'">
              <span class="text-sm font-semibold">3</span>
            </div>
            <span class="ml-3 font-medium" :class="currentStep >= 3 ? 'text-gray-800' : 'text-gray-500'">
              Confirmation
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CheckoutProgress",
  props: {
    currentStep: {
      type: Number,
      required: true
    }
  }
};
</script>