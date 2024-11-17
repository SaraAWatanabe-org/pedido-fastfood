package com.challenge.fastfood.usecases.lunch;

import com.challenge.fastfood.interfaceadapters.interfaces.lunch.SaveLunchGatewayInterface;
import com.challenge.fastfood.entities.Lunch;

public class CreateLunchUseCase {

    private final SaveLunchGatewayInterface saveLunchGatewayInterface;

    public CreateLunchUseCase(SaveLunchGatewayInterface saveLunchGatewayInterface) {
        this.saveLunchGatewayInterface = saveLunchGatewayInterface;
    }


    public Lunch createLunch(Lunch lunch) {
        return saveLunchGatewayInterface.saveLunch(lunch);
    }


}
