package com.challenge.fastfood.usecases.lunchitem;

import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.SaveLunchItemGatewayInterface;
import com.challenge.fastfood.usecases.lunchItem.CreateLunchItemUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

import static com.challenge.fastfood.utils.TestUtils.buildLunchItemMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateLunchItemUseCaseTest {

    @InjectMocks
    CreateLunchItemUseCase createLunchItemUseCase;

    @Mock
    SaveLunchItemGatewayInterface saveLunchItemGatewayInterface;

    @Mock
    FindLunchItemsGatewayInterface findLunchItemsGatewayInterface;

    @Test
    @DisplayName("Should throw LunchItemException when LunchItem already exists")
    void createLunchItem_shouldThrowLunchItemExceptionWhenLunchItemAlreadyExists() {
        var lunchItem = buildLunchItemMock();

        when(findLunchItemsGatewayInterface.findLunchItemByName(lunchItem.getName())).thenReturn(lunchItem);

        assertThrows(LunchItemException.class, () -> createLunchItemUseCase.createLunchItem(lunchItem), "LunchItem already exists");
    }

    @Test
    @DisplayName("Should create LunchItem")
    void createLunchItem_shouldCreateLunchItem() {
        var lunchItem = buildLunchItemMock();

        when(findLunchItemsGatewayInterface.findLunchItemByName(lunchItem.getName())).thenReturn(null);
        when(saveLunchItemGatewayInterface.saveLunchItem(lunchItem)).thenReturn(lunchItem);

        AtomicReference<LunchItem> response = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            response.set(createLunchItemUseCase.createLunchItem(lunchItem));
        });
        assertNotNull(response.get());
    }
}
