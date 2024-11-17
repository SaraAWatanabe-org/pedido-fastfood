package com.challenge.fastfood.interfaceadapters.gateways.lunch;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.ProductionLunchGatewayInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ProductionLunchGatewayImpl implements ProductionLunchGatewayInterface {

    private final LunchAdapterInterface lunchAdapter;

    public ProductionLunchGatewayImpl(LunchAdapterInterface lunchAdapter) {
        this.lunchAdapter = lunchAdapter;
    }


    @Override
    public void toProductionLunch(Lunch lunch) throws JsonProcessingException {
        this.lunchAdapter.httpRequestPrepareLunch(lunch);
    }
}
