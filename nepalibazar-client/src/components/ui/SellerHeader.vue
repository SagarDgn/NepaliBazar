<template>
  <div class="bg-black text-white flex justify-center items-center h-8 relative overflow-hidden ">
    <!-- Animated background elements -->
    <div class="absolute inset-0">
      <div class="absolute top-1 left-1/4 w-1 h-1 bg-green-400 rounded-full animate-pulse"></div>
      <div class="absolute bottom-1 right-1/3 w-1 h-1 bg-orange-400 rounded-full animate-pulse delay-300"></div>
      <div class="absolute top-2 left-3/4 w-1 h-1 bg-white rounded-full animate-pulse delay-500"></div>
    </div>
    
    <p class="font-light text-sm tracking-wider transform hover:scale-105 transition-all duration-300 flex items-center space-x-3">
      <span class="text-yellow-300 animate-bounce">✨</span>
      <span class="font-medium bg-gradient-to-r from-green-300 to-orange-300 bg-clip-text text-transparent">
        Sell All Nepali Products in single marketplace
      </span>
      <span class="text-yellow-300 animate-bounce delay-100">🎯</span>
    </p>

    <!-- Side decorations -->
    <div class="absolute left-4 top-1/2 transform -translate-y-1/2 text-green-400 text-xs">
      🇳🇵
    </div>
    <div class="absolute right-4 top-1/2 transform -translate-y-1/2 text-orange-400 text-xs">
      🛍️
    </div>
  </div>
</template>

<script>
export default {
    name: "SellerHero"
}
</script>

<style scoped>
@keyframes subtle-glow {
  0%, 100% { opacity: 0.8; }
  50% { opacity: 1; }
}

.bg-gradient-to-r.from-green-300.to-orange-300 {
  animation: subtle-glow 2s ease-in-out infinite;
}
</style>