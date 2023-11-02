import { api } from '@/api/api';

export const deleteRefreshTokenApi = () => {
	try {
		return api.delete('/v1/refresh-token');
	} catch (error) {
		console.log(error);
	}
};

export const createAccessTokenApi = () => {
	try {
		return api.post('/v1/refresh-token/access-token');
	} catch (error) {
		console.log(error);
	}
};
