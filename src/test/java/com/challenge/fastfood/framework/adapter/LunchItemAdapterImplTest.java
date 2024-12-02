package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.entities.LunchItem;
import com.challenge.fastfood.entities.LunchItemType;
import com.challenge.fastfood.framework.mapstruct.LunchItemMapper;
import com.challenge.fastfood.framework.mapstruct.LunchMapper;
import com.challenge.fastfood.framework.persistence.client.ClientRepository;
import com.challenge.fastfood.framework.persistence.lunch.LunchRepository;
import com.challenge.fastfood.framework.persistence.lunchItem.LunchItemEntity;
import com.challenge.fastfood.framework.persistence.lunchItem.LunchItemsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static com.challenge.fastfood.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LunchItemAdapterImplTest {

    @InjectMocks
    LunchItemAdapterImpl adapter;
    @Mock
    LunchItemsRepository lunchItemsRepository;
    @Mock
    LunchItemMapper lunchItemMapper;

    private final Long ID = 33L;
    private final String NOME = "Joao";

    @Test
    @DisplayName("Should find By Type And Status True")
    public void findByTypeAndStatusTrue() {
        var lunchItem = List.of(buildLunchItemEntityMock());

        when(lunchItemsRepository.findByTypeAndStatusTrue(any())).thenReturn(lunchItem);
        when(lunchItemMapper.lunchItemEntityToLunchItem(lunchItem)).thenReturn(List.of(buildLunchItemMock()));

        assertDoesNotThrow(() -> {
            var response = adapter.findByTypeAndStatusTrue(LunchItemType.DRINK);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should find By Status True")
    public void findByStatusTrue() {
        var lunchItem = List.of(buildLunchItemEntityMock());

        when(lunchItemsRepository.findByStatusTrue()).thenReturn(lunchItem);
        when(lunchItemMapper.lunchItemEntityToLunchItem(lunchItem)).thenReturn(List.of(buildLunchItemMock()));

        assertDoesNotThrow(() -> {
            var response = adapter.findByStatusTrue();
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should find By Name")
    public void findByName() {
        var lunchItem = buildLunchItemEntityMock();

        when(lunchItemsRepository.findByName(any())).thenReturn(lunchItem);
        when(lunchItemMapper.lunchItemEntityToLunchItem(lunchItem)).thenReturn(buildLunchItemMock());

        assertDoesNotThrow(() -> {
            var response = adapter.findByName(NOME);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should find By Id And Status True")
    public void findByIdAndStatusTrue() {
        var lunchItem = buildLunchItemEntityMock();

        when(lunchItemsRepository.findByIdAndStatusTrue(any())).thenReturn(lunchItem);
        when(lunchItemMapper.lunchItemEntityToLunchItem(lunchItem)).thenReturn(buildLunchItemMock());

        assertDoesNotThrow(() -> {
            var response = adapter.findByIdAndStatusTrue(ID);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should save Lunch Item")
    public void saveLunchItem() {
        var lunchItem = buildLunchItemEntityMock();
        var request = buildLunchItemMock();

        when(lunchItemMapper.lunchItemToLunchItemEntity(request)).thenReturn(buildLunchItemEntityMock());
        when(lunchItemsRepository.save(any())).thenReturn(lunchItem);
        when(lunchItemMapper.lunchItemEntityToLunchItem(lunchItem)).thenReturn(buildLunchItemMock());

        assertDoesNotThrow(() -> {
            var response = adapter.saveLunchItem(request);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should find By Id")
    public void findById() {
        var lunchItem = Optional.of(buildLunchItemEntityMock());

        when(lunchItemsRepository.findById(ID)).thenReturn(lunchItem);
        when(lunchItemMapper.lunchItemEntityToLunchItem(lunchItem.get())).thenReturn(buildLunchItemMock());

        assertDoesNotThrow(() -> {
            var response = adapter.findById(ID);
            assertNotNull(response);
        });
    }



}
