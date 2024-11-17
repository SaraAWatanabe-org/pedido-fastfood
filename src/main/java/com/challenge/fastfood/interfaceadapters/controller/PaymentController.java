package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.interfaceadapters.controller.request.PaymentRequestCallBack;
import com.challenge.fastfood.interfaceadapters.gateways.lunch.FindLunchGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.lunch.ProductionLunchGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.payment.PaymentImplGatewayImpl;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.ProductionLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.usecases.payment.CheckPaymentUseCase;
import com.challenge.fastfood.usecases.payment.PaymentUseCase;
import com.challenge.fastfood.entities.Payment;

public class PaymentController {

    private final PaymentAdapterInterface paymentAdapter;
    private final LunchAdapterInterface lunchAdapter;


    public PaymentController(PaymentAdapterInterface paymentAdapter,LunchAdapterInterface lunchAdapter1) {
        this.paymentAdapter = paymentAdapter;
        this.lunchAdapter = lunchAdapter1;
    }

    public String checkPayment(PaymentRequestCallBack paymentRequest) throws Exception {
        PaymentProcessGatewayInterface paymentProcessGatewayInterface = new PaymentImplGatewayImpl(paymentAdapter);
        FindLunchGatewayInterface findLunchGatewayInterface = new FindLunchGatewayImpl(lunchAdapter);
        ProductionLunchGatewayInterface lunchGatewayInterface = new ProductionLunchGatewayImpl(lunchAdapter);

        CheckPaymentUseCase checkPayment = new CheckPaymentUseCase(paymentProcessGatewayInterface,findLunchGatewayInterface,lunchGatewayInterface);
        Payment payment = checkPayment.execute(paymentRequest.numberLunch(),paymentRequest.status());

        return payment.getStatus();
    }


}