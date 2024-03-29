import * as React from 'react';
import Header from "../components/Header/Header";

const MainLayout = ({ children }) => {

    return (
        <div>
            <Header />
            <div>{ children }</div>
        </div>
    );
}

export default MainLayout;