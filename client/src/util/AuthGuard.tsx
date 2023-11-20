import * as React from 'react';
import { Navigate } from "react-router-dom";

const AuthGuard = ({ children }) => {
    const token = sessionStorage.getItem('token');
    const id = sessionStorage.getItem('id');
    const role = sessionStorage.getItem('role');
    const name = sessionStorage.getItem('name');

    const user = {
        id: id,
        name: name,
        role: role,
        token: token
    }

    return (
        token ? React.cloneElement(children, { user }) : <Navigate to='/login' replace/>
    )
}

export default AuthGuard;