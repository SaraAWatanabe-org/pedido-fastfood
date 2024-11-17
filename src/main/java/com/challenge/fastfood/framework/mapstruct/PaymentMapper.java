package com.challenge.fastfood.framework.mapstruct;

import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.framework.persistence.payment.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    Payment toPayment(PaymentEntity paymentEntity);

//    PaymentRequest toPaymentRequest(Payment payment);

    PaymentEntity toPaymentEntity(Payment payment);
}
