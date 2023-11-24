import ServerApi from '../config/axios';

const basePath = 'products';

const create = async (product) => {
    return await ServerApi.post(`${basePath}/`, product);
}

const getAll = async () => {
    return await ServerApi.get(`${basePath}/`);
}

const getByCategoryId = async (categoryId: number) => {
    return await ServerApi.get(`${basePath}?categoryId=${categoryId}`);
}

const productsService = {
    create,
    getAll,
    getByCategoryId
}

export default productsService;