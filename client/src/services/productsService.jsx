import axios from 'axios';

const basePath = 'products';

const getAll = async (token) => {
    return await axios.get(`${process.env.REACT_APP_SERVER_URL}${basePath}/`, {
        headers: {
            Authorization: token
        }
    });
}

const getByCategoryId = async (token, categoryId) => {
    return await axios.get(`${process.env.REACT_APP_SERVER_URL}${basePath}?categoryId=${categoryId}`, {
        headers: {
            Authorization: token
        }
    });
}

const productsService = {
    getAll,
    getByCategoryId
}

export default productsService;