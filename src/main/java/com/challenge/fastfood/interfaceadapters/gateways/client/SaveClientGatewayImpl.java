package com.challenge.fastfood.interfaceadapters.gateways.client;

import com.challenge.fastfood.interfaceadapters.interfaces.client.ClientAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.client.SaveClientGatewayInterface;
import com.challenge.fastfood.entities.Client;
import jakarta.transaction.Transactional;


public class SaveClientGatewayImpl implements SaveClientGatewayInterface {

    private final ClientAdapterInterface clientAdapter;

    public SaveClientGatewayImpl(ClientAdapterInterface clientAdapter) {
        this.clientAdapter = clientAdapter;
    }

    @Override
    @Transactional
    public Client saveClient(Client client) {
        return clientAdapter.saveClient(client);
    }
}
