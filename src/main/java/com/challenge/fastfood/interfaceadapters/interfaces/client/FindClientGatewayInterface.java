package com.challenge.fastfood.interfaceadapters.interfaces.client;

import com.challenge.fastfood.entities.Client;

public interface FindClientGatewayInterface {


    Client findClientById(Long id);
    Client findClient(String cpf);
}
