import axios from 'axios';

const basePath = 'order_details';

const getActive = async (token) => {
    return await axios.get(`${process.env.REACT_APP_SERVER_URL}${basePath}/active`, {
        headers: {
            Authorization: token
        }
    });
}

const checkout = async (token) => {
    return await axios.post(`${process.env.REACT_APP_SERVER_URL}${basePath}/checkout`, {}, {
        headers: {
            Authorization: token
        }
    });
}

const orderDetailsService = {
    getActive,
    checkout
}

export default orderDetailsService;