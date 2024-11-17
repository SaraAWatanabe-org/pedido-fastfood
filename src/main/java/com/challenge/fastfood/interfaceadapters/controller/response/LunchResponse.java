package com.challenge.fastfood.interfaceadapters.controller.response;

import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.framework.persistence.lunchItem.LunchItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record LunchResponse(

        @Schema(description = "The details for the snack of the lunch")
        List<LunchItem> lunchItems,
        String status,
        @Schema(description = "The client CPF", example = "111.111.111-11")
        String cpf,
        @Schema(description = "The total price", example = "142.34")
        Float priceTotal,
        @Schema(description = "The number of the lunch", example = "1")
        Long numberLunch,
        @Schema(description = "Date", example = "10/10/10")
        LocalDateTime date
        ) {
}
