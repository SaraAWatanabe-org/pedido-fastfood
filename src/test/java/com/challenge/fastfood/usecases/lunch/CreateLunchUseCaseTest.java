package com.challenge.fastfood.usecases.lunch;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.SaveLunchGatewayInterface;
import com.challenge.fastfood.usecases.lunch.CreateLunchUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

import static com.challenge.fastfood.utils.TestUtils.buildLunchMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateLunchUseCaseTest {

    @InjectMocks
    CreateLunchUseCase lunchUseCase;

    @Mock
    SaveLunchGatewayInterface saveLunchGatewayInterface;

    @Test
    @DisplayName("Should save a Lunch")
    void createLunch_shouldCreateLunch() {
        var lunch = buildLunchMock();
        AtomicReference<Lunch> response = new AtomicReference<>();

        when(saveLunchGatewayInterface.saveLunch(lunch)).thenReturn(lunch);

        assertDoesNotThrow(() -> {
            response.set(lunchUseCase.createLunch(lunch));
        });
        assertNotNull(response.get(), "The response should not be null");
        assertEquals(lunch.getClient(), response.get().getClient());
        assertEquals(lunch.getId(), response.get().getId());
        assertEquals(lunch.getPriceTotal(), response.get().getPriceTotal());
        assertEquals(lunch.getLunchItems().size(), response.get().getLunchItems().size());
        assertEquals(lunch.getClient().getName(), response.get().getClient().getName());
    }
}
