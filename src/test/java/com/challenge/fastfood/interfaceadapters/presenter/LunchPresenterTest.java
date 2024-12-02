package com.challenge.fastfood.interfaceadapters.presenter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.challenge.fastfood.utils.TestUtils.buildLunchListMock;
import static com.challenge.fastfood.utils.TestUtils.buildLunchMock;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LunchPresenterTest {

    @Test
    @DisplayName("Should convert Lunch to LunchResponse")
    public void lunchToLunchResponse() {
        var request = buildLunchMock();

        assertDoesNotThrow(() -> {
            var response = LunchPresenter.lunchToLunchResponse(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when request is null")
    public void lunchToLunchResponse_error() {
        assertThrows(IllegalArgumentException.class, () -> LunchPresenter.lunchToLunchResponse(null));
    }

    @Test
    @DisplayName("Should convert Lunch list to LunchResponse list")
    public void lunchesToLunchResponses() {
        var request = buildLunchListMock();

        assertDoesNotThrow(() -> {
            var response = LunchPresenter.lunchesToLunchResponses(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when request is null")
    public void lunchesToLunchResponses_error() {
        assertThrows(IllegalArgumentException.class, () -> LunchPresenter.lunchesToLunchResponses(null));
    }

}