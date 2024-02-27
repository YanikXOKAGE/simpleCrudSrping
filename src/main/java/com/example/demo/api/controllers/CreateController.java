package com.example.demo.api.controllers;


import com.example.demo.api.models.ItemModel;
import com.example.demo.api.responses.BadResponse;
import com.example.demo.api.responses.Response;
import com.example.demo.core.enums.EErrorType;
import com.example.demo.domain.services.impls.ICatalogService;
import com.example.demo.domain.services.impls.IItemService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CreateController {

    private final ICatalogService catalogService;
    private final IItemService itemService;


    public CreateController(ICatalogService catalogService, IItemService itemService) {
        this.catalogService = catalogService;
        this.itemService = itemService;
    }


    @RequestMapping("/addItemByCatalogId")
    Response addItem(
            @RequestParam Long catalogId,
            @RequestParam String title,
            @RequestParam String link) {

        long timeInMillis = DateTime.now(DateTimeZone.UTC).getMillis();
        ItemModel itemModel = new ItemModel(title, link, timeInMillis);
        EErrorType eErrorType = itemService.addItemToCatalog(catalogId, itemModel);
        if (eErrorType == EErrorType.SUCCESS) {
            return new Response("Item was added");
        }
        return new BadResponse("Item was not added");
    }


    @RequestMapping("addCatalog")
    Response addCatalog(
            @RequestParam String catalogName) {
        EErrorType eErrorType = catalogService.addCatalog(catalogName);
        if (eErrorType == EErrorType.SUCCESS) {
            return new Response("catalog was added");
        }
        return new BadResponse("Catalog was not added");
    }

}
