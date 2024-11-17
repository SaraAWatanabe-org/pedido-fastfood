package com.challenge.fastfood.framework.api;

import com.challenge.fastfood.interfaceadapters.controller.LunchItemsController;
import com.challenge.fastfood.interfaceadapters.controller.request.LunchItemRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.LunchItemResponse;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lunch-items")
@Tag(name = "LunchItems", description = "Lunch Items Controller")
@RequiredArgsConstructor
public class LunchItemsApi {

    private LunchItemsController lunchItemsController;
    private final LunchItemAdapterInterface lunchItemAdapter;

    @GetMapping
    @Operation(summary = "Get available lunch items", description = "Get available lunch items")
    public ResponseEntity<List<LunchItemResponse>> getLunchItems(@RequestParam(required = false) String type) {
        lunchItemsController = new  LunchItemsController(lunchItemAdapter);
        List<LunchItemResponse> lunchItemResponses = lunchItemsController.findLunchItems(type);
        return ResponseEntity.ok(lunchItemResponses);
    }

    @PostMapping
    @Operation(summary = "Create a lunch item", description = "Create a lunch item")
    public ResponseEntity<LunchItemResponse> createLunchItem(@Valid @RequestBody LunchItemRequest lunchItemRequest) {
        lunchItemsController = new  LunchItemsController(lunchItemAdapter);
        LunchItemResponse lunchItemResponse = lunchItemsController.createLunchItem(lunchItemRequest);
        return ResponseEntity.ok(lunchItemResponse);
    }

    @DeleteMapping("/{lunchId}")
    @Operation(summary = "Delete a lunch order", description = "Delete a lunch order")
    public ResponseEntity<Boolean> deleteLunch(@PathVariable Long lunchId){
        lunchItemsController = new  LunchItemsController(lunchItemAdapter);
        Boolean deleteLunchItem = lunchItemsController.editStatusLunchItem(lunchId);
        return ResponseEntity.ok(deleteLunchItem);
    }

    @PutMapping("/{lunchId}")
    @Operation(summary = "Edit a lunch order", description = "Edit a lunch order")
    public ResponseEntity<LunchItemResponse> editLunchItem(@PathVariable Long lunchId, @Valid @RequestBody LunchItemRequest lunchItemRequest) {
        lunchItemsController = new  LunchItemsController(lunchItemAdapter);
        LunchItemResponse lunchItemResponse = lunchItemsController.editLunchItem(lunchId, lunchItemRequest);
        return ResponseEntity.ok(lunchItemResponse);
    }
}
