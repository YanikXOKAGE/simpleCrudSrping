package com.example.demo.domain.services.impls;

import com.example.demo.api.models.ItemModel;
import com.example.demo.core.dal.models.Item;
import com.example.demo.core.enums.EErrorType;

import java.util.List;

public interface IItemService {


    Item getItemById(Long itemId);

    List<Item> getItems();

    List<Item> getItemsByCatalogId(Long catalogId);

    EErrorType updateItemById(Long itemId, String newTitle, String newLink);

    EErrorType deleteItem(Long itemId);

    EErrorType  addItemToCatalog(Long catalogId, ItemModel itemModel);

    List<ItemModel> createItemModelList(List<Item> items);

}
