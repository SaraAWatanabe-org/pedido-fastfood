package com.challenge.fastfood.interfaceadapters.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ClientResponse(
        @Schema(description = "The name of the client", example = "Jequelia")
        String name,
        @Schema(description = "The email of the client", example = "jequelia@email.com")
        String email) {
}
