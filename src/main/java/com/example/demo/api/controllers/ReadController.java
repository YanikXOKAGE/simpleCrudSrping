package com.example.demo.api.controllers;


import com.example.demo.api.models.ItemModel;
import com.example.demo.api.responses.*;
import com.example.demo.core.dal.models.Catalog;
import com.example.demo.core.dal.models.CatalogItemRelation;
import com.example.demo.core.dal.models.Item;
import com.example.demo.core.dal.repositories.ICatalogRepository;
import com.example.demo.core.dal.repositories.IItemRepository;
import com.example.demo.domain.services.impls.IItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ReadController {
    private final ICatalogRepository catalogRepository;
    private final IItemService itemService;


    public ReadController(ICatalogRepository catalogRepository, IItemService itemService) {
        this.catalogRepository = catalogRepository;
        this.itemService = itemService;
    }

    @RequestMapping("/getItemById")
    public Response getItemById(
            @RequestParam Long itemId) {

        Item itemById = itemService.getItemById(itemId);

        if (itemById == null) {
            String msg = String.format("Item with Id %s not found", itemId);
            System.out.printf(msg);
            return new BadResponse(msg);
        }

        GetIemByIdResponse getIemByIdResponse = new GetIemByIdResponse(itemById.getItem_Id(), itemById.getTitle(), itemById.getLink(), itemById.getDate());
        System.out.printf("We sent response %s", getIemByIdResponse);

        return getIemByIdResponse;
    }


    @RequestMapping("/getAllItems")
    public Response getAllItems() {
        List<Item> items = itemService.getItems();

        List<ItemModel> collect = items.stream().map(item -> new ItemModel(
                item.getItem_Id(),
                item.getTitle(),
                item.getLink(),
                item.getDate()
        )).collect(Collectors.toList());

        GetAllItemsResponse getAllItemsResponse = new GetAllItemsResponse(collect);
        System.out.printf("We sent response %s", getAllItemsResponse);

        return getAllItemsResponse;
    }


    @RequestMapping("/getItemsByCatalogId")
    public Response getItemsByCatalog(Long catalogId) {

        List<Item> items = itemService.getItemsByCatalogId(catalogId);

        if (items == null || items.isEmpty()) {
            return new BadResponse("Items not found");
        }


        List<ItemModel> collectedItems = items.stream().map(item -> new ItemModel(
                item.getItem_Id(),
                item.getTitle(),
                item.getLink(),
                item.getDate()
        )).collect(Collectors.toList());

        return new GetItemsByCatalogResponse(collectedItems);
    }

}
