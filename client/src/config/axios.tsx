import axios from 'axios';

const ServerApi = axios.create({
    baseURL: `${process.env.REACT_APP_SERVER_URL}`,
});

if(sessionStorage.getItem('token')) {
    ServerApi.interceptors.request.use(
        (config) => {
            const token =  sessionStorage.getItem('token')
            return {
                ...config,
                headers: {
                    Authorization: `${token}`,
                    ...config.headers,
                },
            };
        },
        (error) => {
            return Promise.reject(error);
        }
    );
}

export default ServerApi;