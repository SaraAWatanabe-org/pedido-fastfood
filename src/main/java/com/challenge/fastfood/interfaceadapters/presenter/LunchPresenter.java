package com.challenge.fastfood.interfaceadapters.presenter;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.interfaceadapters.controller.response.LunchResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LunchPresenter {

    public static LunchResponse lunchToLunchResponse(Lunch lunch) {
        if (lunch == null) {
            throw new IllegalArgumentException("Lunch cannot be null");
        }

        List<LunchItem> lunchItems = lunch.getLunchItems().stream()
                .map(item -> new LunchItem(
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getType(),
                        item.getStatus()
                ))
                .collect(Collectors.toList());

        return new LunchResponse(
                lunchItems,
                lunch.getStatus().getDescricao(),
                lunch.getClient() != null ? lunch.getClient().getCpf() : null,
                (float) lunch.getPriceTotal(),
                lunch.getId(),
                lunch.getDate()
        );
    }

    public static List<LunchResponse> lunchesToLunchResponses(List<Lunch> lunches) {
        if (lunches == null) {
            throw new IllegalArgumentException("Lunch list cannot be null");
        }

        return lunches.stream()
                .filter(Objects::nonNull) // Filtra elementos nulos na lista de Lunch
                .map(LunchPresenter::lunchToLunchResponse) // Usa uma expressão lambda para mapear
                .collect(Collectors.toList());
    }




}
