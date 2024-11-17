package com.challenge.fastfood.interfaceadapters.interfaces.lunchItem;


import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchItemType;

import java.util.List;

public interface FindLunchItemsGatewayInterface {

    LunchItem findLunchItemById(Long id);
    List<LunchItem> findLunchItems(LunchItemType type);
    LunchItem findLunchItemByName(String name);
}
