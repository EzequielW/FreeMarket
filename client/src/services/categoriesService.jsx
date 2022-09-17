import axios from 'axios';

const basePath = 'categories';

const getAll = async (token) => {
    return await axios.get(`${process.env.REACT_APP_SERVER_URL}${basePath}/`, {
        headers: {
            Authorization: token
        }
    });
}

const categoriesService = {
    getAll
}

export default categoriesService;