package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.framework.mapstruct.ClientMapper;
import com.challenge.fastfood.framework.persistence.client.ClientEntity;
import com.challenge.fastfood.framework.persistence.client.ClientRepository;
import com.challenge.fastfood.interfaceadapters.interfaces.client.ClientAdapterInterface;
import org.springframework.stereotype.Component;


@Component
public class ClientAdapterImpl implements ClientAdapterInterface {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientAdapterImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client findClientById(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElse(null);
        return clientMapper.clientEntityToClient(clientEntity);
    }

    @Override
    public Client findClient(String cpf) {
        ClientEntity byCpf = clientRepository.findByCpf(cpf);
        return clientMapper.clientEntityToClient(byCpf);
    }

    @Override
    public Client saveClient(Client client) {
        ClientEntity clientEntity = clientMapper.clientToClientEntity(client);
        ClientEntity save = clientRepository.save(clientEntity);
        return clientMapper.clientEntityToClient(save);
    }
}
