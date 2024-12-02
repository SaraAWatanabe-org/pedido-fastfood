package com.challenge.fastfood.usecases.client;

import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.client.SaveClientGatewayInterface;
import com.challenge.fastfood.usecases.client.CreateClientUseCase;
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
public class CreateClientUseCaseTest {

    @InjectMocks
    CreateClientUseCase createClientUseCase;

    @Mock
    SaveClientGatewayInterface saveClientGateway;

    @Mock
    FindClientGatewayInterface findClientGateway;

    @Test
    @DisplayName("Should throw ClientException when client data is invalid with null name")
    void shouldThrowClientException_whenClientNameIsNull() {
        var client = buildClientMock();
        client.setName(null);

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client name is null");
    }

    @Test
    @DisplayName("Should throw ClientException when client data is invalid with null CPF")
    void shouldThrowClientException_whenClientCpfIsNull() {
        var client = buildClientMock();
        client.setCpf(null);

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client CPF is null");
    }

    @Test
    @DisplayName("Should throw ClientException when client data is invalid with null email")
    void shouldThrowClientException_whenClientEmailIsNull() {
        var client = buildClientMock();
        client.setEmail(null);

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client email is null");
    }

    @Test
    @DisplayName("Should throw ClientException when client name exceeds max length")
    void shouldThrowClientException_whenClientNameExceedsMaxLength() {
        var client = buildClientMock();
        client.setName("A".repeat(101));

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client name exceeds max length");
    }

    @Test
    @DisplayName("Should throw ClientException when client CPF length exceeds max length")
    void shouldThrowClientException_whenClientCpfExceedsMaxLength() {
        var client = buildClientMock();
        client.setCpf("1".repeat(15));

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client CPF length exceeds max length");
    }

    @Test
    @DisplayName("Should throw ClientException when client CPF length is below min length")
    void shouldThrowClientException_whenClientCpfBelowMinLength() {
        var client = buildClientMock();
        client.setCpf("1".repeat(10));

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client CPF length is below min length");
    }

    @Test
    @DisplayName("Should throw ClientException when client email exceeds max length")
    void shouldThrowClientException_whenClientEmailExceedsMaxLength() {
        var client = buildClientMock();
        client.setEmail("a".repeat(101) + "@test.com");

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client email exceeds max length");
    }

    @Test
    @DisplayName("Should throw ClientException when client already exists")
    void shouldThrowClientException_whenClientAlreadyExists() {
        var client = buildClientMock();
        when(findClientGateway.findClient(client.getCpf())).thenReturn(client);

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client already exists");
    }

    @Test
    @DisplayName("Should throw ClientException when client already exists and cpf is null")
    void shouldThrowClientException_whenClientAlreadyExistsAndCpfIsNull() {
        var client = buildClientMock();
        var response = buildClientMock();
        response.setCpf(null);

        when(findClientGateway.findClient(client.getCpf())).thenReturn(response);

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client already exists");
    }

    @Test
    @DisplayName("Should throw ClientException when client already exists and email is null")
    void shouldThrowClientException_whenClientAlreadyExistsAndEmailIsNull() {
        var client = buildClientMock();
        var response = buildClientMock();
        response.setEmail(null);

        when(findClientGateway.findClient(client.getCpf())).thenReturn(response);

        assertThrows(ClientException.class, () -> createClientUseCase.createClient(client), "Expected ClientException when client already exists");
    }

    @Test
    @DisplayName("Should create client successfully after verify client")
    void shouldCreateClientSuccessfully_afterVerifyClient() {
        var client = buildClientMock();
        var response = buildClientMock();
        response.setEmail(null);
        response.setCpf(null);

        when(findClientGateway.findClient(client.getCpf())).thenReturn(response);
        when(saveClientGateway.saveClient(client)).thenReturn(client);

        assertDoesNotThrow(() -> createClientUseCase.createClient(client));
    }

    @Test
    @DisplayName("Should create client successfully")
    void shouldCreateClientSuccessfully() {
        var client = buildClientMock();
        when(findClientGateway.findClient(client.getCpf())).thenReturn(null);
        when(saveClientGateway.saveClient(client)).thenReturn(client);

        var response = createClientUseCase.createClient(client);

        assertNotNull(response);
        assertEquals(client.getId(), response.getId());
        assertEquals(client.getName(), response.getName());
        assertEquals(client.getCpf(), response.getCpf());
        assertEquals(client.getEmail(), response.getEmail());

        verify(saveClientGateway, times(1)).saveClient(client);
    }
}
