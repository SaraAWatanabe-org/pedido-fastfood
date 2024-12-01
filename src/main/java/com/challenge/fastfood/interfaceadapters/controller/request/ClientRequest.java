package com.challenge.fastfood.interfaceadapters.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ClientRequest(
        @Schema(description = "The name of the client", example = "Jequelia")
        @NotNull(message = "The name is required")
        @NotEmpty(message = "The name is required")
        String name,
        @Schema(description = "The email of the client", example = "jequelia@email.com")
        @NotNull(message = "The email is required")
        @NotEmpty(message = "The email is required")
        String email,
        @Schema(description = "The cpf of the client", example = "123.456.789-00")
        @NotNull(message = "The cpf is required")
        @Size(min=11, max=14)
        @NotEmpty(message = "The cpf is required")
        String cpf) {
}
