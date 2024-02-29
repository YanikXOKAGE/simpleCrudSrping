package com.example.demo.api.controllers;


import com.example.demo.api.models.ItemModel;
import com.example.demo.api.responses.*;
import com.example.demo.core.dal.models.Item;
import com.example.demo.core.dal.repositories.IItemRepository;
import com.example.demo.domain.services.impls.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
public class ReadController {
    private final IItemService itemService;
    private final IItemRepository itemRepository;


    public ReadController(IItemService itemService, IItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @RequestMapping("/getItemById")
    public Response getItemById(
            @RequestParam Long itemId) {

        Item itemById = itemService.getItemById(itemId);

        if (itemById == null) {
            String msg = String.format("Item with Id %s not found", itemId);
            log.info(msg);
            return new BadResponse(msg);
        }

        GetIemByIdResponse getIemByIdResponse = new GetIemByIdResponse(itemById.getItem_Id(), itemById.getTitle(), itemById.getLink(), itemById.getDate());
        String msg = String.format("We sent response %s", getIemByIdResponse);
        log.info(msg);

        return getIemByIdResponse;
    }


    @RequestMapping("/getAllItems")
    public Response getAllItems() {
        List<Item> items = itemService.getItems();

        List<ItemModel> itemModelList = itemService.createItemModelList(items);

        GetAllItemsResponse getAllItemsResponse = new GetAllItemsResponse(itemModelList);

        String msg = String.format("We sent response %s", getAllItemsResponse);
        log.info(msg);

        return getAllItemsResponse;
    }


    @RequestMapping("/getItemsByCatalogId")
    public Response getItemsByCatalog(Long catalogId) {

        List<Item> items = itemService.getItemsByCatalogId(catalogId);

        if (items == null || items.isEmpty()) {
            return new BadResponse("Items not found");
        }

        List<ItemModel> itemModelList = itemService.createItemModelList(items);

        return new GetItemsByCatalogResponse(itemModelList);
    }

    @RequestMapping("getItemByTitle")
    public Response getItemByTitle(
            @RequestParam String title) {
        List<Item> byTitleContainingIgnoreCase = itemRepository.findByTitleContainingIgnoreCase(title);
        List<ItemModel> itemModelList = itemService.createItemModelList(byTitleContainingIgnoreCase);

        return new GeItemByTittleResponse(itemModelList);
    }

}
