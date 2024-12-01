package com.challenge.fastfood.usecases.lunchitem;

import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.EditLunchItemGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.usecases.lunchItem.EditLunchItemUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildLunchItemMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditLunchItemUseCaseTest {

    @InjectMocks
    EditLunchItemUseCase editLunchItemUseCase;

    @Mock
    EditLunchItemGatewayInterface editLunchItemGatewayInterface;

    @Mock
    FindLunchItemsGatewayInterface findLunchItemsGatewayInterface;

    final Long LUNCH_ITEM_ID = 1L;

    @Test
    @DisplayName("Should throw LunchItemException when idLunch is null")
    void editStatusLunchItem_shouldThrowLunchItemExceptionWhenIdLunchIsNull() {
        assertThrows(LunchItemException.class, () -> editLunchItemUseCase.editStatusLunchItem(null), "idLunchItem and status are required");
    }

    @Test
    @DisplayName("Should throw LunchItemException when not found lunchItem")
    void editStatusLunchItem_shouldThrowLunchItemExceptionWhenNotFoundLunchItem() {
        when(findLunchItemsGatewayInterface.findLunchItemById(LUNCH_ITEM_ID)).thenReturn(null);

        assertThrows(LunchItemException.class, () -> editLunchItemUseCase.editStatusLunchItem(LUNCH_ITEM_ID), "Lunch item not found");
    }

    @Test
    @DisplayName("Should edit lunchItem status")
    void editStatusLunchItem() {
        var lunchItem = buildLunchItemMock();

        when(findLunchItemsGatewayInterface.findLunchItemById(LUNCH_ITEM_ID)).thenReturn(lunchItem);
        when(editLunchItemGatewayInterface.editStatusLunchItem(LUNCH_ITEM_ID, false)).thenReturn(true);

        assertDoesNotThrow(() -> {
            assertTrue(editLunchItemUseCase.editStatusLunchItem(LUNCH_ITEM_ID));
        });
    }

    @Test
    @DisplayName("Should throw LunchItemException when idLunch is null")
    void editLunchItem_shouldThrowLunchItemExceptionWhenIdLunchIsNull() {
        var lunchItem = buildLunchItemMock();

        assertThrows(LunchItemException.class, () -> editLunchItemUseCase.editLunchItem(null, lunchItem), "idLunchItem and lunchItem are required");
    }

    @Test
    @DisplayName("Should throw LunchItemException when LunchItem is null")
    void editLunchItem_shouldThrowLunchItemExceptionWhenLunchItemIsNull() {
        assertThrows(LunchItemException.class, () -> editLunchItemUseCase.editLunchItem(LUNCH_ITEM_ID, null), "idLunchItem and lunchItem are required");
    }

    @Test
    @DisplayName("Should throw LunchItemException when not found LunchItem by id")
    void editLunchItem_shouldThrowLunchItemExceptionWhenNotFoundLunchItemById() {
        var lunchItem = buildLunchItemMock();

        when(findLunchItemsGatewayInterface.findLunchItemById(LUNCH_ITEM_ID)).thenReturn(null);

        assertThrows(LunchItemException.class, () -> editLunchItemUseCase.editLunchItem(LUNCH_ITEM_ID, lunchItem), "Lunch item not found");
    }

    @Test
    @DisplayName("Should throw LunchItemException when LunchItem price is under or equal 0")
    void editLunchItem_shouldThrowLunchItemExceptionWhenLunchItemPriceIsUnderOrEqual0() {
        var lunchItem = buildLunchItemMock();
        lunchItem.setPrice((float) 0);

        when(findLunchItemsGatewayInterface.findLunchItemById(LUNCH_ITEM_ID)).thenReturn(lunchItem);

        assertThrows(LunchItemException.class, () -> editLunchItemUseCase.editLunchItem(LUNCH_ITEM_ID, lunchItem), "Price must be greater than 0");
    }

    @Test
    @DisplayName("Should edit LunchItem")
    void editLunchItem() {
        var lunchItem = buildLunchItemMock();

        when(findLunchItemsGatewayInterface.findLunchItemById(LUNCH_ITEM_ID)).thenReturn(lunchItem);
        when(editLunchItemGatewayInterface.editLunchItem(lunchItem, LUNCH_ITEM_ID)).thenReturn(lunchItem);

        assertDoesNotThrow(() -> {
            assertNotNull(editLunchItemUseCase.editLunchItem(LUNCH_ITEM_ID, lunchItem));
        });
    }
}
