package com.challenge.fastfood.usecases.client;

import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.entities.Client;

public class FindClientUseCase {

    private final FindClientGatewayInterface findClientGatewayInterface;

    public FindClientUseCase(FindClientGatewayInterface findClientGatewayInterface) {
        this.findClientGatewayInterface = findClientGatewayInterface;
    }

    public Client findClient(String cpf) {

        if (cpf == null) {
            throw new ClientException("The CPF is required");
        }

        Client client = findClientGatewayInterface.findClient(cpf);

        if (client == null || client.getCpf() == null || client.getEmail() == null) {
            throw new ClientException("Client not found");
        }
        return client;
    }
}
