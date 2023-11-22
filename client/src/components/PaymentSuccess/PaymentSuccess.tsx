import * as React from 'react';
import { Container, Typography } from '@mui/material';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';

import MainLayout from '../../layouts/MainLayout';

const PaymentSuccess = () => {
    return (
        <MainLayout>
            <Container sx={{ py: 16, textAlign: 'center' }} maxWidth="md">
                <CheckCircleIcon color="success" sx={{ fontSize: '220px', pb: 3 }}/>
                <Typography variant="h4">Payment was successfully processed, your product it's on is way.</Typography>
            </Container>
        </MainLayout>
    );
}

export default PaymentSuccess;