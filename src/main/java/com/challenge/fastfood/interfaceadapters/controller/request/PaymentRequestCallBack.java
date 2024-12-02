package com.challenge.fastfood.interfaceadapters.controller.request;

import lombok.Builder;

@Builder
public record PaymentRequestCallBack(Long numberLunch, String status) {
}
