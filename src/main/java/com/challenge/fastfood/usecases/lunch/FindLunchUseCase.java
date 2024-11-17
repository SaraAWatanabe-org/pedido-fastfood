package com.challenge.fastfood.usecases.lunch;

import com.challenge.fastfood.entities.LunchStatus;
import com.challenge.fastfood.interfaceadapters.controller.request.LunchRequest;
import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.entities.Client;
import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.entities.Lunch;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindLunchUseCase {

    private final FindLunchGatewayInterface findLunchGatewayInterface;

    public FindLunchUseCase(FindLunchGatewayInterface findLunchGatewayInterface) {
        this.findLunchGatewayInterface = findLunchGatewayInterface;
    }

    public List<Lunch> findLunchs() {
        List<Lunch> allLunches = findLunchGatewayInterface.findLunchs();

        // Filtra os pedidos com status "Finalizado"
        List<Lunch> filteredLunches = allLunches.stream()
                .filter(lunch -> !LunchStatus.FINALIZADO.equals(lunch.getStatus()))
                .toList();

        // Ordena por status e data (mais antigos primeiro)
        List<Lunch> sortedLunches = filteredLunches.stream()
                .sorted(Comparator.comparing(Lunch::getStatus)
                        .thenComparing(Lunch::getDate))
                .collect(Collectors.toList());

        return sortedLunches;
    }

    public Lunch findLunchById(Long id) {
        return findLunchGatewayInterface.findLunchById(id);
    }

    public Lunch getLunch(LunchRequest lunchRequest, FindClientGatewayInterface findClientGatewayInterface) {
        Lunch lunch = new Lunch();
        if (lunchRequest.clientId() != null) {
            Client client = findClientGatewayInterface.findClientById(lunchRequest.clientId());
            if (client == null) {
                throw new ClientException("Client id doesn't represent any existing client");
            }
            client.setId(lunchRequest.clientId());
            lunch.setClient(client);
        }
        return lunch;
    }
}
