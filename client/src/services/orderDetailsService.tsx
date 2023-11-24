import ServerApi from '../config/axios';

const basePath = 'order_details';

const getActive = async () => {
    return await ServerApi.get(`${basePath}/active`);
}

const getAllByUser = async () => {
    return await ServerApi.get(`${basePath}/`);
}

const checkout = async () => {
    return await ServerApi.post(`${basePath}/checkout`);
}

const orderDetailsService = {
    getActive,
    checkout,
    getAllByUser
}

export default orderDetailsService;