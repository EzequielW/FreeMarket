import axios from 'axios';

const basePath = 'products';

const getAll = async (token) => {
    return await axios.get(`${process.env.REACT_APP_SERVER_URL}${basePath}/`, {
        headers: {
            Authorization: token
        }
    });
}

const productsService = {
    getAll
}

export default productsService;