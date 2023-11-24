import * as React from 'react';
import { Card, Box, CardContent, Typography, Divider } from '@mui/material';

const ProductCard = ({orderItems, paymentStatus, createdAt}) => {
    const total = () => {
        return orderItems.reduce(
            (sum, orderItem) => sum + orderItem.product.price * orderItem.quantity,
            0
        );
    }

    return (
        <Card>
            <CardContent sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                    <Typography component="div" variant="h6">
                        { new Date(createdAt).toLocaleDateString('en-us', { year:"numeric", month:"long", day:"numeric"})  }
                    </Typography>
                    <Typography component="div" variant="h6">
                        { paymentStatus }
                    </Typography>
                </Box>
                <Divider />
            </CardContent>
            
            <CardContent sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                {
                    orderItems.map(oi => {
                        return (
                            <Box key={oi.id} sx={{ display: 'flex', justifyContent: 'space-between', paddingBottom: '1rem' }}>
                                <Typography component="div" variant="h6">
                                    { oi.product.name }
                                    <Typography component="div" variant="subtitle2">
                                        Quantity { oi.quantity }
                                    </Typography>
                                </Typography>
                                
                                <Typography component="div" variant="h6">
                                    ${ (oi.product.price * oi.quantity).toFixed(2) }
                                </Typography>
                            </Box>
                        )
                    })
                }

                <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                    <Typography component="div" variant="h5">
                        Total
                    </Typography>
                    <Typography component="div" variant="h5">
                        ${ total() ? total().toFixed(2) : "00.00" }
                    </Typography>
                </Box>
            </CardContent>
        </Card>
    );
}

export default ProductCard;