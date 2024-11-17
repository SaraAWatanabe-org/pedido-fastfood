package com.challenge.fastfood.interfaceadapters.controller.request;

public record PaymentRequest(Double value, String cpf, Long numberLunch) {
}
