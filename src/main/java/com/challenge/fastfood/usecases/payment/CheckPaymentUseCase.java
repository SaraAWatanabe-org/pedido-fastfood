package com.challenge.fastfood.usecases.payment;


import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.ProductionLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;

public class CheckPaymentUseCase {

    private final PaymentProcessGatewayInterface paymentProcessGatewayInterface;
    private final FindLunchGatewayInterface findLunchGatewayInterface;
    private final ProductionLunchGatewayInterface lunchGatewayInterface;


    public CheckPaymentUseCase(PaymentProcessGatewayInterface payment, FindLunchGatewayInterface findLunchGatewayInterface, ProductionLunchGatewayInterface lunchGatewayInterface) {
        this.paymentProcessGatewayInterface = payment;
        this.findLunchGatewayInterface = findLunchGatewayInterface;
        this.lunchGatewayInterface = lunchGatewayInterface;

    }


    public Payment execute(Long numberLunch, String status) throws Exception {
        Payment payment = paymentProcessGatewayInterface.findPaymentByLunchId(numberLunch);

        payment.setStatus(status);
        paymentProcessGatewayInterface.savePayment(payment);

        if(payment.getStatus().equals("SUCCESS")){
            Lunch lunchById = findLunchGatewayInterface.findLunchById(numberLunch);
            lunchGatewayInterface.toProductionLunch(lunchById);
        }

        return payment;
    }
}
