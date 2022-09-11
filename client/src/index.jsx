import React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";

import './index.css';

import Home from './components/Home/Home';
import Login from './components/Login/Login';

import AuthGuard from './util/AuthGuard';
import LoginGuard from './util/LoginGuard';

const container = document.getElementById('root');
const root = createRoot(container);
root.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={ <AuthGuard><Home/></AuthGuard> } />
      <Route path="login" element={ <LoginGuard><Login/></LoginGuard> } />
    </Routes>
  </BrowserRouter>
);