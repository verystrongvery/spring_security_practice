import { AUTHENTICATION_DONE_COOKIE_KEY_NAME, OAUTH2_LOGIN_URL } from '@/constants/api';
import { useCookies } from 'vue3-cookies';
import { useUsersStore } from '@/store/users';

const { cookies } = useCookies();
export const openOauthWindow = (loginSuccessRedirectUrl: string) => {
	const oauthWindow = window.open(OAUTH2_LOGIN_URL, '_blank', 'width=400,height=600');
	const timer = setInterval(() => oauthAuthenticationSuccess(oauthWindow, timer, loginSuccessRedirectUrl), 1000);
};

const oauthAuthenticationSuccess = async (oauthWindow: Window | null, timer: number, loginSuccessRedirectUrl: string) => {
	if (!cookies.isKey(AUTHENTICATION_DONE_COOKIE_KEY_NAME)) {
		return;
	}
	clearInterval(timer);
	cookies.remove(AUTHENTICATION_DONE_COOKIE_KEY_NAME);
	oauthWindow?.close();
	const user = useUsersStore();
	await user.loadUser();
	window.location.href = loginSuccessRedirectUrl;
};
