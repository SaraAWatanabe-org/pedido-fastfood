package com.challenge.fastfood.interfaceadapters.controller.request;

import com.challenge.fastfood.config.validation.EnumValue;
import com.challenge.fastfood.entities.LunchItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LunchItemRequest(
        @Schema(description = "The name of the lunch item", example = "Hamburger")
        @NotNull(message = "The name is required")
        @NotEmpty(message = "The name is required")
        String name,
        @Schema(description = "The price of the lunch item with two decimal points", example = "12.34")
        Float price,
        @Schema(description = "The type of the lunch item", example = "SNACK, ACCOMPANIMENT, DRINK, DESSERT")
        @EnumValue(enumClass = LunchItemType.class, message = "Invalid lunch item type")
        @NotNull
        String type){
}
