package com.challenge.fastfood.usecases.payment;

import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.ProductionLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.usecases.payment.CheckPaymentUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildLunchMock;
import static com.challenge.fastfood.utils.TestUtils.buildPaymentMock;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckPaymentUseCaseTest {

    @InjectMocks
    CheckPaymentUseCase checkPaymentStatus;

    @Mock
    PaymentProcessGatewayInterface paymentProcessGatewayInterface;

    @Mock
    FindLunchGatewayInterface findLunchGatewayInterface;

    @Mock
    ProductionLunchGatewayInterface lunchGatewayInterface;

    private final Long NUMBER_LUNCH = 1523L;
    private final String STATUS_SUCCESS = "SUCCESS";
    private final String STATUS_CUSTOM = "Finalizado";

    @Test
    @DisplayName("Should return payment with success status")
    void execute_successStatus() throws Exception {
        var payment = buildPaymentMock();
        var lunch = buildLunchMock();

        when(paymentProcessGatewayInterface.findPaymentByLunchId(NUMBER_LUNCH)).thenReturn(payment);
        when(paymentProcessGatewayInterface.savePayment(any())).thenReturn(payment);
        when(findLunchGatewayInterface.findLunchById(NUMBER_LUNCH)).thenReturn(lunch);

        assertDoesNotThrow(() -> {
            assertNotNull(checkPaymentStatus.execute(NUMBER_LUNCH, STATUS_SUCCESS));
        });
    }

    @Test
    @DisplayName("Should return payment with custom status")
    void execute_customStatus() throws Exception {
        var payment = buildPaymentMock();

        when(paymentProcessGatewayInterface.findPaymentByLunchId(NUMBER_LUNCH)).thenReturn(payment);
        when(paymentProcessGatewayInterface.savePayment(any())).thenReturn(payment);

        assertDoesNotThrow(() -> {
            assertNotNull(checkPaymentStatus.execute(NUMBER_LUNCH, STATUS_CUSTOM));
        });
    }
}
