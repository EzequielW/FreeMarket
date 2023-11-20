import * as React from 'react';
import { useState } from 'react';
import { Box, Paper, Grow, Typography, Button, CardMedia, Fab, 
    Badge, CircularProgress, Collapse } from '@mui/material';
import { TransitionGroup } from 'react-transition-group';
import { styled } from '@mui/material/styles';
import { AddCircle, RemoveCircle, ShoppingCart } from '@mui/icons-material';

import orderItemsService from '../../services/orderItemsService';
import orderDetailsService from '../../services/orderDetailsService';

const CartCard = ({token, orderItems, getOrderDetails}) => {
    const [showCart, setShowCart] = useState(false);

    const mercadopago = new window.MercadoPago(process.env.REACT_APP_MP_PUBLIC_KEY, {
        locale: 'es-AR'
    });

    const StyledBadge = styled(Badge)(() => ({
        '& .MuiBadge-badge': {
          right: -3,
          top: 13,
          padding: '0 4px',
        },
    }));

    const updateOrderItem = async (orderItem, newQuantity) => {
        try{
            const updatedOrderItem = {
                id: orderItem.id,
                productId: orderItem.product.id,
                quantity: newQuantity
            };

            const response = await orderItemsService.update(token, updatedOrderItem);
            console.log(response.data);
            await getOrderDetails();
        } catch(err){
            console.log(err);
        }
    }

    const removeOrderItem = async (orderItem) => {
        try{
            const response = await orderItemsService.deleteOne(token, orderItem.id);
            console.log(response.data);
            await getOrderDetails();
        } catch(err){
            console.log(err);
        }
    }

    const checkoutOrder = async () => {
        try{
            const response = await orderDetailsService.checkout(token);
            mercadopago.checkout({
                preference: {
                  id: response.data
                },
                autoOpen: true
            });
        } catch(err){
            console.log(err);
        }
    }

    return (
        <div>
            <Grow in={showCart}>
                <Paper sx={{ position: 'fixed', bottom: 80, right: 16, width: "500px"}}>
                    <Box sx={{ p: 3, maxHeight: "500px", overflowY: "auto" }}>
                        {   
                            orderItems ?
                            <TransitionGroup>
                                {
                                    orderItems.map(oi => {
                                        return (
                                            <Collapse key={oi.id}>
                                                <Box sx={{ display: 'flex' }}>
                                                    <CardMedia
                                                        component="img"
                                                        sx={{ width: 100, height: 100, p: 1 }}
                                                        image={`${process.env.REACT_APP_SERVER_URL}public${oi.product.imagePath}`}
                                                        alt="Product image"
                                                    />
                                                    <Box sx={{ width: "100%" }}>
                                                        <Typography sx={{ 
                                                            overflow: "hidden",
                                                            textOverflow: "ellipsis",
                                                            display: "-webkit-box",
                                                            WebkitLineClamp: 2,
                                                            WebkitBoxOrient: "vertical",
                                                            mr: 10
                                                        }}>
                                                            {oi.product.name}
                                                        </Typography>
                                                    </Box>
                                                    <Typography>
                                                        {'$' + (oi.product.price * oi.quantity).toFixed(2)}
                                                    </Typography>
                                                </Box>
                                                <Box sx={{ display: 'flex' }}>
                                                    <Typography sx={{ pr: 2 }}>Item quantity</Typography>
                                                    <AddCircle color="primary" sx={{ cursor: "pointer" }}
                                                        onClick={() => updateOrderItem(oi, oi.quantity + 1) } />
                                                    <Typography sx={{ px: 1 }}>
                                                        {oi.quantity}
                                                    </Typography>
                                                    <RemoveCircle color="primary" sx={{ cursor: "pointer" }} 
                                                        onClick={() => {
                                                            if(oi.quantity === 1){
                                                                removeOrderItem(oi);
                                                            }
                                                            else{
                                                                updateOrderItem(oi, oi.quantity - 1);
                                                            }
                                                        }}/>
                                                </Box>
                                            </Collapse>
                                        );
                                    })
                                }
                            </TransitionGroup>
                            : <CircularProgress />
                        }
                        <Box sx={{ display: 'flex', justifyContent: 'space-between', pt: 3 }}>
                            <Typography>
                                Total
                            </Typography>
                            <Typography>
                                ${ 
                                    orderItems ?
                                    orderItems.reduce((prev, oi) => prev + (oi.product.price * oi.quantity), 0).toFixed(2) 
                                    : "0"
                                }
                            </Typography>
                        </Box>
                    </Box>
                    <Button variant='contained' fullWidth onClick={checkoutOrder}>Checkout</Button>
                </Paper>
            </Grow>
            <Fab color="primary" aria-label="shopping-cart" sx={{ position: 'fixed', bottom: 16, right: 16 }} onClick={() => setShowCart(!showCart)}>
                <StyledBadge badgeContent={orderItems ? orderItems.length : 0} color="secondary">
                    <ShoppingCart />
                </StyledBadge>
            </Fab>
        </div>
    )
}

export default CartCard;