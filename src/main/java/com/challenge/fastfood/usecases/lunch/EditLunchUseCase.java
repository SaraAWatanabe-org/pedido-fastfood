package com.challenge.fastfood.usecases.lunch;

import com.challenge.fastfood.config.exception.PaymentException;
import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.LunchStatus;
import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.EditLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;

public class EditLunchUseCase {

    private final FindLunchGatewayInterface findLunchGatewayInterface;
    private final EditLunchGatewayInterface editLunchGatewayInterface;
    private final PaymentProcessGatewayInterface paymentProcessGatewayInterface;

    public EditLunchUseCase(FindLunchGatewayInterface findLunchGatewayInterface, EditLunchGatewayInterface editLunchGatewayInterface, PaymentProcessGatewayInterface paymentProcessGatewayInterface) {
        this.findLunchGatewayInterface = findLunchGatewayInterface;
        this.editLunchGatewayInterface = editLunchGatewayInterface;
        this.paymentProcessGatewayInterface = paymentProcessGatewayInterface;
    }

    public Lunch updateLunchStatus(Long lunchId, String newStatus) {
        if (newStatus == null ) {
            throw new PaymentException("Status cannot be null or empty");
        }

        Lunch lunch = findLunchGatewayInterface.findLunchById(lunchId);
        if (lunch == null) {
            throw new PaymentException("Lunch not found for id: " + lunchId);
        }
        Payment paymentByLunchId = paymentProcessGatewayInterface.findPaymentByLunchId(lunchId);

        if(paymentByLunchId == null || !"SUCCESS".equals(paymentByLunchId.getStatus())){
            throw new PaymentException("Pagamento não foi efetuado!");
        }

        LunchStatus lunchStatus = LunchStatus.fromDescricao(newStatus);
        lunch.setStatus(lunchStatus);

        return editLunchGatewayInterface.editLunch(lunch);
    }
}
