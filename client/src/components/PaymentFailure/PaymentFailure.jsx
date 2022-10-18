import { Container, Typography } from '@mui/material';
import ErrorIcon from '@mui/icons-material/Error';

import Header from '../Header/Header';

const PaymentPending = ({user}) => {
    return (
        <div>
            <Header />
            <Container sx={{ py: 16, textAlign: 'center' }} maxWidth="md">
                <ErrorIcon color="error" sx={{ fontSize: '220px', pb: 3 }}/>
                <Typography variant="h4">Couldn't process payment, please try again or contact support.</Typography>
            </Container>
        </div>
    );
}

export default PaymentPending;