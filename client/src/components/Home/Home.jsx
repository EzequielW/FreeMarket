import { useState, useEffect } from 'react';
import { Container, Grid, Box, Paper, 
    Checkbox, FormControlLabel } from '@mui/material';

import ProductCard from './ProductCard';
import productsService from '../../services/productsService';
import categoriesService from '../../services/categoriesService';

const Home = ({user}) => {
    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);

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
                setCategories(response.data);
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
                            {
                                categories.map(c => {
                                    return (
                                        <Box sx={{ display: 'flex' }}>
                                            <FormControlLabel
                                                control={
                                                <Checkbox checked={false} onChange={() => {}} name={c.name} />
                                                }
                                                label={c.name}
                                            />
                                        </Box> 
                                    );
                                })
                            }
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