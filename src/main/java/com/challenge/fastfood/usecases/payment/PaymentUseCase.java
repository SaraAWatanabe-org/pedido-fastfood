package com.challenge.fastfood.usecases.payment;


import com.challenge.fastfood.config.exception.PaymentException;
import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.entities.Payment;

import java.io.IOException;
import java.util.Objects;

public class PaymentUseCase {

    private final PaymentProcessGatewayInterface paymentProcessGatewayInterface;

    private final FindLunchGatewayInterface findLunchGatewayInterface;

    public PaymentUseCase(PaymentProcessGatewayInterface payment, FindLunchGatewayInterface findLunchGatewayInterface) {
        this.paymentProcessGatewayInterface = payment;
        this.findLunchGatewayInterface = findLunchGatewayInterface;
    }

    public Payment execute(Payment payment) {

        Payment paymentByLunchId = paymentProcessGatewayInterface.findPaymentByLunchId(payment.getNumberLunch());

        Lunch lunchById = findLunchGatewayInterface.findLunchById(payment.getNumberLunch());

        if(lunchById == null){
            throw new PaymentException("Pedido invalido para pagamento: Número de lanche não existe no banco");
        }

        if(paymentByLunchId != null){
            if(Objects.equals(payment.getNumberLunch(), paymentByLunchId.getNumberLunch())){

                if("SUCCESS".equals(paymentByLunchId.getStatus())){
                    throw new PaymentException("Pagamento já foi processado com sucesso");
                }else{
                    throw new PaymentException("Processando pagamento");
                }
            }

            if(!Objects.equals(payment.getCpf(), lunchById.getClient().getCpf())){
                throw new PaymentException("CPF precisa existir no banco da lanchonete");
            }

        }else{
            payment.setStatus("PENDING");
        }
        Payment savedPayment = paymentProcessGatewayInterface.savePayment(payment);
        String transactionID = null;
        try {
            transactionID = paymentProcessGatewayInterface.processPayment(payment);
            savedPayment.setTransactionId(transactionID);
            savedPayment.setCpf(lunchById.getClient().getCpf());
            savedPayment.setPriceTotal(lunchById.getPriceTotal());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  paymentProcessGatewayInterface.savePayment(savedPayment);
    }

}
