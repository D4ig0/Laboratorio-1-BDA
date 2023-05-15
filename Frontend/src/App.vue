<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import NavBar from './components/Navbar.vue'
import axios from 'axios'

</script>

<template>
  <header>
    <NavBar/>
  </header>

  <RouterView />
</template>

<style scoped>
body { 
  width: 100%;
  height: 100%;
}
</style>
