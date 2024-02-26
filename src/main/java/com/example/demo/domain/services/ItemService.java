package com.example.demo.domain.services;

import com.example.demo.api.models.ItemModel;
import com.example.demo.api.responses.BadResponse;
import com.example.demo.core.dal.models.Catalog;
import com.example.demo.core.dal.models.CatalogItemRelation;
import com.example.demo.core.dal.models.Item;
import com.example.demo.core.dal.repositories.ICatalogRepository;
import com.example.demo.core.dal.repositories.IItemRepository;
import com.example.demo.core.enums.EErrorType;
import com.example.demo.core.utils.RepositoryHelper;
import com.example.demo.domain.services.impls.IItemService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ItemService implements IItemService {

    private final IItemRepository itemRepository;
    private final ICatalogRepository catalogRepository;


    public ItemService(IItemRepository itemRepository, ICatalogRepository catalogRepository) {
        this.itemRepository = itemRepository;
        this.catalogRepository = catalogRepository;

    }


    @Override
    public Item getItemById(Long itemId) {

        Optional<Item> optionalItem = itemRepository.findById(itemId);

        return optionalItem.orElse(null);

    }

    @Override
    public List<Item> getItems() {

        return itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsByCatalogId(Long catalogId) {

        List<Catalog> allCatalog = catalogRepository.findAll();

        if (catalogId > allCatalog.size() || catalogId == 0) {
            String msg = String.format("Catalog with id %s not found", catalogId);
            System.out.println(msg);
            return null;
        }


        Catalog catalog = allCatalog
                .stream()
                .filter(catalog1 -> Objects.equals(catalog1.getCatalogId(), catalogId))
                .findFirst()
                .get();


        return catalog.getCatalogItemRelations()
                .stream()
                .map(CatalogItemRelation::getItem)
                .collect(Collectors.toList());
    }


    @Override
    public EErrorType updateItemById(Long itemId, String newTitle, String newLink) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setTitle(newTitle);
            item.setLink(newLink);
            item.setDate(DateTime.now().getMillis());
            itemRepository.save(item);

            System.out.println("Item with itemId " + itemId + " updated successfully.");
            return EErrorType.SUCCESS;

        } else {
            System.out.println("Item with itemId " + itemId + " not found.");
            return EErrorType.ITEM_ID_NOT_FOUND;
        }
    }

    @Override
    public EErrorType deleteItem(Long itemId) {
        Item itemToDelete = itemRepository.findById(itemId).orElse(null);

        if (itemToDelete != null) {
            itemToDelete.getCatalogItemRelations().clear();
            itemRepository.deleteByItemId(itemId);
            System.out.printf("Item № %s was deleted", itemId);
            return EErrorType.SUCCESS;

        } else {
            System.out.printf("Item № %s not found", itemId);
            return EErrorType.ITEM_ID_NOT_FOUND;
        }
    }

    @Override
    @Transactional
    public EErrorType addItemToCatalog(Long catalogId, ItemModel itemModel) {
        Optional<Catalog> catalogOptional = catalogRepository.findById(catalogId);
        if (catalogOptional.isPresent()) {
            Item newItem = new Item();
            newItem.setTitle(itemModel.title);
            newItem.setLink(itemModel.link);
            newItem.setDate(itemModel.date);

            Item savedItem = itemRepository.save(newItem);
            Long itemId = savedItem.getItem_Id();
            System.out.println(itemId);

            int code = catalogRepository.addReletion(itemId, catalogId);
            if (RepositoryHelper.isSuccessAction(code)) {
                return EErrorType.SUCCESS;
            }
            return EErrorType.UNKNOWN_ERROR;
        }
        return EErrorType.CATALOG_ID_NOT_FOUND;

    }
}