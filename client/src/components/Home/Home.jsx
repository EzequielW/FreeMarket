import React from 'react';
import { Container, Grid, Box, Paper, 
    Checkbox, FormControlLabel } from '@mui/material';

import ProductCard from './ProductCard';

const Home = () => {
    const products = [
        {
            id: 1,
            name: "AMD 5600x 4.5Ghz",
            price: "1200",
            category: {
                id: 1,
                name: "CPU"
            }
        },
        {
            id: 2,
            name: "INTEL i7 7700K 5.0Ghz",
            price: "999.99",
            category: {
                id: 1,
                name: "CPU"
            }
        },
        {
            id: 3,
            name: "GIGABYTE GeForce RTX 3060 Eagle OC 12G (REV2.0) Graphics Card, 2X WINDFORCE Fans, 12GB 192-bit GDDR6, GV-N3060EAGLE OC-12GD REV2.0 Video Card",
            price: "499.99",
            category: {
                id: 2,
                name: "GPU"
            }
        }
    ]

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