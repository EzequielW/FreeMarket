import React from 'react'
import { Container, Typography, TextField, Button, 
    Checkbox, FormControlLabel, Paper, CircularProgress, Snackbar, Alert } from '@mui/material';
import { useNavigate } from "react-router-dom";
import authService from '../../services/authService';

const Login = () => {
    const navigate = useNavigate();
    const [credentials, setCredentials] = React.useState({ email: '', password: ''});
    const [loading, setLoading] = React.useState(false);
    const [validationError, setValidationError] = React.useState(false);
    const [serverError, setServerError] = React.useState(false);

    const handleEmail = (value) => {
        setCredentials({
            ...credentials,
            email: value
        });
    }

    const handlePassword = (value) => {
        setCredentials({
            ...credentials,
            password: value
        });
    }

    const handleLogin = async () => {
        setLoading(true);
        try{
            const response = await authService.login(credentials);
            const data = response.data;
            const token = response.headers.authorization;
            
            sessionStorage.setItem('id', data.id);
            sessionStorage.setItem('name', data.name);
            sessionStorage.setItem('role', data.role);
            sessionStorage.setItem('token', token ? token : '');

            navigate('/');
        } catch(err){
            if(err.response.status === 401){
                setValidationError(true);
            }
            else{
                setServerError(true);
            }
        }
        setLoading(false);
    }

    const closeError = () => {
        setServerError(false);
    }

    return (
        <div>
            <Container sx={{ py: 16 }} maxWidth="sm">
                <Paper elevation={3} sx={{ p: 6 }}>
                    <Typography variant="h4" align='center' sx={{ pb:6 }}>
                        Log in
                    </Typography>
                    <TextField 
                        id="email" 
                        label="Email" 
                        type="text" 
                        fullWidth
                        sx={{ pb:3 }}
                        value={credentials.email}
                        onChange={(e) => handleEmail(e.target.value)}
                        error={validationError} 
                        helperText={validationError ? 'Invalid username or password' : ''}
                        />
                    <TextField 
                        id="password" 
                        label="Contraseña" 
                        type="password" 
                        fullWidth
                        sx={{ pb:2 }}
                        value={credentials.password}
                        onChange={(e) => handlePassword(e.target.value)}
                        error={validationError} 
                        helperText={validationError ? 'Invalid username or password' : ''}
                        />
                    <FormControlLabel sx={{ pb:3 }} control={<Checkbox />} label="Remind me" />
                    <Button variant="contained" fullWidth onClick={handleLogin} disabled={loading} size="large">
                        { loading &&
                            <CircularProgress
                                size={24}
                                sx={{
                                    position: 'absolute',
                                    top: '50%',
                                    left: '50%',
                                    marginTop: '-12px',
                                    marginLeft: '-12px',
                                }}
                            /> } 
                        Confirm
                    </Button>
                </Paper>
            </Container> 
            <Snackbar open={serverError} onClose={closeError} anchorOrigin={{ vertical: 'top', horizontal: 'center' }}>
                <Alert onClose={closeError} severity="error">
                    Unable to connect to server, please try again later.
                </Alert>
            </Snackbar>
        </div>
    );
}
  
export default Login