import * as React from 'react';
import { Container, Typography } from '@mui/material';
import ErrorIcon from '@mui/icons-material/Error';

import MainLayout from '../../layouts/MainLayout';

const PaymentPending = () => {
    return (
        <MainLayout>
            <Container sx={{ py: 16, textAlign: 'center' }} maxWidth="md">
                <ErrorIcon color="error" sx={{ fontSize: '220px', pb: 3 }}/>
                <Typography variant="h4">Couldn't process payment, please try again or contact support.</Typography>
            </Container>
        </MainLayout>
    );
}

export default PaymentPending;