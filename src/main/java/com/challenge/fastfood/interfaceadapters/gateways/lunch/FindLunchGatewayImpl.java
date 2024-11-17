package com.challenge.fastfood.interfaceadapters.gateways.lunch;

import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.entities.Lunch;

import java.util.List;


public class FindLunchGatewayImpl implements FindLunchGatewayInterface {

    private final LunchAdapterInterface lunchAdapter;

    public FindLunchGatewayImpl(LunchAdapterInterface lunchAdapter) {
        this.lunchAdapter = lunchAdapter;
    }


    @Override
    public List<Lunch> findLunchs() {
        return lunchAdapter.findLunchs();
    }

    @Override
    public Lunch findLunchById(Long id) {
        return lunchAdapter.findLunchById(id);
    }
}
