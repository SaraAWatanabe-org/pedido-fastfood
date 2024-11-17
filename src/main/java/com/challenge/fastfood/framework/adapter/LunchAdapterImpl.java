package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchStatus;
import com.challenge.fastfood.framework.mapstruct.LunchItemMapper;
import com.challenge.fastfood.framework.mapstruct.LunchMapper;
import com.challenge.fastfood.framework.persistence.client.ClientEntity;
import com.challenge.fastfood.framework.persistence.client.ClientRepository;
import com.challenge.fastfood.framework.persistence.lunch.LunchEntity;
import com.challenge.fastfood.framework.persistence.lunch.LunchRepository;
import com.challenge.fastfood.framework.persistence.lunchItem.LunchItemEntity;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LunchAdapterImpl implements LunchAdapterInterface {

    @Value("${production.service.url}")
    private String productionServiceUrl;
    private final LunchRepository lunchRepository;
    private final ClientRepository clientRepository;
    private final LunchItemMapper lunchItemMapper;
    private final LunchMapper lunchMapper;
    private final ObjectMapper objectMapper;


    @Override
    public List<Lunch> findLunchs() {
        List<LunchEntity> lunchRepositoryAll = lunchRepository.findAll();
        return lunchMapper.lunchsEntityToLunchs(lunchRepositoryAll);
    }

    @Override
    public Lunch findLunchById(Long id) {
        LunchEntity lunchEntity = lunchRepository.findById(id).orElse(null);
        return lunchMapper.lunchEntityToLunch(lunchEntity);
    }

    @Override
    public Lunch saveLunch(Lunch lunch) {
        List<LunchItemEntity> lunchItems = new ArrayList<>();
        ClientEntity clientEntity = null;

        for(LunchItem item : lunch.getLunchItems()){
            LunchItemEntity lunchItemEntity = lunchItemMapper.lunchItemToLunchItemEntity(item);
            lunchItems.add(lunchItemEntity);
        }


        clientEntity = getClientEntity(lunch, clientEntity);

        LunchEntity lunchEntity = new LunchEntity();
        lunchEntity.setLunchItems(lunchItems);
        lunchEntity.setClient(clientEntity);
        lunchEntity.setStatus(LunchStatus.RECEBIDO.getDescricao());
        lunchEntity.setDate(LocalDateTime.now());
        double price = 0;
        for (LunchItemEntity lunchItem : lunchItems) {
            price += lunchItem.getPrice();
        }
        lunchEntity.setPriceTotal(price);

        LunchEntity save = lunchRepository.save(lunchEntity);

        return lunchMapper.lunchEntityToLunch(save);
    }

    private ClientEntity getClientEntity(Lunch lunch, ClientEntity clientEntity) {
        if(lunch.getClient() != null && lunch.getClient().getId() != null) {
            clientEntity = clientRepository.findById(lunch.getClient().getId()).orElse(null);
        }
        return clientEntity;
    }

    @Override
    public Lunch editLunch(Lunch lunch) {
        LunchEntity lunchEntity = lunchMapper.lunchToLunchEntity(lunch);
        LunchEntity save = lunchRepository.save(lunchEntity);
        return lunchMapper.lunchEntityToLunch(save);

    }

    @Override
    public void httpRequestPrepareLunch(Lunch lunch) throws JsonProcessingException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(productionServiceUrl+"/prepare"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(lunch)))
                .header("Content-Type", "application/json")
                .build();
        try {
           httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar requisição para o serviço de preparo de pedido", e);
        }
    }
}
