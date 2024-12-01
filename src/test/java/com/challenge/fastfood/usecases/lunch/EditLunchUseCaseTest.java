package com.challenge.fastfood.usecases.lunch;

import com.challenge.fastfood.config.exception.PaymentException;
import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.EditLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.usecases.lunch.EditLunchUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

import static com.challenge.fastfood.utils.TestUtils.buildLunchMock;
import static com.challenge.fastfood.utils.TestUtils.buildPaymentMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditLunchUseCaseTest {

    @InjectMocks
    EditLunchUseCase editLunchUseCase;

    @Mock
    FindLunchGatewayInterface findLunchGatewayInterface;

    @Mock
    EditLunchGatewayInterface editLunchGatewayInterface;

    @Mock
    PaymentProcessGatewayInterface paymentProcessGatewayInterface;

    final Long LUNCH_ID = 1L;
    final String NEW_STATUS = "Pronto";

    @Test
    @DisplayName("Should throw Payment Exception when status is null")
    void updateLunchStatus_shouldThrowPaymentExceptionWhenStatusIsNull() {
        assertThrows(PaymentException.class, () -> editLunchUseCase.updateLunchStatus(LUNCH_ID, null), "Status cannot be null or empty");
    }

    @Test
    @DisplayName("Should throw Payment Exception when not found Lunch by id")
    void updateLunchStatus_shouldThrowPaymentExceptionWhenNotFoundLunchById() {
        assertThrows(PaymentException.class, () -> editLunchUseCase.updateLunchStatus(null, NEW_STATUS), "Lunch not found for id: "+LUNCH_ID);
    }

    @Test
    @DisplayName("Should throw Payment Exception when not found Payment by Lunch id")
    void updateLunchStatus_shouldThrowPaymentExceptionWhenNotFoundPaymentByLunchId() {
        var lunch = buildLunchMock();

        when(findLunchGatewayInterface.findLunchById(LUNCH_ID)).thenReturn(lunch);
        when(paymentProcessGatewayInterface.findPaymentByLunchId(LUNCH_ID)).thenReturn(null);

        assertThrows(PaymentException.class, () -> editLunchUseCase.updateLunchStatus(LUNCH_ID, NEW_STATUS), "Pagamento não foi efetuado!");
    }

    @Test
    @DisplayName("Should throw Payment Exception when Payment status is different than Success")
    void updateLunchStatus_shouldThrowPaymentExceptionWhenPaymentStatusIsDifferentThanSuccess() {
        var lunch = buildLunchMock();
        var payment = buildPaymentMock();

        when(findLunchGatewayInterface.findLunchById(LUNCH_ID)).thenReturn(lunch);
        when(paymentProcessGatewayInterface.findPaymentByLunchId(LUNCH_ID)).thenReturn(payment);

        assertThrows(PaymentException.class, () -> editLunchUseCase.updateLunchStatus(LUNCH_ID, NEW_STATUS), "Pagamento não foi efetuado!");
    }

    @Test
    @DisplayName("Should edit a Lunch")
    void updateLunchStatus_shouldEditLunch() {
        var lunch = buildLunchMock();
        var payment = buildPaymentMock();
        payment.setStatus("SUCCESS");

        when(findLunchGatewayInterface.findLunchById(LUNCH_ID)).thenReturn(lunch);
        when(paymentProcessGatewayInterface.findPaymentByLunchId(LUNCH_ID)).thenReturn(payment);
        when(editLunchGatewayInterface.editLunch(lunch)).thenReturn(lunch);

        AtomicReference<Lunch> response = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            response.set(editLunchUseCase.updateLunchStatus(LUNCH_ID, NEW_STATUS));
        });
        assertNotNull(response.get(), "The response should not be null");
        assertEquals(lunch.getClient(), response.get().getClient());
        assertEquals(lunch.getId(), response.get().getId());
        assertEquals(lunch.getPriceTotal(), response.get().getPriceTotal());
        assertEquals(lunch.getLunchItems().size(), response.get().getLunchItems().size());
        assertEquals(lunch.getClient().getName(), response.get().getClient().getName());
    }
}
