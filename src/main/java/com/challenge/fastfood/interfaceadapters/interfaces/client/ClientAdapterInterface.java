package com.challenge.fastfood.interfaceadapters.interfaces.client;

import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.framework.persistence.client.ClientEntity;

public interface ClientAdapterInterface {

    Client findClientById(Long id);
    Client findClient(String cpf);
    Client saveClient(Client client);

}
