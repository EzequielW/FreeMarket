import { useState, useEffect } from 'react';
import { Container, Grid, Box, Paper, 
    Checkbox, FormControlLabel, Typography, Button } from '@mui/material';
import { Search } from '@mui/icons-material';

import ProductCard from './ProductCard';
import productsService from '../../services/productsService';
import categoriesService from '../../services/categoriesService';

const Home = ({user}) => {
    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);

    const updateCategory = (category) => {
        let tempCategories = categories;
        
        tempCategories = tempCategories.map(c => {
            if(c.id === category.id){
                return category;
            } else{
                return c;
            }
        });

        setCategories(tempCategories);
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
                            {
                                categories.map(c => {
                                    return (
                                        <Box sx={{ display: 'flex' }} key={c.id}>
                                            <FormControlLabel
                                                control={
                                                <Checkbox checked={c.filter} onChange={() => {
                                                    const cat = c;
                                                    cat.filter = !cat.filter;
                                                    updateCategory(cat);
                                                }} name={c.name} />
                                                }
                                                label={c.name}
                                            />
                                        </Box> 
                                    );
                                })
                            }
                            <Button variant='contained' endIcon={<Search />} sx={{ mt: 2 }} fullWidth>Search</Button>
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
                    </Grid>
                </Grid>
            </Container>
        </div>
    );
}

export default Home;