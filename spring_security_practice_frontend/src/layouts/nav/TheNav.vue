<template>
  <v-navigation-drawer expand-on-hover rail app>
    <v-row>
      <v-col>
        <v-list>
          <v-list-item v-if="user.state.accessToken != ''" :prepend-avatar="user.state.picture" :title="user.state.name" :subtitle="user.state.email"></v-list-item>
          <v-list-item v-else :prepend-avatar="nonLogin" @click="openOauthWindow('/')">
            <v-list-item-title class="font-weight-bold">로그인</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
    <v-row>
      <v-divider></v-divider>
    </v-row>
  </v-navigation-drawer>
</template>

<script setup lang="ts">
import { useUsersStore } from '@/store/users';
import { openOauthWindow } from '@/service/login';
import nonLogin from '@/assets/non_login.png';

const user = useUsersStore();

user.loadUser();
</script>