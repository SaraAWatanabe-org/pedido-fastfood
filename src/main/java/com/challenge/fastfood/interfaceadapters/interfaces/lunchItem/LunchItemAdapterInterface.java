package com.challenge.fastfood.interfaceadapters.interfaces.lunchItem;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchItemType;
import com.challenge.fastfood.framework.persistence.lunchItem.LunchItemEntity;

import java.util.List;

public interface LunchItemAdapterInterface {

    List<LunchItem> findByTypeAndStatusTrue(LunchItemType lunchItemType);
    List<LunchItem> findByStatusTrue();
    LunchItem findByName(String name);
    LunchItem findByIdAndStatusTrue(Long id);
    LunchItem saveLunchItem(LunchItem lunchItem);
    LunchItem findById(Long id);

}
