<template>
  <footer class="bg-black text-gray-300 py-10 mt-20">
    <div class="max-w-6xl mx-auto px-6 text-center space-y-3">
      <h2 class="text-2xl font-semibold text-white">NepaliBazar</h2>
      <p class="text-gray-400 text-sm">
        Your trusted online marketplace connecting buyers and sellers across Nepal.
      </p>
      <p class="text-sm text-gray-400">
        📞 +977 9709518369 | ✉️ sagardada2535@gmail.com
      </p>
      <p class="text-xs text-gray-500 pt-4 border-t border-gray-700 mt-4">
        © {{ new Date().getFullYear() }} NepaliBazar. All Rights Reserved.
      </p>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer",
};
</script>

<style scoped>
footer {
  font-family: 'Inter', sans-serif;
}
</style>
