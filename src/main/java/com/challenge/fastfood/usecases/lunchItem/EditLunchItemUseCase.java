package com.challenge.fastfood.usecases.lunchItem;


import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.EditLunchItemGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.entities.LunchItem;

public class EditLunchItemUseCase {

    public static final boolean STATUS = false;
    private final EditLunchItemGatewayInterface editLunchItemGatewayInterface;
    private final FindLunchItemsGatewayInterface findLunchItemsGatewayInterface;

    public EditLunchItemUseCase(EditLunchItemGatewayInterface editLunchItemGatewayInterface, FindLunchItemsGatewayInterface findLunchItemsGatewayInterface) {
        this.editLunchItemGatewayInterface = editLunchItemGatewayInterface;
        this.findLunchItemsGatewayInterface = findLunchItemsGatewayInterface;
    }

    public Boolean editStatusLunchItem(Long idLunchItem) {

        if (idLunchItem == null) {
            throw new LunchItemException("idLunchItem and status are required");
        }

        LunchItem lunchItem = findLunchItemsGatewayInterface.findLunchItemById(idLunchItem);

        if (lunchItem == null) {
            throw new LunchItemException("Lunch item not found");
        }

        return editLunchItemGatewayInterface.editStatusLunchItem(idLunchItem, STATUS);
    }

    public LunchItem editLunchItem(Long idLunchItem, LunchItem lunchItem) {

        if (idLunchItem == null || lunchItem == null) {
            throw new LunchItemException("idLunchItem and lunchItem are required");
        }

        LunchItem lunchItemById = findLunchItemsGatewayInterface.findLunchItemById(idLunchItem);

        if (lunchItemById == null) {
            throw new LunchItemException("Lunch item not found");
        }

        if (lunchItem.getPrice() <= 0) {
            throw new LunchItemException("Price must be greater than 0");
        }


        return editLunchItemGatewayInterface.editLunchItem(lunchItem, idLunchItem);
    }

}
