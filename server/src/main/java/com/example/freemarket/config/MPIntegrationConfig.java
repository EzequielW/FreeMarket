package com.example.freemarket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.mercadopago.core.MPRequestOptions;

@Service
public class MPIntegrationConfig {
    @Value("${spring.datasource.mp-token}")
	private String ACCESS_TOKEN;

    @Bean
    public MPRequestOptions getRequestOptions(){
        MPRequestOptions requestOptions = MPRequestOptions.builder()
            .accessToken(ACCESS_TOKEN)
            .build();
        return requestOptions;
    }
}
