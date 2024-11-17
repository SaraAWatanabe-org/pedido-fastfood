package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.interfaceadapters.controller.request.LunchItemRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.LunchItemResponse;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchItemType;
import com.challenge.fastfood.interfaceadapters.gateways.lunchItem.EditLunchItemsGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.lunchItem.FindLunchItemsGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.lunchItem.SaveLunchItemGatewayImpl;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.EditLunchItemGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.SaveLunchItemGatewayInterface;
import com.challenge.fastfood.interfaceadapters.presenter.LunchItemsPresenter;
import com.challenge.fastfood.usecases.lunchItem.CreateLunchItemUseCase;
import com.challenge.fastfood.usecases.lunchItem.EditLunchItemUseCase;
import com.challenge.fastfood.usecases.lunchItem.FindLunchItemsUseCase;

import java.util.List;

public class LunchItemsController {


    private final LunchItemAdapterInterface lunchItemAdapter;

    public LunchItemsController(LunchItemAdapterInterface lunchItemAdapter) {
        this.lunchItemAdapter = lunchItemAdapter;
    }


    public LunchItemResponse createLunchItem(LunchItemRequest lunchItemRequest) {
        SaveLunchItemGatewayInterface saveLunchItemGatewayInterface = new SaveLunchItemGatewayImpl(lunchItemAdapter);
        FindLunchItemsGatewayInterface findLunchItemsGatewayInterface = new FindLunchItemsGatewayImpl(lunchItemAdapter);
        CreateLunchItemUseCase createLunchItemUseCase = new CreateLunchItemUseCase(saveLunchItemGatewayInterface,findLunchItemsGatewayInterface);
        LunchItem toLunchItem = LunchItemsPresenter.lunchItemRequestToLunchItem(lunchItemRequest);
        LunchItem lunchItem = createLunchItemUseCase.createLunchItem(toLunchItem);
        return LunchItemsPresenter.lunchItemToLunchItemResponse(lunchItem);
    }

    public Boolean editStatusLunchItem(Long idLunchItem) {
        EditLunchItemGatewayInterface editLunchItemGatewayInterface = new EditLunchItemsGatewayImpl(lunchItemAdapter);
        FindLunchItemsGatewayInterface findLunchItemsGatewayInterface = new FindLunchItemsGatewayImpl(lunchItemAdapter);
        EditLunchItemUseCase editLunchItemUseCase = new EditLunchItemUseCase(editLunchItemGatewayInterface,findLunchItemsGatewayInterface);
        return editLunchItemUseCase.editStatusLunchItem(idLunchItem);
    }

    public LunchItemResponse editLunchItem(Long idLunchItem, LunchItemRequest lunchItemRequest) {
        EditLunchItemGatewayInterface editLunchItemGatewayInterface = new EditLunchItemsGatewayImpl(lunchItemAdapter);
        FindLunchItemsGatewayInterface findLunchItemsGatewayInterface = new FindLunchItemsGatewayImpl(lunchItemAdapter);
        EditLunchItemUseCase editLunchItemUseCase = new EditLunchItemUseCase(editLunchItemGatewayInterface,findLunchItemsGatewayInterface);
        LunchItem toLunchItem = LunchItemsPresenter.lunchItemRequestToLunchItem(lunchItemRequest);
        LunchItem lunchItem = editLunchItemUseCase.editLunchItem(idLunchItem, toLunchItem);
        return LunchItemsPresenter.lunchItemToLunchItemResponse(lunchItem);
    }

    public List<LunchItemResponse> findLunchItems(String type) {
        LunchItemType lunchItemType;
        try {
            lunchItemType = type == null ? null : LunchItemType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LunchItemException("type not found");
        }
        FindLunchItemsGatewayInterface findLunchItemsGatewayInterface = new FindLunchItemsGatewayImpl(lunchItemAdapter);
        FindLunchItemsUseCase findLunchItemsUseCase = new FindLunchItemsUseCase(findLunchItemsGatewayInterface);
        List<LunchItem> lunchItems = findLunchItemsUseCase.findLunchItems(lunchItemType);
        return LunchItemsPresenter.lunchItemToLunchItemResponseList(lunchItems);
    }

    public LunchItem findLunchItemByName(String name) {
        FindLunchItemsGatewayInterface findLunchItemsGatewayInterface = new FindLunchItemsGatewayImpl(lunchItemAdapter);
        FindLunchItemsUseCase findLunchItemsUseCase = new FindLunchItemsUseCase(findLunchItemsGatewayInterface);
        return findLunchItemsUseCase.findLunchItemByName(name);
    }
}