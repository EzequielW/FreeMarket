import * as React from 'react';
import { useState, useEffect } from 'react';
import { Container, Grid, Box } from '@mui/material';

import ProductCard from './ProductCard';
import CartCard from './CartCard';
import CategoryFilter from './CategoryFilter';
import productsService from '../../services/productsService';
import orderDetailsService from '../../services/orderDetailsService';
import MainLayout from '../../layouts/MainLayout';

const Home = ({user}) => {
    const [products, setProducts] = useState([]);
    const [orderDetails, setOrderDetails] = useState({
        orderItems: []
    });

    const getOrderDetails = async () => {
        try{
            const response = await orderDetailsService.getActive(user.token);
            setOrderDetails(response.data);
        } catch(err){
            console.log(err);
        }
    }

    useEffect((token = user.token) => {
        const loadData = async () => {
            try{
                const response = await productsService.getAll(token);
                setProducts(response.data);
            } catch(err){
                console.log(err);
            }

            await getOrderDetails();
        }
        loadData();
    }, []); // eslint-disable-line react-hooks/exhaustive-deps

    return (
        <MainLayout>
            <Container maxWidth="lg" sx={{ py: 4 }}>
                <Grid container spacing={2}>
                    <Grid item xs={4}>
                        <CategoryFilter token={user.token} setProducts={setProducts} />
                    </Grid>
                    <Grid item xs={8}>
                        {
                            products.map(p => {
                                return (
                                    <Box key={p.id} sx={{ mb: 2 }}>
                                        <ProductCard product={p} orderItems={orderDetails.orderItems} token={user.token} updateCart={getOrderDetails} />
                                    </Box>
                                );
                            })
                        }
                    </Grid>
                </Grid>
                { // Show cart only if there are items already
                    orderDetails && orderDetails.orderItems && orderDetails.orderItems.length > 0 &&
                    <CartCard token={user.token} orderItems={orderDetails.orderItems} getOrderDetails={getOrderDetails} />
                }
            </Container>
        </MainLayout>
    );
}

export default Home;