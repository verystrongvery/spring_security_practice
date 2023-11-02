import { api } from '@/api/api';

export const findUserApi = () => {
	try {
		return api.get('/v1/users');
	} catch (error) {
		console.log(error);
	}
};
