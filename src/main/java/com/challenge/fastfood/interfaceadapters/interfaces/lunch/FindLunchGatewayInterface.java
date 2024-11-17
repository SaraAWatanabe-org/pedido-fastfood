package com.challenge.fastfood.interfaceadapters.interfaces.lunch;

import com.challenge.fastfood.entities.Lunch;

import java.util.List;

public interface FindLunchGatewayInterface {

    List<Lunch> findLunchs();
    Lunch findLunchById(Long id);
}
