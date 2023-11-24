import * as React from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { Box, Button, CardMedia, Container, FormControl, InputAdornment, MenuItem, 
    OutlinedInput, Paper, Select, SelectChangeEvent, TextField, Typography } from '@mui/material';

import CameraAltOutlinedIcon from '@mui/icons-material/CameraAltOutlined';
import FileUploadOutlinedIcon from '@mui/icons-material/FileUploadOutlined';

import { grey } from '@mui/material/colors';

import productsService from '../../services/productsService';
import categoriesService from '../../services/categoriesService';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const imageInputStyle = { 
    height: 200, 
    display: 'flex', 
    flexDirection: 'column',
    justifyContent: 'space-evenly', 
    alignItems: 'center', 
    borderStyle: 'dashed', 
    borderWidth: 2, 
    borderRadius: 2, 
    borderColor: 'info.main',
    backgroundColor: grey[100],
    cursor: 'pointer'
}

const CreateProduct = () => {
    const navigate = useNavigate();
    
    const [categories, setCategories] = React.useState([]);
    const [name, setName] = React.useState('');
    const [price, setPrice] = React.useState('');
    const [category, setCategory] = React.useState('');
    const [selectedFile, setSelectedFile] = React.useState<File>();

    const handleSetCategory = (event: SelectChangeEvent) => {
        setCategory(event.target.value);
    }

    const handleSetName = (event: React.ChangeEvent<HTMLInputElement>) => {
        setName(event.target.value);
    }

    const handleSetPrice = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPrice(event.target.value);
    }

    const onSelectFile = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files || event.target.files.length === 0) {
            setSelectedFile(undefined)
            return
        }

        setSelectedFile(event.target.files[0]);
    }

    const getCategories = async () => {
        try{
            const response = await categoriesService.getAll();
            const categoriesList = response.data.map(c => {
                return { ...c, filter: false };
            });
            setCategories(categoriesList);
        } catch(err){
            console.log(err);
        }
    }

    const createProduct = async () => {
        try{
            const productForm = new FormData();
            productForm.set('name', name);
            productForm.set('price', price);
            productForm.set('categoryId', category);
            productForm.set('file', selectedFile);

            await productsService.create(productForm);
            
            navigate('/products_admin');
        } catch(error){
            console.log(error.response ? error.response.data : error);
        }
    }

    useEffect(() => {
        getCategories();
    }, []); // eslint-disable-line react-hooks/exhaustive-deps

    return (
        <AdminLayout>
            <Box sx={{ display: 'flex', justifyContent: 'center' }}>
                <Paper sx={{ padding: 3 }} component={Container} maxWidth="md">
                    <Box sx={{ marginBottom: 3 }}>
                        <Typography variant='h6' sx={{ paddingBottom: 1 }}>Name</Typography>
                        <TextField value={name} onChange={handleSetName} fullWidth variant='outlined' placeholder='Product name'/>
                    </Box>
                    <Box sx={{ marginBottom: 3 }}>
                        <Typography variant='h6' sx={{ paddingBottom: 1 }}>Image</Typography>
                        {
                            !selectedFile &&
                            <Box sx={imageInputStyle} component='label'>
                                <input type="file" hidden onChange={onSelectFile} />
                                <CameraAltOutlinedIcon sx={{ fontSize: 100, color: grey[700] }}/>
                                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                                    <FileUploadOutlinedIcon color='info' sx={{ paddingRight: 1 }}/>
                                    <Typography variant='body2'>Drop your files here or browse</Typography>
                                </Box>
                            </Box>
                        }
                        { 
                            selectedFile &&  
                            <Box component='label' sx={{ cursor: 'pointer' }}>
                                <input type="file" hidden onChange={onSelectFile} />
                                <CardMedia
                                    component="img"
                                    sx={{ width: 200, height: 200 }}
                                    image={URL.createObjectURL(selectedFile)}
                                    alt="Product image"
                                />
                            </Box>
                        }
                    </Box>
                    <Box sx={{ marginBottom: 3 }}>
                        <Typography variant='h6' sx={{ paddingBottom: 1 }}>Category</Typography>
                        <Select
                            value={category}
                            onChange={handleSetCategory}
                            placeholder='Product category'
                            fullWidth
                        >
                            {
                                categories.map(c => (
                                    <MenuItem key={c.id} value={c.id}>{ c.name }</MenuItem>
                                ))
                            }
                        </Select>
                    </Box>
                    <Box sx={{ marginBottom: 3 }}>
                        <Typography variant='h6' sx={{ paddingBottom: 1 }}>Price</Typography>
                        <FormControl fullWidth>
                            <OutlinedInput
                                type='number' value={price} onChange={handleSetPrice}
                                startAdornment={<InputAdornment position="start">$</InputAdornment>}
                            />
                        </FormControl>
                    </Box>          
                    <Button fullWidth variant='contained' onClick={createProduct}>Create product</Button>
                </Paper>
            </Box>
        </AdminLayout>
    )
}

export default CreateProduct;