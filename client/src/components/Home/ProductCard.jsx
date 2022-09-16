import React from 'react';
import { Card, CardMedia, Box, CardContent, Typography } from '@mui/material';

const ProductCard = ({product}) => {
    return (
        <Card sx={{ display: 'flex' }}>
            <CardMedia
                component="img"
                sx={{ width: 200, height: 200, p: 1 }}
                image={`${process.env.REACT_APP_SERVER_URL}public${product.imagePath}`}
                alt="Product image"
            />
            <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                <CardContent sx={{ flex: '1 0 auto' }}>
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
                    ${ product ? product.price : "00.00" }
                </Typography>
                </CardContent>
            </Box>
        </Card>
    );
}

export default ProductCard;