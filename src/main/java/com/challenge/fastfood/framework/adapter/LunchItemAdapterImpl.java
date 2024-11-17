package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchItemType;
import com.challenge.fastfood.framework.mapstruct.LunchItemMapper;
import com.challenge.fastfood.framework.persistence.lunchItem.LunchItemEntity;
import com.challenge.fastfood.framework.persistence.lunchItem.LunchItemsRepository;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LunchItemAdapterImpl implements LunchItemAdapterInterface {

    private final LunchItemsRepository lunchItemsRepository;
    private final LunchItemMapper lunchItemMapper;

    public LunchItemAdapterImpl(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        this.lunchItemsRepository = lunchItemsRepository;
        this.lunchItemMapper = lunchItemMapper;
    }


    @Override
    public List<LunchItem> findByTypeAndStatusTrue(LunchItemType lunchItemType) {
        List<LunchItemEntity> lunchItemEntity = lunchItemsRepository.findByTypeAndStatusTrue(lunchItemType);
        return lunchItemMapper.lunchItemEntityToLunchItem(lunchItemEntity);
    }

    @Override
    public List<LunchItem> findByStatusTrue() {
        List<LunchItemEntity> byStatusTrue = lunchItemsRepository.findByStatusTrue();
        return  lunchItemMapper.lunchItemEntityToLunchItem(byStatusTrue);
    }

    @Override
    public LunchItem findByName(String name) {
        LunchItemEntity lunchItemEntity = lunchItemsRepository.findByName(name);
        return  lunchItemMapper.lunchItemEntityToLunchItem(lunchItemEntity);
    }

    @Override
    public LunchItem findByIdAndStatusTrue(Long id) {
        LunchItemEntity byIdAndStatusTrue = lunchItemsRepository.findByIdAndStatusTrue(id);
        return lunchItemMapper.lunchItemEntityToLunchItem(byIdAndStatusTrue);
    }

    @Override
    public LunchItem saveLunchItem(LunchItem lunchItem) {
        LunchItemEntity lunchItemEntity = lunchItemMapper.lunchItemToLunchItemEntity(lunchItem);
        LunchItemEntity save = lunchItemsRepository.save(lunchItemEntity);
        return lunchItemMapper.lunchItemEntityToLunchItem(save);
    }

    @Override
    public LunchItem findById(Long id) {
        LunchItemEntity lunchItemEntity = lunchItemsRepository
                .findById(id)
                .orElseThrow(() -> new LunchItemException("Lunch item not found."));
        return  lunchItemMapper.lunchItemEntityToLunchItem(lunchItemEntity);
    }
}
