package com.challenge.fastfood.interfaceadapters.presenter;

import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.interfaceadapters.controller.request.ClientRequest;
import com.challenge.fastfood.interfaceadapters.controller.request.PaymentRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.LunchItemResponse;
import com.challenge.fastfood.interfaceadapters.controller.response.PaymentResponse;

public class PaymentPresenter {

    public Payment toPayment(PaymentRequest paymentRequest) {
        if (paymentRequest == null) {
            throw new ClientException("paymentRequest cannot be null");
        }
        Payment payment = new Payment();
        payment.setCpf(paymentRequest.cpf());
        payment.setNumberLunch(paymentRequest.numberLunch());
        payment.setPriceTotal(paymentRequest.value());
        return payment;
    }


    public PaymentResponse toPaymentResponse(Payment payment) {
        if (payment == null) {
            throw new ClientException("Payment cannot be null");
        }
        return new PaymentResponse(
                payment.getId(),
                payment.getPriceTotal(),
                payment.getCpf(),
                payment.getNumberLunch(),
                payment.getStatus(),
                payment.getTransactionId()
        );
    }

}
