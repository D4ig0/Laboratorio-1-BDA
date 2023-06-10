<script setup lang="ts">
import { RouterView } from 'vue-router'
import NavBar from './components/NavBar.vue'
import { useAuthStore } from './stores/auth'
const store = useAuthStore()
store.initStates()
</script>

<template>
  
  <header>
    
    <NavBar/>
  </header>

  <RouterView />
</template>

<style scoped>

</style>
