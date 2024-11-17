package com.challenge.fastfood.framework.mapstruct;


import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.interfaceadapters.controller.request.ClientRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.ClientResponse;
import com.challenge.fastfood.framework.persistence.client.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {




    @Mapping(target = "id", ignore = true)
    Client clientRequestToClient(ClientRequest clientRequest);

    ClientResponse clientToClientResponse(Client client);

//    List<ClientResponse> clientListToClientResponse(List<Client> client);
    @Mapping(target = "lunches", ignore = true)
    ClientEntity clientToClientEntity(Client client);

    Client clientEntityToClient(ClientEntity clientEntity);

    List<Client> clientListEntityToClient(List<ClientEntity> clientEntity);

}
