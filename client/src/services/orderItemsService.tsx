import ServerApi from '../config/axios';

const basePath = 'order_items';

const create = async (orderItem) => {
    return await ServerApi.post(`${basePath}/`, orderItem);
}

const update = async (orderItem) => {
    return await ServerApi.put(`${basePath}/${orderItem.id}`, orderItem);
}

const deleteOne = async (id) => {
    return await ServerApi.delete(`${basePath}/${id}`);
}

const orderItemsService = {
    create,
    update,
    deleteOne
}

export default orderItemsService;