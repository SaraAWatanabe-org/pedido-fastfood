package com.challenge.fastfood.interfaceadapters.gateways.lunchItem;

import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchItemType;

import java.util.List;


public class FindLunchItemsGatewayImpl implements FindLunchItemsGatewayInterface {

    private final LunchItemAdapterInterface lunchItemAdapter;

    public FindLunchItemsGatewayImpl( LunchItemAdapterInterface lunchItemAdapter) {
        this.lunchItemAdapter = lunchItemAdapter;
    }

    @Override
    public List<LunchItem> findLunchItems(LunchItemType type) {
        List<LunchItem> lunchItem;
        if (type == null) {
            lunchItem = lunchItemAdapter.findByStatusTrue();
        } else {
            lunchItem = lunchItemAdapter.findByTypeAndStatusTrue(type);
        }
        return lunchItem;
    }

    @Override
    public LunchItem findLunchItemByName(String name) {
        return lunchItemAdapter.findByName(name);
    }

    @Override
    public LunchItem findLunchItemById(Long id) {
        return lunchItemAdapter.findByIdAndStatusTrue(id);
    }

}
