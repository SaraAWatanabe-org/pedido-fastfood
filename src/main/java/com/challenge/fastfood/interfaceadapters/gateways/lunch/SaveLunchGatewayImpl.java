package com.challenge.fastfood.interfaceadapters.gateways.lunch;

import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.SaveLunchGatewayInterface;
import com.challenge.fastfood.entities.Lunch;

import jakarta.transaction.Transactional;




public class SaveLunchGatewayImpl implements SaveLunchGatewayInterface {


    private final LunchAdapterInterface lunchAdapter;

    public SaveLunchGatewayImpl(LunchAdapterInterface lunchAdapter) {
        this.lunchAdapter = lunchAdapter;
    }


    @Override
    @Transactional
    public Lunch saveLunch(Lunch lunch) {
        return lunchAdapter.saveLunch(lunch);
    }
}
