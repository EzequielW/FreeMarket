import * as React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import './index.css';

import Login from './components/Login/Login';
import Home from './components/Home/Home';
import Orders from './components/Orders/Orders';
import Dashboard from './components/Dashboard/Dashboard';
import PaymentSuccess from './components/PaymentSuccess/PaymentSuccess';
import PaymentPending from './components/PaymentPending/PaymentPending';
import PaymentFailure from './components/PaymentFailure/PaymentFailure';

import AuthGuard from './util/AuthGuard';
import LoginGuard from './util/LoginGuard';
import ProductsAdmin from './components/ProductsAdmin/ProductsAdmin';
import OrdersAdmin from './components/OrdersAdmin/OrdersAdmin';
import SettingsAdmin from './components/SettingsAdmin/SettingsAdmin';
import CreateProduct from './components/CreateProduct/CreateProduct';

const container = document.getElementById('root')!;
const root = createRoot(container);
root.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={ <Navigate to="/products" /> } />
      <Route path="/products" element={ <AuthGuard><Home user={undefined}/></AuthGuard> } />
      <Route path="/orders" element={ <AuthGuard><Orders user={undefined}/></AuthGuard> } />
      <Route path="/dashboard" element={ <AuthGuard><Dashboard user={undefined}/></AuthGuard> } />
      <Route path="/products_admin" element={ <AuthGuard><ProductsAdmin /></AuthGuard> } />
      <Route path="/products_admin/create" element={ <AuthGuard><CreateProduct /></AuthGuard> } />
      <Route path="/orders_admin" element={ <AuthGuard><OrdersAdmin /></AuthGuard> } />
      <Route path="/settings_admin" element={ <AuthGuard><SettingsAdmin /></AuthGuard> } />
      <Route path="login" element={ <LoginGuard><Login/></LoginGuard> } />
      <Route path="payments/feedback/success" element={ <AuthGuard><PaymentSuccess /></AuthGuard> } />
      <Route path="payments/feedback/failure" element={ <AuthGuard><PaymentFailure /></AuthGuard> } />
      <Route path="payments/feedback/pending" element={ <AuthGuard><PaymentPending /></AuthGuard> } />
    </Routes>
  </BrowserRouter>
);