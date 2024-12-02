package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.interfaceadapters.controller.request.ClientRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.ClientResponse;
import com.challenge.fastfood.interfaceadapters.interfaces.client.ClientAdapterInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildClientMock;
import static com.challenge.fastfood.utils.TestUtils.buildClientRequestMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @InjectMocks
    ClientController controller;

    @Mock
    ClientAdapterInterface clientAdapter;

    private final String NOME = "Joao";
    private final String CPF = "12345678900";

    @Test
    @DisplayName("Should create a client")
    public void testCreateClient() {
        ClientRequest clientRequest = buildClientRequestMock();

        Client expectedClient = buildClientMock();
        expectedClient.setName(NOME);
        expectedClient.setCpf(CPF);

        when(clientAdapter.saveClient(any(Client.class))).thenReturn(expectedClient);

        ClientResponse response = controller.createClient(clientRequest);

        assertEquals(NOME, response.name());
        assertEquals(expectedClient.getEmail(), response.email());
    }

    @Test
    @DisplayName("Should find a client")
    public void testFindClient() {
        Client expectedClient = buildClientMock();
        expectedClient.setName(NOME);
        expectedClient.setCpf(CPF);

        when(clientAdapter.findClient(CPF)).thenReturn(expectedClient);

        ClientResponse response = controller.findClient(CPF);

        assertEquals(NOME, response.name());
        assertEquals(expectedClient.getEmail(), response.email());
    }

}
