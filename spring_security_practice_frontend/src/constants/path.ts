const VITE_HOST = import.meta.env.VITE_HOST;
const VITE_TOMCAT_PORT = import.meta.env.VITE_TOMCAT_PORT;

export const BACKEND_BASE_URL = 'http://' + VITE_HOST + ':' + VITE_TOMCAT_PORT;