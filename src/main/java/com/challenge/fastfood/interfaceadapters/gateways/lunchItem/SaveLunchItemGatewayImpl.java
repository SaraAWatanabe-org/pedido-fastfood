package com.challenge.fastfood.interfaceadapters.gateways.lunchItem;

import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.SaveLunchItemGatewayInterface;
import com.challenge.fastfood.entities.LunchItem;
import jakarta.transaction.Transactional;


public class SaveLunchItemGatewayImpl implements SaveLunchItemGatewayInterface {

    private final LunchItemAdapterInterface lunchItemAdapter;

    public SaveLunchItemGatewayImpl(LunchItemAdapterInterface lunchItemAdapter) {
        this.lunchItemAdapter = lunchItemAdapter;
    }


    @Override
    @Transactional
    public LunchItem saveLunchItem(LunchItem lunchItem) {
        return lunchItemAdapter.saveLunchItem(lunchItem);

    }
}
