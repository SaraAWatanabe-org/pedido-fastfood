package com.challenge.fastfood.interfaceadapters.interfaces.lunch;

import com.challenge.fastfood.entities.Lunch;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductionLunchGatewayInterface {

    void toProductionLunch(Lunch lunch) throws JsonProcessingException;
}
