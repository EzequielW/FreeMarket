import ServerApi from '../config/axios';

const basePath = 'categories';

const getAll = async () => {
    return await ServerApi.get(`${basePath}/`);
}

const categoriesService = {
    getAll
}

export default categoriesService;