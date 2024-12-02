package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.framework.mapstruct.ClientMapper;
import com.challenge.fastfood.framework.persistence.client.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.challenge.fastfood.utils.TestUtils.buildClientEntityMock;
import static com.challenge.fastfood.utils.TestUtils.buildClientMock;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientAdapterImplTest {

    @InjectMocks
    ClientAdapterImpl adapter;
    @Mock
    ClientRepository clientRepository;
    @Mock
    ClientMapper clientMapper;

    private final Long ID = 23L;
    private final String CPF = "12345678900";

    @Test
    @DisplayName("Should find Client By Id")
    public void findClientById() {
        var repositoryResponse = Optional.of(buildClientEntityMock());

        when(clientRepository.findById(ID)).thenReturn(repositoryResponse);
        when(clientMapper.clientEntityToClient(repositoryResponse.get())).thenReturn(buildClientMock());

        assertDoesNotThrow(() -> {
            var response = adapter.findClientById(ID);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should find Client")
    public void findClient() {
        var repositoryResponse = buildClientEntityMock();

        when(clientRepository.findByCpf(CPF)).thenReturn(repositoryResponse);
        when(clientMapper.clientEntityToClient(repositoryResponse)).thenReturn(buildClientMock());

        assertDoesNotThrow(() -> {
            var response = adapter.findClient(CPF);
            assertNotNull(response);
        });
    }

    @Test
    @DisplayName("Should save Client")
    public void saveClient() {
        var repositoryResponse = buildClientEntityMock();

        when(clientRepository.save(any())).thenReturn(repositoryResponse);
        when(clientMapper.clientEntityToClient(repositoryResponse)).thenReturn(buildClientMock());
        when(clientMapper.clientToClientEntity(any())).thenReturn(buildClientEntityMock());

        assertDoesNotThrow(() -> {
            var response = adapter.saveClient(buildClientMock());
            assertNotNull(response);
        });
    }


}
