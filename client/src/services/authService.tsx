import axios from 'axios';

const basePath = 'auth';

const login = async (credentials) => {
    return await axios.post(`${process.env.REACT_APP_SERVER_URL}${basePath}/login`, credentials);
}

const logout = async () => {
    sessionStorage.setItem('id', '');
    sessionStorage.setItem('name', '');
    sessionStorage.setItem('role', '');
    sessionStorage.setItem('token', '');
}

const authService = {
    login,
    logout
}

export default authService;