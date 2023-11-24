import * as React from 'react';
import { Card, CardMedia, Box, CardContent, Typography, Button } from '@mui/material';
import { AddShoppingCart } from '@mui/icons-material';

import orderItemsService from '../../services/orderItemsService';

const ProductCard = ({product, orderItems, updateCart}) => {
    const addToCart = async () => {
        try{
            const newOrderItem = {
                id: 0,
                productId: product.id,
                quantity: 1
            }

            console.log('productid', product.id, 'orderItems', orderItems);
            const orderItem = orderItems.find(oi => oi.product.id === product.id);

            if(orderItem){
                newOrderItem.quantity += orderItem.quantity;
                newOrderItem.id = orderItem.id;
                await orderItemsService.update(newOrderItem);
            }
            else{
                await orderItemsService.create(newOrderItem);
            }
            updateCart();
        } catch(err){
            console.log(err);
        }
    }

    return (
        <Card sx={{ display: 'flex' }}>
            <CardMedia
                component="img"
                sx={{ width: 200, height: 200, p: 1 }}
                image={`${process.env.REACT_APP_SERVER_URL}public${product.imagePath}`}
                alt="Product image"
            />
            <CardContent sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                <Box>
                    <Typography component="div" variant="h6" 
                        sx={{ 
                            mb: 1,
                            overflow: "hidden",
                            textOverflow: "ellipsis",
                            display: "-webkit-box",
                            WebkitLineClamp: 2,
                            WebkitBoxOrient: "vertical" 
                        }}>
                        { product ? product.name : "Placeholder title" }
                    </Typography>
                    <Typography component="div" variant="h5">
                        ${ product ? product.price.toFixed(2) : "00.00" }
                    </Typography>
                </Box>
                <Button variant='contained' startIcon={<AddShoppingCart />} sx={{ alignSelf: 'flex-start'}} onClick={addToCart}>Add to cart</Button>
            </CardContent>
        </Card>
    );
}

export default ProductCard;