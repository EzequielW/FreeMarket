import * as React from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import ProductsTable from './ProductsTable';
import { Typography, Button, Box } from '@mui/material';
import { Link } from 'react-router-dom';
import Product from '../../interfaces/Product';
import { useEffect } from 'react';

import productsService from '../../services/productsService';

const ProductsAdmin = () => {
    const [products, setProducts] = React.useState<Product[]>([]);

    useEffect(() => {
        const loadData = async () => {
            try{
                const response = await productsService.getAll();
                setProducts(response.data);
                console.log(response.data)
            } catch(err){
                console.log(err);
            }
        }
        loadData();
    }, []); // eslint-disable-line react-hooks/exhaustive-deps

    return (
        <AdminLayout>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', paddingBottom: 2 }}>
                <Typography variant='h5' sx={{ fontWeight: 700 }}>Products</Typography>
                <Button variant="contained" component={Link} to='/products_admin/create'>Create product</Button>
            </Box>
            <ProductsTable products={products} />
        </AdminLayout>
    )
}

export default ProductsAdmin;