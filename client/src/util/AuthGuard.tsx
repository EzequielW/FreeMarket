import * as React from 'react';
import { Navigate } from "react-router-dom";

const AuthGuard = ({ children }) => {
    const token = sessionStorage.getItem('token');

    return (
        token ? React.cloneElement(children) : <Navigate to='/login' replace/>
    )
}

export default AuthGuard;