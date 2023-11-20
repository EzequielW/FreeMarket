import * as React from 'react';
import { useEffect, useState } from 'react';
import { Paper, FormControl, RadioGroup, FormControlLabel, Typography, Button,
    Radio } from '@mui/material';
import { Search } from '@mui/icons-material';

import productsService from '../../services/productsService';
import categoriesService from '../../services/categoriesService';

const CategoryFilter = ({token, setProducts}) => {
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState({ id: 0, name: "All"});

    const searchProducts = async () => {
        if(selectedCategory.id === 0){
            try{
                const response = await productsService.getAll(token);
                setProducts(response.data);
            } catch(err){
                console.log(err);
            }
        }
        else{
            try{
                const response = await productsService.getByCategoryId(token, selectedCategory.id);
                setProducts(response.data);
            } catch(err){
                console.log(err);
            }
        }
    }

    useEffect(() => {
        const loadData = async () => {
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
    );
}

export default CategoryFilter;