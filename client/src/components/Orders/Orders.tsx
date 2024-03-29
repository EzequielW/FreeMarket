import * as React from 'react';
import { useState, useEffect } from 'react';
import { Container, Grid, Box } from '@mui/material';

import MainLayout from '../../layouts/MainLayout';
import OrderCard from './OrderCard';

import orderDetailsService from '../../services/orderDetailsService';

const Orders = () => {
    const [odList, setODList] = useState([]);

    const getOrderHistory = async () => {
        try{
            const response = await orderDetailsService.getAllByUser();
            setODList(response.data.filter((o: any) => o.orderItems.length > 0));
        } catch(err){
            console.log(err);
        }
    }

    useEffect(() => {
        const loadData = async () => {
            await getOrderHistory();
        }
        loadData();
    }, []); // eslint-disable-line react-hooks/exhaustive-deps

    return (
        <MainLayout>
            <Container maxWidth="lg" sx={{ py: 4 }}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        {
                            odList.map(od => {
                                return (
                                    <Box key={'order_' + od.id} sx={{ mb: 2 }}>
                                        <OrderCard orderItems={od.orderItems} paymentStatus={od.paymentStatus} createdAt={od.createdAt} />
                                    </Box>
                                );
                            })
                        }
                    </Grid>
                </Grid>
            </Container>
        </MainLayout>
    );
}

export default Orders;