package com.example.freemarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.freemarket.config.MPIntegrationConfig;
import com.example.freemarket.model.OrderDetails;
import com.example.freemarket.model.OrderItem;
import com.example.freemarket.model.User;
import com.example.freemarket.service.IMPIntegrationService;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

@Service
public class MPIntegrationService implements IMPIntegrationService {
    @Value("${spring.datasource.react-app-url}")
	private String REACT_APP_URL;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    MPIntegrationConfig mpIntegrationConfig;

    public String checkoutRequest(User user) throws MPException, MPApiException{
        OrderDetails orderDetails = orderDetailsService.getActive(user);

        // Create a preference object
        PreferenceClient client = new PreferenceClient();

        // Create a preference item
        List<PreferenceItemRequest> items = new ArrayList<>();
        
        for(OrderItem oi: orderDetails.getOrderItems()){
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                .id(oi.getProduct().getId().toString())
                .title(oi.getProduct().getName())
                .quantity(oi.getQuantity())
                .unitPrice(oi.getProduct().getPrice())
                .currencyId("USD")
                .build();
            items.add(item);
        }

        String feedbackURL = REACT_APP_URL + "payments/feedback";
        PreferenceRequest request = PreferenceRequest.builder()
            .items(items)
            .backUrls(
                PreferenceBackUrlsRequest.builder()
                    .success(feedbackURL + "/success")
                    .failure(feedbackURL + "/failure")
                    .pending(feedbackURL + "/pending")
                    .build())
            .autoReturn("approved")
            .build();

        Preference preference = client.create(request, mpIntegrationConfig.getRequestOptions());

        return preference.getId();
    }
}
