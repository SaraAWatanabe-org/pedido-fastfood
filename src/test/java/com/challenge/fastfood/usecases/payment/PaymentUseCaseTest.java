package com.challenge.fastfood.usecases.payment;

import com.challenge.fastfood.config.exception.PaymentException;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.usecases.payment.PaymentUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.challenge.fastfood.utils.TestUtils.buildLunchMock;
import static com.challenge.fastfood.utils.TestUtils.buildPaymentMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentUseCaseTest {

    @InjectMocks
    PaymentUseCase paymentUseCase;

    @Mock
    PaymentProcessGatewayInterface paymentProcessGatewayInterface;

    @Mock
    FindLunchGatewayInterface findLunchGatewayInterface;

    @Test
    @DisplayName("Should throw PaymentException when not found Lunch")
    void processPayment_shouldThrowPaymentExceptionWhenNotFoundLunch() {
        var payment = buildPaymentMock();

        when(paymentProcessGatewayInterface.findPaymentByLunchId(payment.getNumberLunch())).thenReturn(payment);
        when(findLunchGatewayInterface.findLunchById(payment.getNumberLunch())).thenReturn(null);

        assertThrows(PaymentException.class, () -> paymentUseCase.execute(payment), "Pedido invalido para pagamento: Número de lanche não existe no banco");
    }

    @Test
    @DisplayName("Should throw PaymentException when payment already has processed")
    void processPayment_shouldThrowPaymentExceptionWhenPaymentAlreadyHasProcessed() {
        var payment = buildPaymentMock();
        var lunch = buildLunchMock();
        payment.setStatus("SUCCESS");

        when(paymentProcessGatewayInterface.findPaymentByLunchId(payment.getNumberLunch())).thenReturn(payment);
        when(findLunchGatewayInterface.findLunchById(payment.getNumberLunch())).thenReturn(lunch);

        assertThrows(PaymentException.class, () -> paymentUseCase.execute(payment), "Pagamento já foi processado com sucesso");
    }

    @Test
    @DisplayName("Should throw PaymentException when payment still in progress")
    void processPayment_shouldThrowPaymentExceptionWhenPaymentStillInProgress() {
        var payment = buildPaymentMock();
        var lunch = buildLunchMock();
        payment.setStatus("WAITING");

        when(paymentProcessGatewayInterface.findPaymentByLunchId(payment.getNumberLunch())).thenReturn(payment);
        when(findLunchGatewayInterface.findLunchById(payment.getNumberLunch())).thenReturn(lunch);

        assertThrows(PaymentException.class, () -> paymentUseCase.execute(payment), "Processando pagamento");
    }

    @Test
    @DisplayName("Should throw PaymentException when cpf does not exists")
    void processPayment_shouldThrowPaymentExceptionWhenCpfDoesNotExists() {
        var payment = buildPaymentMock();
        var paymentByLunchId = buildPaymentMock();
        var lunch = buildLunchMock();
        payment.setNumberLunch(30L);
        paymentByLunchId.setNumberLunch(31L);

        when(paymentProcessGatewayInterface.findPaymentByLunchId(payment.getNumberLunch())).thenReturn(paymentByLunchId);
        when(findLunchGatewayInterface.findLunchById(payment.getNumberLunch())).thenReturn(lunch);

        assertThrows(PaymentException.class, () -> paymentUseCase.execute(payment), "CPF precisa existir no banco da lanchonete");
    }

    @Test
    @DisplayName("Should throw IOException when processPayment fail")
    void processPayment_shouldThrowIOExceptionWhenProcessPaymentFail() throws IOException, InterruptedException {
        final long NUMBER = 30L;
        final String CPF = "123456789";
        var payment = buildPaymentMock();
        var paymentByLunchId = buildPaymentMock();
        var lunch = buildLunchMock();
        payment.setNumberLunch(NUMBER);
        payment.setCpf(CPF);
        payment.setStatus("PENDING");
        paymentByLunchId.setNumberLunch(NUMBER+1);
        paymentByLunchId.setCpf(CPF);
        lunch.getClient().setCpf(CPF);

        when(paymentProcessGatewayInterface.findPaymentByLunchId(payment.getNumberLunch())).thenReturn(paymentByLunchId);
        when(findLunchGatewayInterface.findLunchById(payment.getNumberLunch())).thenReturn(lunch);
        when(paymentProcessGatewayInterface.savePayment(any())).thenReturn(payment);
        when(paymentProcessGatewayInterface.processPayment(payment)).thenThrow(IOException.class);

        assertThrows(RuntimeException.class, () -> paymentUseCase.execute(payment));
    }

    @Test
    @DisplayName("Should throw InterruptedException when processPayment fail")
    void processPayment_shouldThrowInterruptedExceptionWhenProcessPaymentFail() throws IOException, InterruptedException {
        final Long NUMBER = 30L;
        final String CPF = "123456789";
        var payment = buildPaymentMock();
        var paymentByLunchId = buildPaymentMock();
        var lunch = buildLunchMock();
        payment.setNumberLunch(NUMBER);
        payment.setCpf(CPF);
        payment.setStatus("PENDING");
        paymentByLunchId.setNumberLunch(NUMBER);
        paymentByLunchId.setCpf(CPF);
        lunch.getClient().setCpf(CPF);

        when(paymentProcessGatewayInterface.findPaymentByLunchId(payment.getNumberLunch())).thenReturn(null);
        when(findLunchGatewayInterface.findLunchById(payment.getNumberLunch())).thenReturn(lunch);
        when(paymentProcessGatewayInterface.savePayment(any())).thenReturn(payment);
        when(paymentProcessGatewayInterface.processPayment(payment)).thenThrow(InterruptedException.class);

        assertThrows(RuntimeException.class, () -> paymentUseCase.execute(payment));
    }

    @Test
    @DisplayName("Should process payment without error")
    void processPayment_shouldProcessPaymentWithoutError() throws IOException, InterruptedException {
        final Long NUMBER = 30L;
        final String CPF = "123456789";
        var payment = buildPaymentMock();
        var paymentByLunchId = buildPaymentMock();
        var lunch = buildLunchMock();
        payment.setNumberLunch(NUMBER);
        payment.setCpf(CPF);
        payment.setStatus("PENDING");
        paymentByLunchId.setNumberLunch(NUMBER);
        paymentByLunchId.setCpf(CPF);
        lunch.getClient().setCpf(CPF);
        lunch.setPriceTotal(NUMBER.doubleValue());

        when(paymentProcessGatewayInterface.findPaymentByLunchId(payment.getNumberLunch())).thenReturn(null);
        when(findLunchGatewayInterface.findLunchById(payment.getNumberLunch())).thenReturn(lunch);
        when(paymentProcessGatewayInterface.savePayment(any())).thenReturn(payment);
        when(paymentProcessGatewayInterface.processPayment(payment)).thenReturn("transactionID");

        assertDoesNotThrow(() -> {
            assertNotNull(paymentUseCase.execute(payment));
        });
    }
}
