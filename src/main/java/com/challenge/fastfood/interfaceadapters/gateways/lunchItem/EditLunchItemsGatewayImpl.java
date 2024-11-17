package com.challenge.fastfood.interfaceadapters.gateways.lunchItem;

import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.EditLunchItemGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.entities.LunchItem;
import jakarta.transaction.Transactional;


public class EditLunchItemsGatewayImpl implements EditLunchItemGatewayInterface {


    private final LunchItemAdapterInterface lunchItemAdapter;

    public EditLunchItemsGatewayImpl(LunchItemAdapterInterface lunchItemAdapter) {
        this.lunchItemAdapter = lunchItemAdapter;
    }


    @Override
    @Transactional
    public Boolean editStatusLunchItem(Long idLunchItem, Boolean status) {
        try {
            LunchItem lunchItemsRepositoryById = lunchItemAdapter.findById(idLunchItem);
            lunchItemsRepositoryById.setStatus(status);
            lunchItemAdapter.saveLunchItem(lunchItemsRepositoryById);
            return true;

    }   catch (Exception e) {
            throw new LunchItemException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public LunchItem editLunchItem(LunchItem lunchItem, Long idLunchItem) {
        LunchItem toLunchItem ;
        try {

            LunchItem item = lunchItemAdapter.findById(idLunchItem);
            item.setStatus(true);
            item.setName(lunchItem.getName());
            item.setType(lunchItem.getType());
            item.setPrice(lunchItem.getPrice());
            toLunchItem = lunchItemAdapter.saveLunchItem(item);

        } catch (Exception e) {
            throw new LunchItemException(e.getMessage());
        }
        return toLunchItem;
    }
}
