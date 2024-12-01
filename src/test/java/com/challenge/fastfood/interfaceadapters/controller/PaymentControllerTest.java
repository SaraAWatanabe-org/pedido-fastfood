package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.interfaceadapters.controller.request.LunchItemRequest;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildLunchItemRequestMock;
import static com.challenge.fastfood.utils.TestUtils.buildPaymentCallBackMock;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    PaymentController controller;
    @Mock
    PaymentAdapterInterface paymentAdapter;
    @Mock
    LunchAdapterInterface lunchAdapter;

    private final Long ID_LUNCH_ITEM = 3L;
    private final String TYPE = "DRINK";
    private final LunchItemRequest REQUEST = buildLunchItemRequestMock();

    @Test
    @DisplayName("Should throw IllegalArgumentException")
    public void createLunchItem() {
        assertThrows(NullPointerException.class, () -> controller.checkPayment(buildPaymentCallBackMock()));
    }

}