package com.challenge.fastfood.usecases.lunch;

import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.LunchStatus;
import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.usecases.lunch.FindLunchUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.challenge.fastfood.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindLunchUseCaseTest {

    @InjectMocks
    FindLunchUseCase findLunchUseCase;

    @Mock
    FindLunchGatewayInterface findLunchGatewayInterface;

    @Mock
    FindClientGatewayInterface findClientGatewayInterface;

    final Long LUNCH_ID = 1L;

    @Test
    @DisplayName("Should find and sort lunches by status and date")
    void findLunchs() {
        var lunchList = buildLunchListMock();

        when(findLunchGatewayInterface.findLunchs()).thenReturn(lunchList);

        List<Lunch> sortedLunchList = findLunchUseCase.findLunchs();

        assertDoesNotThrow(() -> findLunchUseCase.findLunchs());

        List<Lunch> expectedSortedLunchList = lunchList.stream()
                .filter(lunch -> !LunchStatus.FINALIZADO.equals(lunch.getStatus()))
                .sorted(Comparator.comparing(Lunch::getStatus)
                        .thenComparing(Lunch::getDate))
                .collect(Collectors.toList());

        assertEquals(expectedSortedLunchList, sortedLunchList, "A lista de Lunches não está ordenada corretamente por status e data.");
    }

    @Test
    @DisplayName("Should find Lunch by id")
    void findLunchById() {
        var lunch = buildLunchMock();

        when(findLunchGatewayInterface.findLunchById(LUNCH_ID)).thenReturn(lunch);

        AtomicReference<Lunch> response = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            response.set(findLunchUseCase.findLunchById(LUNCH_ID));
        });
        assertNotNull(response, "The response should not be null");
        assertEquals(lunch.getClient(), response.get().getClient());
        assertEquals(lunch.getId(), response.get().getId());
        assertEquals(lunch.getPriceTotal(), response.get().getPriceTotal());
        assertEquals(lunch.getLunchItems().size(), response.get().getLunchItems().size());
        assertEquals(lunch.getClient().getName(), response.get().getClient().getName());
    }

    @Test
    @DisplayName("Should return a empty Lunch when client id is null")
    void getLunch_shouldReturnAEmptyLunchWhenClientIdIsNull() {
        var request = buildLunchRequestMock(true);

        AtomicReference<Lunch> response = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            response.set(findLunchUseCase.getLunch(request, findClientGatewayInterface));
        });
        assertNotNull(response.get(), "The response should not be null");
        assertNull(response.get().getClient());
        assertNull(response.get().getLunchItems());
        assertNull(response.get().getId());
        assertNull(response.get().getDate());
        assertEquals(0.0, response.get().getPriceTotal());
    }

    @Test
    @DisplayName("Should throw Client Exception when not found client by id")
    void getLunch_shouldThrowClientExceptionWhenClientIdIsNotFound() {
        var request = buildLunchRequestMock(false);

        when(findClientGatewayInterface.findClientById(request.clientId())).thenReturn(null);

        assertThrows(ClientException.class, () -> findLunchUseCase.getLunch(request, findClientGatewayInterface), "Client id doesn't represent any existing client");
    }

    @Test
    @DisplayName("Should find client and return a Lunch")
    void getLunch_shouldFindAndReturnLunchByClientId() {
        var request = buildLunchRequestMock(false);
        var client = buildClientMock();

        when(findClientGatewayInterface.findClientById(request.clientId())).thenReturn(client);

        AtomicReference<Lunch> response = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            response.set(findLunchUseCase.getLunch(request, findClientGatewayInterface));
        });
        assertNotNull(response.get(), "The response should not be null");
        assertEquals(client, response.get().getClient());
        assertEquals(request.clientId(), response.get().getClient().getId());
        assertNull(response.get().getLunchItems());
        assertNull(response.get().getId());
        assertNull(response.get().getDate());
        assertEquals(0.0, response.get().getPriceTotal());
    }
}
