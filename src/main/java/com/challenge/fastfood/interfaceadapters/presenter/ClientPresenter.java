package com.challenge.fastfood.interfaceadapters.presenter;

import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.interfaceadapters.controller.request.ClientRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.ClientResponse;

public class ClientPresenter {

    public Client toClient(ClientRequest clientRequest) {
        if (clientRequest == null) {
            throw new ClientException("ClientRequest cannot be null");
        }
        Client client = new Client();
        client.setName(clientRequest.name());
        client.setEmail(clientRequest.email());
        client.setCpf(clientRequest.cpf());
        return client;
    }

    public ClientRequest toClientRequest(Client client) {
        if (client == null) {
            throw new ClientException("Client cannot be null");
        }
        return new ClientRequest(
                client.getName(),
                client.getEmail(),
                client.getCpf()
        );
    }

    public ClientResponse toClientResponse(Client client) {
        if (client == null) {
            throw new ClientException("Client cannot be null");
        }
        return new ClientResponse(
                client.getName(),
                client.getEmail()
        );
    }
}
