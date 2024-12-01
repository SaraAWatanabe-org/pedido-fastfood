package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.framework.mapstruct.PaymentMapper;
import com.challenge.fastfood.framework.persistence.payment.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.challenge.fastfood.utils.TestUtils.buildPaymentEntityMock;
import static com.challenge.fastfood.utils.TestUtils.buildPaymentMock;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentAdapterImplTest {

    @InjectMocks
    PaymentAdapterImpl adapter;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    PaymentMapper paymentMapper;

    private final Long ID = 33L;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(adapter, "billingServiceUrl", "http://mocked-url.com");
    }

    @Test
    @DisplayName("Should save Payment")
    public void savePayment() {
        var payment  = buildPaymentMock();
        var paymentEntity = buildPaymentEntityMock();

        when(paymentRepository.save(paymentEntity)).thenReturn(paymentEntity);
        when(paymentMapper.toPaymentEntity(any())).thenReturn(paymentEntity);
        when(paymentMapper.toPayment(paymentEntity)).thenReturn(payment);

        assertDoesNotThrow(() -> {
            var response = adapter.savePayment(payment);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should find By Number Lunch")
    public void findByNumberLunch() {
        var payment  = buildPaymentMock();
        var paymentEntity = buildPaymentEntityMock();

        when(paymentRepository.findByNumberLunch(any())).thenReturn(paymentEntity);
        when(paymentMapper.toPayment(paymentEntity)).thenReturn(payment);

        assertDoesNotThrow(() -> {
            var response = adapter.findByNumberLunch(ID);
            assertNotNull(response);
        });
    }




}
