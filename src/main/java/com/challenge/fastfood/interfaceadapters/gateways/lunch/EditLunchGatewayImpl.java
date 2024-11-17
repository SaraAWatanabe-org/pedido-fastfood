package com.challenge.fastfood.interfaceadapters.gateways.lunch;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.EditLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;

public class EditLunchGatewayImpl implements EditLunchGatewayInterface {

    private final LunchAdapterInterface lunchAdapterInterface;

    public EditLunchGatewayImpl(LunchAdapterInterface lunchAdapterInterface) {
        this.lunchAdapterInterface = lunchAdapterInterface;
    }

    @Override
    public Lunch editLunch(Lunch lunch) {
        return lunchAdapterInterface.editLunch(lunch);
    }
}
