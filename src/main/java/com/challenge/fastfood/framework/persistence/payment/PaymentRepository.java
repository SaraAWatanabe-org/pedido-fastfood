package com.challenge.fastfood.framework.persistence.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository  extends JpaRepository<PaymentEntity, Long>   {

    PaymentEntity findByNumberLunch(Long numerLunch);

}
