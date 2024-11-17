package com.challenge.fastfood.interfaceadapters.gateways.payment;

import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.entities.Payment;

import java.io.IOException;

public class PaymentImplGatewayImpl implements PaymentProcessGatewayInterface {


    private final PaymentAdapterInterface paymentAdapter;

    public PaymentImplGatewayImpl(PaymentAdapterInterface paymentAdapter) {
        this.paymentAdapter = paymentAdapter;
    }

    @Override
    public Payment findPaymentByLunchId(Long numberLunch) {
        return paymentAdapter.findByNumberLunch(numberLunch);
    }


    @Override
    public Payment savePayment(Payment payment) {
        return paymentAdapter.savePayment(payment);
    }

    @Override
    public String processPayment(Payment payment) throws IOException {
        return paymentAdapter.httpRequestPayment(payment);
    }


}
