package com.challenge.fastfood.interfaceadapters.presenter;

import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchItemType;
import com.challenge.fastfood.interfaceadapters.controller.request.LunchItemRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.LunchItemResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LunchItemsPresenter {

    public static LunchItemRequest lunchItemToLunchItemRequest(LunchItem lunchItem) {
        if (lunchItem == null) {
            throw new IllegalArgumentException("LunchItem cannot be null");
        }

        return new LunchItemRequest(
                lunchItem.getName(),
                lunchItem.getPrice(),
                lunchItem.getType() != null ? lunchItem.getType().name() : null // Convert enum to String
        );
    }

    public static LunchItem lunchItemRequestToLunchItem(LunchItemRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("LunchItemRequest cannot be null");
        }

        return new LunchItem(
                null, // Assuming ID is not provided in the request
                request.name(),
                request.price(),
                LunchItemType.valueOf(request.type()), // Convert String to enum
                null // Assuming status is not provided in the request
        );
    }

    public static LunchItemResponse lunchItemToLunchItemResponse(LunchItem lunchItem) {
        if (lunchItem == null) {
            throw new IllegalArgumentException("LunchItem cannot be null");
        }

        return new LunchItemResponse(
                lunchItem.getId(),
                lunchItem.getName(),
                lunchItem.getPrice(),
                lunchItem.getType()
        );
    }

    public static LunchItem lunchItemResponseToLunchItem(LunchItemResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("LunchItemResponse cannot be null");
        }

        return new LunchItem(
                response.id(),
                response.name(),
                response.price(),
                response.type(),
                null // Assuming status is not available in LunchItemResponse
        );
    }

    public static List<LunchItemResponse> lunchItemToLunchItemResponseList(List<LunchItem> lunchItems) {
        if (lunchItems == null) {
            throw new IllegalArgumentException("LunchItems list cannot be null");
        }

        return lunchItems.stream()
                .filter(Objects::nonNull) // Filtra elementos nulos na lista
                .map(LunchItemsPresenter::lunchItemToLunchItemResponse) // Usa método auxiliar para conversão
                .collect(Collectors.toList());
    }


}
