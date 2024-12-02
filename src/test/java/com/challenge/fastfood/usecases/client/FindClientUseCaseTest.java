package com.challenge.fastfood.usecases.client;

import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.usecases.client.FindClientUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildClientMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindClientUseCaseTest {

    @InjectMocks
    FindClientUseCase findClientUseCase;

    @Mock
    FindClientGatewayInterface findClientGateway;

    @Test
    @DisplayName("Should throw ClientException when client CPF is null")
    void shouldThrowClientException_whenCpfIsNull() {
        assertThrows(ClientException.class, () -> findClientUseCase.findClient(null), "Expected ClientException when CPF is null");
    }

    @Test
    @DisplayName("Should throw ClientException when client is not found")
    void shouldThrowClientException_whenClientIsNotFound() {
        final var cpf = "1".repeat(11);
        when(findClientGateway.findClient(cpf)).thenReturn(null);

        assertThrows(ClientException.class, () -> findClientUseCase.findClient(cpf), "Expected ClientException when client is not found");
    }

    @Test
    @DisplayName("Should throw ClientException when client is found but CPF is null")
    void shouldThrowClientException_whenClientIsFoundButCpfIsNull() {
        var client = buildClientMock();
        client.setCpf(null);

        when(findClientGateway.findClient(any())).thenReturn(client);

        assertThrows(ClientException.class, () -> findClientUseCase.findClient("1".repeat(11)), "Expected ClientException when client CPF is null");
    }

    @Test
    @DisplayName("Should throw ClientException when client is found but email is null")
    void shouldThrowClientException_whenClientIsFoundButEmailIsNull() {
        var client = buildClientMock();
        client.setEmail(null);

        when(findClientGateway.findClient(any())).thenReturn(client);

        assertThrows(ClientException.class, () -> findClientUseCase.findClient("1".repeat(11)), "Expected ClientException when client email is null");
    }

    @Test
    @DisplayName("Should find and return Client")
    void shouldFindClientSuccessfully() {
        var client = buildClientMock();
        var expected = buildClientMock();

        when(findClientGateway.findClient(client.getCpf())).thenReturn(expected);

        var response = findClientUseCase.findClient(client.getCpf());

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getName());
        assertNotNull(response.getEmail());
        assertNotNull(response.getCpf());

        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getName(), response.getName());
        assertEquals(expected.getCpf(), response.getCpf());
        assertEquals(expected.getEmail(), response.getEmail());

        verify(findClientGateway, times(1)).findClient(client.getCpf());
    }
}
