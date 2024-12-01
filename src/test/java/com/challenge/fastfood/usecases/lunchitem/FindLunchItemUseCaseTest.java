package com.challenge.fastfood.usecases.lunchitem;

import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchItemType;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.usecases.lunchItem.FindLunchItemsUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

import static com.challenge.fastfood.utils.TestUtils.buildLunchItemListMock;
import static com.challenge.fastfood.utils.TestUtils.buildLunchItemMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindLunchItemUseCaseTest {

    @InjectMocks
    FindLunchItemsUseCase findLunchItemsUseCase;

    @Mock
    FindLunchItemsGatewayInterface findLunchItemsGatewayInterface;

    final Long LUNCH_ITEM_ID = 1L;
    final String NAME = "Beer";

    @Test
    @DisplayName("Should find LunchItems by item type")
    void findLunchItems_byItemType() {
        var lunchItem = buildLunchItemListMock();

        when(findLunchItemsGatewayInterface.findLunchItems(LunchItemType.ACCOMPANIMENT)).thenReturn(lunchItem);

        assertDoesNotThrow(() -> findLunchItemsUseCase.findLunchItems(LunchItemType.ACCOMPANIMENT));
    }

    @Test
    @DisplayName("Should find LunchItems by id")
    void findLunchItems_byId() {
        var lunchItem = buildLunchItemMock();

        when(findLunchItemsGatewayInterface.findLunchItemById(LUNCH_ITEM_ID)).thenReturn(lunchItem);

        assertDoesNotThrow(() -> findLunchItemsUseCase.findLunchItems(LUNCH_ITEM_ID));
    }

    @Test
    @DisplayName("Should throw LunchItemException when name is null")
    void findLunchItemByName_shouldThrowLunchItemExceptionWhenNameIsNull() {
        assertThrows(LunchItemException.class, () -> findLunchItemsUseCase.findLunchItemByName(null), "Invalid lunch item, name is required");
    }

    @Test
    @DisplayName("Should find LunchItems by name")
    void findLunchItemByName() {
        var lunchItem = buildLunchItemMock();

        when(findLunchItemsGatewayInterface.findLunchItemByName(NAME)).thenReturn(lunchItem);

        AtomicReference<LunchItem> response = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            response.set(findLunchItemsUseCase.findLunchItemByName(NAME));
        });
        assertNotNull(response.get());
    }
}
