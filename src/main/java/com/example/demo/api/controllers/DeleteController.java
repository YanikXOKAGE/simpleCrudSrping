package com.example.demo.api.controllers;


import com.example.demo.api.responses.BadResponse;
import com.example.demo.api.responses.Response;
import com.example.demo.core.enums.EErrorType;
import com.example.demo.domain.services.impls.ICatalogService;
import com.example.demo.domain.services.impls.IItemService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {

    private final IItemService itemService;

    private final ICatalogService catalogService;

    public DeleteController(IItemService itemService, ICatalogService catalogService) {
        this.itemService = itemService;
        this.catalogService = catalogService;
    }

    @Transactional
    @RequestMapping("/deleteItem")
    public Response deleteItem(
            @RequestParam Long itemId) {
        EErrorType eErrorType = itemService.deleteItem(itemId);
        if (eErrorType == EErrorType.SUCCESS) {
            return new Response("Item was deleted");
        }
        return new BadResponse("Item not found");
    }


    @RequestMapping("/deleteCatalog")
    public Response deleteCatalog(
            @RequestParam Long catalogId) {
        EErrorType eErrorType = catalogService.deleteCatalogById(catalogId);
        if (eErrorType == EErrorType.SUCCESS) {
            return new Response("Catalog was deleted");
        }
        return new BadResponse("Catalog not found");
    }

}
