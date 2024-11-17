package com.challenge.fastfood.interfaceadapters.controller.response;

public record PaymentResponse(Long id, Double priceTotal, String cpf, Long numberLunch,String status,String transactionId) {


}
