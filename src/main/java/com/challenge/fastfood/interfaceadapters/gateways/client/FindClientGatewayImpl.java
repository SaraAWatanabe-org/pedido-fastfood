package com.challenge.fastfood.interfaceadapters.gateways.client;

import com.challenge.fastfood.interfaceadapters.interfaces.client.ClientAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.entities.Client;

public class FindClientGatewayImpl implements FindClientGatewayInterface {

    private final ClientAdapterInterface clientAdapter;

    public FindClientGatewayImpl(ClientAdapterInterface clientAdapter) {
        this.clientAdapter = clientAdapter;
    }

    @Override
    public Client findClient(String cpf) {
        return clientAdapter.findClient(cpf);
    }

    @Override
    public Client findClientById(Long id) {
        return clientAdapter.findClientById(id);
    }

}
