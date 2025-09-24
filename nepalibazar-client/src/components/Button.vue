<template>
  <div class="">
    <v-btn class="bg-red-700/95 hover:bg-red-700 text-white rounded">
        {{ label }}
        <slot/>
    </v-btn>
  </div>
</template>

<script>
export default {
    name: "Button"
}
</script>
