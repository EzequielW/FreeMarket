import * as React from 'react';
import { Container, Typography } from '@mui/material';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';

import Header from '../Header/Header';

const PaymentSuccess = () => {
    return (
        <div>
            <Header />
            <Container sx={{ py: 16, textAlign: 'center' }} maxWidth="md">
                <CheckCircleIcon color="success" sx={{ fontSize: '220px', pb: 3 }}/>
                <Typography variant="h4">Payment was successfully processed, your product it's on is way.</Typography>
            </Container>
        </div>
    );
}

export default PaymentSuccess;