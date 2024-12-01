package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.framework.mapstruct.LunchItemMapper;
import com.challenge.fastfood.framework.mapstruct.LunchMapper;
import com.challenge.fastfood.framework.persistence.client.ClientRepository;
import com.challenge.fastfood.framework.persistence.lunch.LunchRepository;
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
public class LunchAdapterImplTest {

    @InjectMocks
    LunchAdapterImpl adapter;
    @Mock
    LunchRepository lunchRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    LunchItemMapper lunchItemMapper;
    @Mock
    LunchMapper lunchMapper;
    @Mock
    ObjectMapper objectMapper;

    private final Long ID = 23L;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(adapter, "productionServiceUrl", "http://mocked-url.com");
    }

    @Test
    @DisplayName("Should find lunch list")
    public void findLunchs() {
        var responseList = List.of(buildLunchEntityMock());

        when(lunchRepository.findAll()).thenReturn(responseList);
        when(lunchMapper.lunchsEntityToLunchs(any())).thenReturn(List.of(buildLunchMock()));

        assertDoesNotThrow(() -> {
            var response = adapter.findLunchs();
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should find lunch by id")
    public void findLunchById() {
        var responseEntity = Optional.of(buildLunchEntityMock());

        when(lunchRepository.findById(ID)).thenReturn(responseEntity);
        when(lunchMapper.lunchEntityToLunch(any())).thenReturn(buildLunchMock());

        assertDoesNotThrow(() -> {
            var response = adapter.findLunchById(ID);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should edit Lunch")
    public void editLunch() {
        var responseEntity = buildLunchEntityMock();

        when(lunchMapper.lunchToLunchEntity(any())).thenReturn(buildLunchEntityMock());
        when(lunchRepository.save(any())).thenReturn(responseEntity);
        when(lunchMapper.lunchEntityToLunch(any())).thenReturn(buildLunchMock());

        assertDoesNotThrow(() -> {
            var response = adapter.editLunch(buildLunchMock());
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should save Lunch")
    public void saveLunch() {
        var responseEntity = buildLunchEntityMock();
        var lunchItem = buildLunchItemEntityMock();
        var clientEntity = Optional.of(buildClientEntityMock());

        when(lunchItemMapper.lunchItemToLunchItemEntity(any())).thenReturn(lunchItem);
        when(lunchRepository.save(any())).thenReturn(responseEntity);
        when(lunchMapper.lunchEntityToLunch(any())).thenReturn(buildLunchMock());
        when(clientRepository.findById(any())).thenReturn(clientEntity);

        assertDoesNotThrow(() -> {
            var response = adapter.saveLunch(buildLunchMock());
            assertNotNull(response);
        });
    }

}
