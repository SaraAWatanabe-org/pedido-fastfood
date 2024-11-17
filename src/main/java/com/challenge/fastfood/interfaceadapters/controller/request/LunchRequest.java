package com.challenge.fastfood.interfaceadapters.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record LunchRequest(
        @Schema(description = "The client's identification", example = "Patrícia or 111.111.111-11 or patricia@fastfood.com", nullable = true)
        Long clientId,
        @Schema(description = "The ids lunchs snack", example = "[1,2,5]", nullable = true)
        List<Long> snack,
        @Schema(description = "The ids lunchs drink", example = "[]", nullable = true)
        List<Long> drink,
        @Schema(description = "The ids lunchs accompaniment", example = "[]", nullable = true)
        List<Long> accompaniment,
        @Schema(description = "The ids lunchs dessert", example = "[]", nullable = true)
        List<Long> dessert
) {

}
