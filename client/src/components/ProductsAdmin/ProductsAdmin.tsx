import * as React from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import ProductsTable from './ProductsTable';
import { Typography, Button, Box } from '@mui/material';
import { Link } from 'react-router-dom';

const ProductsAdmin = () => {

    return (
        <AdminLayout>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', paddingBottom: 2 }}>
                <Typography variant='h5' sx={{ fontWeight: 700 }}>Products</Typography>
                <Button variant="contained" component={Link} to='/products_admin/create'>Create product</Button>
            </Box>
            <ProductsTable />
        </AdminLayout>
    )
}

export default ProductsAdmin;