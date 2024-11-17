package com.challenge.fastfood.usecases.client;


import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.client.SaveClientGatewayInterface;
import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.entities.Client;

public class CreateClientUseCase  {

    private final SaveClientGatewayInterface saveClientGatewayInterface;
    private final FindClientGatewayInterface findClientGatewayInterface;

    public CreateClientUseCase(SaveClientGatewayInterface saveClientGatewayInterface, FindClientGatewayInterface findClientGatewayInterface) {
        this.saveClientGatewayInterface = saveClientGatewayInterface;
        this.findClientGatewayInterface = findClientGatewayInterface;
    }

    public Client createClient(Client client) {

        if (client.getName() == null || client.getCpf() == null || client.getEmail() == null) {
            throw new ClientException("Invalid client, name, cpf and email are required");
        }

        if (client.getName().length() > 100) {
            throw new ClientException("Invalid name, max 100 characters");
        }

        if (client.getCpf().length() > 14 || client.getCpf().length() < 11) {
            throw new ClientException("Invalid CPF, max 14 characters and min 11 characters");
        }

        if (client.getEmail().length() > 100) {
            throw new ClientException("Invalid email, max 100 characters");
        }

        Client clientFind = findClientGatewayInterface.findClient(client.getCpf());

        if(clientFind != null ){
            if (clientFind.getCpf() != null || clientFind.getEmail() != null) {
                throw new ClientException("Client already exists");
            }
        }

        return saveClientGatewayInterface.saveClient(client);
    }

}

