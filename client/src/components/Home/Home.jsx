import { useState, useEffect } from 'react';
import { Container, Grid, Box, Paper, FormControl, RadioGroup, Radio, 
    FormControlLabel, Typography, Button, CardMedia, Fab, Badge, CircularProgress } from '@mui/material';
import { styled } from '@mui/material/styles';
import { Search, AddCircle, RemoveCircle, ShoppingCart } from '@mui/icons-material';

import ProductCard from './ProductCard';
import productsService from '../../services/productsService';
import categoriesService from '../../services/categoriesService';
import orderDetailsService from '../../services/orderDetailsService';

const Home = ({user}) => {
    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState({ id: 0, name: "All"});
    const [orderDetails, setOrderDetails] = useState({});

    const StyledBadge = styled(Badge)(({ theme }) => ({
        '& .MuiBadge-badge': {
          right: -3,
          top: 13,
          padding: '0 4px',
        },
      }));

    const searchProducts = async () => {
        if(selectedCategory.id === 0){
            try{
                const response = await productsService.getAll(user.token);
                setProducts(response.data);
            } catch(err){
                console.log(err);
            }
        }
        else{
            try{
                const response = await productsService.getByCategoryId(user.token, selectedCategory.id);
                setProducts(response.data);
            } catch(err){
                console.log(err);
            }
        }
    }

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

            try{
                const response = await categoriesService.getAll(token);
                let resCategories = response.data;
                resCategories = resCategories.map(c => {
                    return { ...c, filter: false };
                });
                setCategories(resCategories);
            } catch(err){
                console.log(err);
            }

            await getOrderDetails();
        }
        loadData();
    }, []); // eslint-disable-line react-hooks/exhaustive-deps

    return (
        <div>
            <Container maxWidth="lg" sx={{ py: 4 }}>
                <Grid container spacing={2}>
                    <Grid item xs={4}>
                        <Paper sx={{ p: 4 }}>
                            <Typography variant="h5" sx={{ pb: 2 }}>Categories</Typography>
                            <FormControl>
                                <RadioGroup
                                    value={selectedCategory.id}
                                    onChange={(e, value) => {
                                        const id = value;
                                        const newCategory = categories.find((c) => c.id === Number(id));
                                        if(newCategory !== undefined){
                                            setSelectedCategory(newCategory);
                                        } else{
                                            setSelectedCategory({ id: 0, name: "All"});
                                        }
                                    }}
                                >
                                    <FormControlLabel value={0} control={<Radio />} label={"All"}/>
                                    {
                                        categories.map(c => {
                                            return (
                                                <FormControlLabel value={c.id} control={<Radio />} label={c.name} key={c.id}/>
                                            );
                                        })
                                    }
                                </RadioGroup>
                            </FormControl>
                            <Button variant='contained' endIcon={<Search />} sx={{ mt: 2 }} fullWidth onClick={searchProducts}>Search</Button>
                        </Paper>
                    </Grid>
                    <Grid item xs={8}>
                        {
                            products.map(p => {
                                return (
                                    <Box key={p.id} sx={{ mb: 2 }}><ProductCard product={p}/></Box>
                                );
                            })
                        }
                        <Box sx={{ p: 4 }}>
                            {   
                                orderDetails.orderItems ?
                                orderDetails.orderItems.map(oi => {
                                    return (
                                        <Box key={oi.id}>
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
                                                    {'$' + oi.product.price * oi.quantity}
                                                </Typography>
                                            </Box>
                                            <Box sx={{ display: 'flex' }}>
                                                <Typography sx={{ pr: 2 }}>Item quantity</Typography>
                                                <AddCircle />
                                                <Typography sx={{ px: 1 }}>
                                                    {oi.quantity}
                                                </Typography>
                                                <RemoveCircle />
                                            </Box>
                                        </Box>
                                    );
                                })
                                : <CircularProgress />
                            }
                            <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                                <Typography>
                                    Total
                                </Typography>
                                <Typography>
                                    ${ 
                                        orderDetails.orderItems ?
                                        orderDetails.orderItems.reduce((prev, oi) => prev + (oi.product.price * oi.quantity), 0) 
                                        : "0"
                                    }
                                </Typography>
                            </Box>
                            <Button variant='contained' fullWidth>Checkout</Button>
                        </Box>
                    </Grid>
                </Grid>
                <Fab color="primary" aria-label="shopping-cart" sx={{ position: 'fixed', bottom: 16, right: 16 }}>
                    <StyledBadge badgeContent={orderDetails.length} color="secondary">
                        <ShoppingCart />
                    </StyledBadge>
                </Fab>
            </Container>
        </div>
    );
}

export default Home;