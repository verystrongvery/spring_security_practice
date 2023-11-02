<template>
  <v-app>
    <TheNav/>
    <TheFooter/>
  </v-app>
</template>

<script setup lang="ts">
import TheNav from '@/layouts/nav/TheNav.vue';
import TheFooter from '@/layouts/footer/TheFooter.vue';
</script>

<style>
a {
  text-decoration: none;
  color: black;
}
</style>