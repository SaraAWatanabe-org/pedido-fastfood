package com.challenge.fastfood.interfaceadapters.interfaces.payment;

import com.challenge.fastfood.entities.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PaymentAdapterInterface {

    Payment savePayment(Payment payment);
    Payment findByNumberLunch(Long numberLunch);
    String httpRequestPayment(Payment payment) throws JsonProcessingException;



}
