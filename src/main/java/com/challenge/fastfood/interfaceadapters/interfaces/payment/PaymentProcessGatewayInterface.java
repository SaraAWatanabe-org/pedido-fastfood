package com.challenge.fastfood.interfaceadapters.interfaces.payment;


import com.challenge.fastfood.entities.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface PaymentProcessGatewayInterface {

    String processPayment(Payment payment) throws IOException, InterruptedException;
    Payment findPaymentByLunchId(Long lunchId);
    Payment savePayment(Payment payment);

}
