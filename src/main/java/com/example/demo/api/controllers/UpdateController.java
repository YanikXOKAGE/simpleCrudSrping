package com.example.demo.api.controllers;


import com.example.demo.api.responses.BadResponse;
import com.example.demo.api.responses.Response;
import com.example.demo.core.enums.EErrorType;
import com.example.demo.domain.services.impls.IItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {
    private final IItemService itemService;

    public UpdateController(IItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/updateItem")
    public Response updateCatalog(
            @RequestParam Long itemId,
            @RequestParam String title,
            @RequestParam String link
    ) {
        EErrorType error = itemService.updateItemById(itemId, title, link);
        if(error == EErrorType.SUCCESS) {
            return new Response("Item was updated successfully");
        }

        return new BadResponse("Item not found");

    }
}
