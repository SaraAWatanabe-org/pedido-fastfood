package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.interfaceadapters.controller.request.LunchItemRequest;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildLunchItemRequestMock;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LunchItemsControllerTest {

    @InjectMocks
    LunchItemsController controller;
    @Mock
    LunchItemAdapterInterface lunchItemAdapter;

    private final Long ID_LUNCH_ITEM = 3L;
    private final String TYPE = "DRINK";
    private final LunchItemRequest REQUEST = buildLunchItemRequestMock();

    @Test
    @DisplayName("Should throw IllegalArgumentException")
    public void createLunchItem() {
        assertThrows(IllegalArgumentException.class, () -> controller.createLunchItem(REQUEST));
    }

    @Test
    @DisplayName("Should throw LunchItemException")
    public void editStatusLunchItem() {
        assertThrows(LunchItemException.class, () -> controller.editStatusLunchItem(ID_LUNCH_ITEM));
    }

    @Test
    @DisplayName("Should throw LunchItemException")
    public void editLunchItem() {
        assertThrows(LunchItemException.class, () -> controller.editLunchItem(ID_LUNCH_ITEM, REQUEST));
    }

    @Test
    @DisplayName("Should find Lunch Items")
    public void findLunchItems() {
        assertDoesNotThrow(() -> controller.findLunchItems(TYPE));
    }

    @Test
    @DisplayName("Should find LunchItem By Name")
    public void findLunchItemByName() {
        assertDoesNotThrow(() -> controller.findLunchItemByName(TYPE));
    }


}
