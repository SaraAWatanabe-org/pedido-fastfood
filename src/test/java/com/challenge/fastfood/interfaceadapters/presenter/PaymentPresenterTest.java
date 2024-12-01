package com.challenge.fastfood.interfaceadapters.presenter;

import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.interfaceadapters.controller.request.PaymentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildPaymentMock;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentPresenterTest {

    @InjectMocks
    PaymentPresenter presenter;

    @Test
    @DisplayName("Should convert Lunch to LunchResponse")
    public void toPayment() {
        var request = PaymentRequest.builder()
                .cpf("123456789")
                .numberLunch(2000L)
                .value(200.0)
                .build();

        assertDoesNotThrow(() -> {
            var response = presenter.toPayment(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw ClientException when request is null")
    public void toPayment_error() {
        assertThrows(ClientException.class, () -> presenter.toPayment(null));
    }

    @Test
    @DisplayName("Should convert Lunch list to LunchResponse list")
    public void toPaymentResponse() {
        var request = buildPaymentMock();

        assertDoesNotThrow(() -> {
            var response = presenter.toPaymentResponse(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw ClientException when request is null")
    public void toPaymentResponse_error() {
        assertThrows(ClientException.class, () -> presenter.toPaymentResponse(null));
    }

}