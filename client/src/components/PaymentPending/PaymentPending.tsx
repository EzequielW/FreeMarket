import * as React from 'react';
import { Container, Typography } from '@mui/material';
import PendingIcon from '@mui/icons-material/Pending';

import MainLayout from '../../layouts/MainLayout';

const PaymentPending = () => {
    return (
        <MainLayout>
            <Container sx={{ py: 16, textAlign: 'center' }} maxWidth="md">
                <PendingIcon color="info" sx={{ fontSize: '220px', pb: 3 }}/>
                <Typography variant="h4">Payment is still processing, please await confirmation.</Typography>
            </Container>
        </MainLayout>
    );
}

export default PaymentPending;