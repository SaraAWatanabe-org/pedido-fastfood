package com.challenge.fastfood.interfaceadapters.interfaces.lunch;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface LunchAdapterInterface {

    List<Lunch> findLunchs();
    Lunch findLunchById(Long id);
    Lunch saveLunch(Lunch lunch);
    Lunch editLunch(Lunch lunch);
    void httpRequestPrepareLunch(Lunch lunch) throws JsonProcessingException;
}
