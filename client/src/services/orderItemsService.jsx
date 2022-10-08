import axios from 'axios';

const basePath = 'order_items';

const create = async (token, orderItem) => {
    return await axios.post(`${process.env.REACT_APP_SERVER_URL}${basePath}/`, orderItem, {
        headers: {
            Authorization: token
        }
    });
}

const update = async (token, orderItem) => {
    return await axios.put(`${process.env.REACT_APP_SERVER_URL}${basePath}/${orderItem.id}`, orderItem, {
        headers: {
            Authorization: token
        }
    });
}

const deleteOne = async (token, id) => {
    return await axios.delete(`${process.env.REACT_APP_SERVER_URL}${basePath}/${id}`, {
        headers: {
            Authorization: token
        }
    });
}

const orderItemsService = {
    create,
    update,
    deleteOne
}

export default orderItemsService;