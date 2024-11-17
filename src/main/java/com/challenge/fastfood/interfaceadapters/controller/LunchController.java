package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.interfaceadapters.controller.request.LunchRequest;
import com.challenge.fastfood.interfaceadapters.controller.request.PaymentRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.LunchResponse;
import com.challenge.fastfood.interfaceadapters.gateways.client.FindClientGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.lunch.EditLunchGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.lunch.SaveLunchGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.payment.PaymentImplGatewayImpl;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.EditLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.interfaceadapters.presenter.LunchPresenter;
import com.challenge.fastfood.interfaceadapters.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.SaveLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import com.challenge.fastfood.interfaceadapters.presenter.PaymentPresenter;
import com.challenge.fastfood.usecases.lunch.EditLunchUseCase;
import com.challenge.fastfood.usecases.lunchItem.FindLunchItemsUseCase;
import com.challenge.fastfood.interfaceadapters.gateways.lunch.FindLunchGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.lunchItem.FindLunchItemsGatewayImpl;
import com.challenge.fastfood.interfaceadapters.interfaces.client.ClientAdapterInterface;
import com.challenge.fastfood.usecases.lunch.CreateLunchUseCase;
import com.challenge.fastfood.usecases.lunch.FindLunchUseCase;
import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.usecases.payment.PaymentUseCase;

import java.util.ArrayList;
import java.util.List;

public class LunchController {


    private final ClientAdapterInterface clientAdapter;
    private final LunchAdapterInterface lunchAdapter;
    private final LunchItemAdapterInterface lunchItemAdapter;
    private final PaymentAdapterInterface paymentAdapterInterface;

    public LunchController(ClientAdapterInterface clientAdapter, LunchAdapterInterface lunchAdapter, LunchItemAdapterInterface lunchItemAdapter, PaymentAdapterInterface paymentAdapterInterface) {
        this.clientAdapter = clientAdapter;
        this.lunchAdapter = lunchAdapter;
        this.lunchItemAdapter = lunchItemAdapter;
        this.paymentAdapterInterface = paymentAdapterInterface;

    }

    public LunchResponse createLunch(LunchRequest lunchRequest) {

        FindClientGatewayInterface findClientGatewayInterface = new FindClientGatewayImpl(clientAdapter);
        FindLunchGatewayInterface findLunchGatewayInterface = new FindLunchGatewayImpl(lunchAdapter);
        SaveLunchGatewayInterface saveLunchGatewayInterface = new SaveLunchGatewayImpl(lunchAdapter);
        FindLunchUseCase findLunchUseCase  = new FindLunchUseCase(findLunchGatewayInterface);
        CreateLunchUseCase createLunchUseCase = new CreateLunchUseCase(saveLunchGatewayInterface);

        List<LunchItem> lunchItems = new ArrayList<>();

        mapperLunch(lunchRequest.drink(), lunchItems);
        mapperLunch(lunchRequest.snack(), lunchItems);
        mapperLunch(lunchRequest.accompaniment(), lunchItems);
        mapperLunch(lunchRequest.dessert(), lunchItems);

        Lunch lunch = findLunchUseCase.getLunch(lunchRequest, findClientGatewayInterface);
        lunch.setLunchItems(lunchItems);
        Lunch useCaseLunch = createLunchUseCase.createLunch(lunch);

        PaymentProcessGatewayInterface paymentProcessGatewayInterface = new PaymentImplGatewayImpl(paymentAdapterInterface);

        PaymentUseCase paymentUseCase = new PaymentUseCase(paymentProcessGatewayInterface,findLunchGatewayInterface);
        PaymentPresenter paymentPresenter = new PaymentPresenter();
        Payment toPayment = paymentPresenter.toPayment(new PaymentRequest(useCaseLunch.getPriceTotal(), useCaseLunch.getClient().getCpf(), useCaseLunch.getId()));
        paymentUseCase.execute(toPayment);
        return LunchPresenter.lunchToLunchResponse(useCaseLunch);
    }



    private void mapperLunch(List<Long> lunchRequest, List<LunchItem> lunchItems) {
        FindLunchItemsGatewayInterface findLunchItemsGatewayInterface = new FindLunchItemsGatewayImpl(lunchItemAdapter);
        FindLunchItemsUseCase findLunchItemsUseCase = new FindLunchItemsUseCase(findLunchItemsGatewayInterface);
        for (Long lunchItem : lunchRequest) {
            LunchItem itemById = findLunchItemsUseCase.findLunchItems(lunchItem);
            if (itemById != null) {
                lunchItems.add(itemById);
            }
        }
    }

    public List<LunchResponse> findLunchs() {
        FindLunchGatewayInterface findLunchGatewayInterface = new FindLunchGatewayImpl(lunchAdapter);
        FindLunchUseCase findLunchUseCase  = new FindLunchUseCase(findLunchGatewayInterface);
        List<Lunch> lunchs = findLunchUseCase.findLunchs();
        return LunchPresenter.lunchesToLunchResponses(lunchs);
    }

    public LunchResponse findLunchById(Long id) {
        FindLunchGatewayInterface findLunchGatewayInterface = new FindLunchGatewayImpl(lunchAdapter);
        FindLunchUseCase findLunchUseCase  = new FindLunchUseCase(findLunchGatewayInterface);
        Lunch lunchById = findLunchUseCase.findLunchById(id);
        return  LunchPresenter.lunchToLunchResponse(lunchById);
    }

    public LunchResponse editLunchStatus(Long lunchId, String newStatus){
        FindLunchGatewayInterface findLunchGatewayInterface = new FindLunchGatewayImpl(lunchAdapter);
        EditLunchGatewayInterface editLunchGatewayInterface = new EditLunchGatewayImpl(lunchAdapter);
        PaymentProcessGatewayInterface paymentProcessGatewayInterface = new PaymentImplGatewayImpl(paymentAdapterInterface);

        EditLunchUseCase editLunchUseCase = new EditLunchUseCase(findLunchGatewayInterface,editLunchGatewayInterface,paymentProcessGatewayInterface);
        Lunch lunch = editLunchUseCase.updateLunchStatus(lunchId, newStatus);
        return LunchPresenter.lunchToLunchResponse(lunch);

    }
}