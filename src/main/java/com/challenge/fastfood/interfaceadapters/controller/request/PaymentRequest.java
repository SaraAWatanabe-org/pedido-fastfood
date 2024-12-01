package com.challenge.fastfood.interfaceadapters.controller.request;

import lombok.Builder;

@Builder
public record PaymentRequest(Double value, String cpf, Long numberLunch) {
}
