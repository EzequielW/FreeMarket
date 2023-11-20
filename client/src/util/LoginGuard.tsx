import * as React from 'react';
import { Navigate } from "react-router-dom";

const LoginGuard = ({ children }) => {
    const token = sessionStorage.getItem('token');

    return (
        token ? <Navigate to='/' replace/> : children
    )
}

export default LoginGuard;