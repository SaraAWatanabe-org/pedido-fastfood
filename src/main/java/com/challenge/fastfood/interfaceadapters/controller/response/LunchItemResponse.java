package com.challenge.fastfood.interfaceadapters.controller.response;

import com.challenge.fastfood.entities.LunchItemType;
import io.swagger.v3.oas.annotations.media.Schema;

public record LunchItemResponse(

    @Schema(description = "The id of the lunch item", example = "1")
    Long id,
    @Schema(description = "The name of the lunch item", example = "Hamburger")
    String name,
    @Schema(description = "The price of the lunch item", example = "1.42")
    float price,
    @Schema(description = "The type of the lunch item", example = "SNACK")
    LunchItemType type) {
}

