import { useState, useEffect } from 'react';
import { Container, Grid, Box, Paper, 
    Checkbox, FormControlLabel } from '@mui/material';

import ProductCard from './ProductCard';
import productsService from '../../services/productsService';

const Home = ({user}) => {
    const [products, setProducts] = useState([]);

    useEffect((token = user.token) => {
        const loadData = async () => {
            try{
                const response = await productsService.getAll(token);
                console.log(response.data);
                setProducts(response.data);
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
                            <Box sx={{ display: 'flex' }}>
                                <FormControlLabel
                                    control={
                                    <Checkbox checked={true} onChange={() => {}} name="CPU" />
                                    }
                                    label="Processors"
                                />
                            </Box>
                            <Box sx={{ display: 'flex' }}>
                                <FormControlLabel
                                    control={
                                    <Checkbox checked={true} onChange={() => {}} name="GPU" />
                                    }
                                    label="Graphic cards"
                                />
                            </Box>
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