package com.challenge.fastfood.interfaceadapters.interfaces.lunchItem;


import com.challenge.fastfood.entities.LunchItem;

public interface EditLunchItemGatewayInterface {

    Boolean editStatusLunchItem(Long idLunchItem, Boolean status);

    LunchItem editLunchItem(LunchItem lunchItem, Long idLunchItem);
}
