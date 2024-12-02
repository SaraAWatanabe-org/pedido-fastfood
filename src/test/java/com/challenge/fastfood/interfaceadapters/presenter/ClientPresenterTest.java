package com.challenge.fastfood.interfaceadapters.presenter;

import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.interfaceadapters.controller.request.ClientRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildClientMock;
import static com.challenge.fastfood.utils.TestUtils.buildClientRequestMock;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClientPresenterTest {

    @InjectMocks
    ClientPresenter presenter;

    @Test
    @DisplayName("Should convert Client Request to Client")
    public void toClient() {
        ClientRequest clientRequest = buildClientRequestMock();

        assertDoesNotThrow(() -> {
            var response = presenter.toClient(clientRequest);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw Client Exception when request is null")
    public void toClient_clientException() {
        assertThrows(ClientException.class, () -> {
            presenter.toClient(null);
        });
    }

    @Test
    @DisplayName("Should convert Client to Client Request")
    public void toClientRequest() {
        Client request = buildClientMock();

        assertDoesNotThrow(() -> {
            var response = presenter.toClientRequest(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw Client Exception when request is null")
    public void toClientRequest_clientException() {
        assertThrows(ClientException.class, () -> {
            presenter.toClientRequest(null);
        });
    }

    @Test
    @DisplayName("Should convert Client to Client Response")
    public void toClientResponse() {
        Client request = buildClientMock();

        assertDoesNotThrow(() -> {
            var response = presenter.toClientResponse(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw Client Exception when request is null")
    public void toClientResponse_clientException() {
        assertThrows(ClientException.class, () -> {
            presenter.toClientResponse(null);
        });
    }

}
