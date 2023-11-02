<template>
  <v-bottom-navigation v-if="user.state.accessToken != ''">
    <v-btn @click="logout">
      <v-icon>mdi-exit-run</v-icon>
      <span class="font-weight-bold">로그아웃</span>
    </v-btn>
  </v-bottom-navigation>
</template>
<script setup lang="ts">
import { useUsersStore } from '@/store/users';
import { deleteRefreshTokenApi } from '@/api/refreshToken';

const user = useUsersStore();

const logout = async () => {
  await deleteRefreshTokenApi();
  user.clear();
  window.location.href = '/';
};
</script>
<style scoped></style>