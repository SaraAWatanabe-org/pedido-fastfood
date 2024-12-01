package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.config.exception.PaymentException;
import com.challenge.fastfood.interfaceadapters.interfaces.client.ClientAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import com.challenge.fastfood.usecases.lunch.FindLunchUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildLunchRequestMock;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LunchControllerTest {

    @InjectMocks
    LunchController controller;
    @Mock
    ClientAdapterInterface clientAdapter;
    @Mock
    LunchAdapterInterface lunchAdapter;
    @Mock
    LunchItemAdapterInterface lunchItemAdapter;
    @Mock
    PaymentAdapterInterface paymentAdapterInterface;
    @Mock
    FindLunchUseCase findLunchUseCase;
    @Mock
    FindLunchGatewayInterface findLunchGatewayInterface;

    @Test
    @DisplayName("Should find lunchs")
    public void findLunchs() {
        assertDoesNotThrow(() -> {
            var response = controller.findLunchs();
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when not found Lunch")
    public void findLunchById() {
        assertThrows(IllegalArgumentException.class, () -> controller.findLunchById(1L));
    }

    @Test
    @DisplayName("Should throw PaymentException when not found Lunch")
    public void editLunchStatus() {
        assertThrows(PaymentException.class, () -> controller.editLunchStatus(1L, "SUCCESS"));
    }

    @Test
    @DisplayName("Should throw ")
    public void createLunch() {
        var request = buildLunchRequestMock(false);
        assertThrows(ClientException.class, () -> controller.createLunch(request));
    }

}
