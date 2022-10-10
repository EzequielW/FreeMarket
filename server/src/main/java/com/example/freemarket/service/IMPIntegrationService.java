package com.example.freemarket.service;

import org.springframework.stereotype.Service;

import com.example.freemarket.model.User;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

@Service
public interface IMPIntegrationService {
    String checkoutRequest(User user) throws MPException, MPApiException;
}
