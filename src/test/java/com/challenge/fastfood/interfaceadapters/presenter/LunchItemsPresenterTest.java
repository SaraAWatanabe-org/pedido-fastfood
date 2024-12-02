package com.challenge.fastfood.interfaceadapters.presenter;

import com.challenge.fastfood.entities.LunchItemType;
import com.challenge.fastfood.interfaceadapters.controller.response.LunchItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LunchItemsPresenterTest {

    @Test
    @DisplayName("Should convert LunchItem to LunchItemRequest")
    public void lunchItemToLunchItemRequest() {
        var request = buildLunchItemMock();

        assertDoesNotThrow(() -> {
            var response = LunchItemsPresenter.lunchItemToLunchItemRequest(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when request is null")
    public void lunchItemToLunchItemRequest_error() {
        assertThrows(IllegalArgumentException.class, () -> LunchItemsPresenter.lunchItemToLunchItemRequest(null));
    }

    @Test
    @DisplayName("Should convert LunchItemRequest to LunchItem")
    public void lunchItemRequestToLunchItem() {
        var request = buildLunchItemRequestMock();

        assertDoesNotThrow(() -> {
            var response = LunchItemsPresenter.lunchItemRequestToLunchItem(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when request is null")
    public void lunchItemRequestToLunchItem_error() {
        assertThrows(IllegalArgumentException.class, () -> LunchItemsPresenter.lunchItemRequestToLunchItem(null));
    }

    @Test
    @DisplayName("Should convert LunchItem to LunchItemResponse")
    public void lunchItemToLunchItemResponse() {
        var request = buildLunchItemMock();

        assertDoesNotThrow(() -> {
            var response = LunchItemsPresenter.lunchItemToLunchItemResponse(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when request is null")
    public void lunchItemToLunchItemResponse_error() {
        assertThrows(IllegalArgumentException.class, () -> LunchItemsPresenter.lunchItemToLunchItemResponse(null));
    }

    @Test
    @DisplayName("Should convert LunchItemResponse to LunchItem")
    public void lunchItemResponseToLunchItem() {
        var request = LunchItemResponse.builder()
                .id(3L)
                .name("Joao")
                .price(2000F)
                .type(LunchItemType.DRINK)
                .build();

        assertDoesNotThrow(() -> {
            var response = LunchItemsPresenter.lunchItemResponseToLunchItem(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when request is null")
    public void lunchItemResponseToLunchItem_error() {
        assertThrows(IllegalArgumentException.class, () -> LunchItemsPresenter.lunchItemResponseToLunchItem(null));
    }

    @Test
    @DisplayName("Should convert LunchItem list to LunchItemResponse list")
    public void lunchItemToLunchItemResponseList() {
        var request = buildLunchItemListMock();

        assertDoesNotThrow(() -> {
            var response = LunchItemsPresenter.lunchItemToLunchItemResponseList(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when request is null")
    public void lunchItemToLunchItemResponseList_error() {
        assertThrows(IllegalArgumentException.class, () -> LunchItemsPresenter.lunchItemToLunchItemResponseList(null));
    }

}