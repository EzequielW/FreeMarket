import * as React from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import OrdersTable from './OrdersTable';
import { Box, Typography } from '@mui/material';

const OrdersAdmin = () => {

    return (
        <AdminLayout>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', paddingBottom: 2 }}>
                <Typography variant='h5' sx={{ fontWeight: 700 }}>Orders</Typography>
            </Box>
            <OrdersTable />
        </AdminLayout>
    )
}

export default OrdersAdmin;