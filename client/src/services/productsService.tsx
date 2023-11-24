import ServerApi from '../config/axios';

const basePath = 'products';

const getAll = async () => {
    return await ServerApi.get(`${basePath}/`);
}

const getByCategoryId = async (categoryId: number) => {
    return await ServerApi.get(`${basePath}?categoryId=${categoryId}`);
}

const productsService = {
    getAll,
    getByCategoryId
}

export default productsService;