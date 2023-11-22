import * as React from 'react';
import { Avatar, Box, Container, Grid, Paper, Typography,  } from '@mui/material';
import { PieChart } from '@mui/x-charts/PieChart';
import { LineChart } from '@mui/x-charts/LineChart';

import AttachMoneyIcon from '@mui/icons-material/AttachMoney';
import PersonIcon from '@mui/icons-material/Person';
import InventoryIcon from '@mui/icons-material/Inventory';

import CustomizedTable from './CustomTable';
import AdminLayout from '../../layouts/AdminLayout';

const Dashboard = ({user}) => {
    const topCategories = [
        {
            data: [
                { id: 0, value: 10, label: 'Procesors' },
                { id: 1, value: 15, label: 'Graphic cards' },
                { id: 2, value: 17, label: 'Power supplys' },
                { id: 3, value: 20, label: 'SSD' },
            ],
            innerRadius: 60,
            outerRadius: 100,
            paddingAngle: 2
        },
    ];

    return (
        <AdminLayout>
            <Container maxWidth='xl' sx={{ py: 4 }}>
                <Grid container spacing={2}>
                    <Grid item xs={4}>
                        <Paper sx={{ padding: 3, display: 'flex', justifyContent: 'space-between', alignItems: 'center', backgroundColor: 'warning.main' }}>
                            <Box>
                                <Typography variant='subtitle1' sx={{ color: 'white' }}>
                                    Total Sales 
                                </Typography>
                                <Typography variant='h5' sx={{ color: 'white', fontWeight: 700 }}>
                                    $18.420 
                                </Typography>
                            </Box>
                            <Avatar sx={{ backgroundColor: 'white', padding: 1.5 }}>
                                <AttachMoneyIcon fontSize='large' color='warning' />
                            </Avatar>
                        </Paper>
                    </Grid>
                    <Grid item xs={4}>
                        <Paper sx={{ padding: 3, display: 'flex', justifyContent: 'space-between', alignItems: 'center', backgroundColor: 'primary.main' }}>
                            <Box>
                                <Typography variant='subtitle1' sx={{ color: 'white' }}>
                                    Total Users 
                                </Typography>
                                <Typography variant='h5' sx={{ color: 'white', fontWeight: 700 }}>
                                    456
                                </Typography>
                            </Box>
                            <Avatar sx={{ backgroundColor: 'white', padding: 1.5 }}>
                                <PersonIcon fontSize='large' color='primary' />
                            </Avatar>
                        </Paper>
                    </Grid>
                    <Grid item xs={4}>
                        <Paper sx={{ padding: 3, display: 'flex', justifyContent: 'space-between', alignItems: 'center', backgroundColor: 'secondary.main' }}>
                            <Box>
                                <Typography variant='subtitle1' sx={{ color: 'white' }}>
                                    Total Products 
                                </Typography>
                                <Typography variant='h5' sx={{ color: 'white', fontWeight: 700 }}>
                                    34
                                </Typography>
                            </Box>
                            <Avatar sx={{ backgroundColor: 'white', padding: 1.5 }}>
                                <InventoryIcon fontSize='large' color='secondary' />
                            </Avatar>
                        </Paper>
                    </Grid>
                    <Grid item xs={4}>
                        <Paper sx={{ padding: 2 }}>
                            <PieChart
                                series={topCategories}
                                height={300}
                                margin={{ top: 100, bottom: 150, left: 100, right:100 }}
                                slotProps={{
                                    legend: {
                                      direction: 'row',
                                      position: { vertical: 'bottom', horizontal: 'middle' },
                                      padding: 0,
                                    }
                                }}
                            />
                        </Paper>
                    </Grid>
                    <Grid item xs={8}>
                        <Paper sx={{ padding: 2 }}>
                            <LineChart
                                xAxis={[{ data: [1, 2, 3, 5, 8, 10] }]}
                                series={[
                                    {
                                        data: [2, 5.5, 2, 8.5, 1.5, 5],
                                    },
                                ]}
                                height={300}
                            />
                        </Paper>
                    </Grid>
                    <Grid item xs={12}>
                        <CustomizedTable />
                    </Grid>
                </Grid>
            </Container>
        </AdminLayout>
    );
}

export default Dashboard;