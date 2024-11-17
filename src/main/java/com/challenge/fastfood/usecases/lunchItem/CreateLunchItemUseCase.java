package com.challenge.fastfood.usecases.lunchItem;

import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.SaveLunchItemGatewayInterface;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.entities.LunchItem;

public class CreateLunchItemUseCase {

    private final SaveLunchItemGatewayInterface saveLunchItemGatewayInterface;

    private final FindLunchItemsGatewayInterface findLunchItemsGatewayInterface;

    public CreateLunchItemUseCase(SaveLunchItemGatewayInterface saveLunchItemGatewayInterface, FindLunchItemsGatewayInterface findLunchItemsGatewayInterface) {
        this.saveLunchItemGatewayInterface = saveLunchItemGatewayInterface;
        this.findLunchItemsGatewayInterface = findLunchItemsGatewayInterface;
    }

    public LunchItem createLunchItem(LunchItem lunchItem) {

        LunchItem lunchItemByName = findLunchItemsGatewayInterface.findLunchItemByName(lunchItem.getName());
        if(lunchItemByName != null){
            throw new LunchItemException("LunchItem already exists");
        }

        lunchItem.setStatus(true);

        return saveLunchItemGatewayInterface.saveLunchItem(lunchItem);
    }
}
