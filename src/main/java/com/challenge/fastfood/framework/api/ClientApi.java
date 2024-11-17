package com.challenge.fastfood.framework.api;

import com.challenge.fastfood.framework.adapter.ClientAdapterImpl;
import com.challenge.fastfood.interfaceadapters.controller.ClientController;
import com.challenge.fastfood.interfaceadapters.controller.request.ClientRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.ClientResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@Tag(name = "Client", description = "Client Controller")
@RequiredArgsConstructor
public class ClientApi {

    private ClientController clientController;
    private final ClientAdapterImpl clientAdapter;

    @PostMapping
    @Operation(summary = "Create client", description = "Create a client")
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest clientRequest) {

        clientController = new ClientController(clientAdapter);
        ClientResponse client = clientController.createClient(clientRequest);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    @Operation(summary = "Get client", description = "Get client")
    public ResponseEntity<ClientResponse> getClient(@RequestParam(required = false) String cpf) {
        clientController = new ClientController(clientAdapter);
        ClientResponse clientResponse = clientController.findClient(cpf);
        return ResponseEntity.ok(clientResponse);
    }

}
