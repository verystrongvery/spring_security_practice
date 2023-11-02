import { defineStore } from 'pinia';
import { findUserApi } from '@/api/users';
import { computed, reactive } from 'vue';
import { createAccessTokenApi } from '@/api/refreshToken';

export const useUsersStore = defineStore('users', () => {
	const state = reactive({ id: '', email: '', name: '', picture: '', accessToken: '' });

	const loadUser = async () => {
		const res: any = await createAccessTokenApi();
		await createAccessTokenApiSuccess(res.data);
	};

	const createAccessTokenApiSuccess = async (data: any) => {
		// 리프레시 토큰이 유효하지 않은 경우
		if (data == '') {
			clear();
			return;
		}
		state.accessToken = data.accessToken;
		const res: any = await findUserApi();
		findUserApiSuccess(res.data);
	};

	const findUserApiSuccess = (data: any) => {
		state.id = data.usersId;
		state.email = data.email;
		state.name = data.name;
		state.picture = data.picture;
	};

	const clear = () => {
		state.id = '';
		state.email = '';
		state.name = '';
		state.picture = '';
		state.accessToken = '';
	};

	const isLogin = computed(() => state.accessToken != '' && state.id != '');

	return { state, loadUser, clear, isLogin };
});
