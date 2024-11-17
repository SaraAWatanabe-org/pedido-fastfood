package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.interfaceadapters.controller.request.ClientRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.ClientResponse;
import com.challenge.fastfood.interfaceadapters.presenter.ClientPresenter;
import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.client.SaveClientGatewayInterface;
import com.challenge.fastfood.usecases.client.CreateClientUseCase;
import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.usecases.client.FindClientUseCase;
import com.challenge.fastfood.interfaceadapters.gateways.client.FindClientGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.client.SaveClientGatewayImpl;
import com.challenge.fastfood.interfaceadapters.interfaces.client.ClientAdapterInterface;

public class ClientController {

    private final ClientAdapterInterface clientAdapter;

    public ClientController(ClientAdapterInterface clientAdapter) {
        this.clientAdapter = clientAdapter;
    }

    public ClientResponse createClient(ClientRequest clientRequest) {
        ClientPresenter clientPresenter = new ClientPresenter();
        Client toClient = clientPresenter.toClient(clientRequest);
        SaveClientGatewayInterface saveClientGatewayInterface = new SaveClientGatewayImpl(clientAdapter);
        FindClientGatewayInterface findClientGatewayInterface = new FindClientGatewayImpl(clientAdapter);
        CreateClientUseCase createClientUseCase = new CreateClientUseCase(saveClientGatewayInterface,findClientGatewayInterface);
        Client client = createClientUseCase.createClient(toClient);
        return  clientPresenter.toClientResponse(client);
    }

    public ClientResponse findClient(String cpf) {
        ClientPresenter clientPresenter = new ClientPresenter();
        FindClientGatewayInterface findClientGatewayInterface = new FindClientGatewayImpl(clientAdapter);
        FindClientUseCase findClientUseCase = new FindClientUseCase(findClientGatewayInterface);
        Client client = findClientUseCase.findClient(cpf);
        return  clientPresenter.toClientResponse(client);
    }
}