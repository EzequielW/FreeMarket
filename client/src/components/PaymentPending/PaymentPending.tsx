import * as React from 'react';
import { Container, Typography } from '@mui/material';
import PendingIcon from '@mui/icons-material/Pending';

import Header from '../Header/Header';

const PaymentPending = () => {
    return (
        <div>
            <Header />
            <Container sx={{ py: 16, textAlign: 'center' }} maxWidth="md">
                <PendingIcon color="info" sx={{ fontSize: '220px', pb: 3 }}/>
                <Typography variant="h4">Payment is still processing, please await confirmation.</Typography>
            </Container>
        </div>
    );
}

export default PaymentPending;