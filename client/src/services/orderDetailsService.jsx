import axios from 'axios';

const basePath = 'order_details';

const getActive = async (token) => {
    return await axios.get(`${process.env.REACT_APP_SERVER_URL}${basePath}/active`, {
        headers: {
            Authorization: token
        }
    });
}

const orderDetailsService = {
    getActive
}

export default orderDetailsService;