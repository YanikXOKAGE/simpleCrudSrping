package com.example.demo.domain.services;


import com.example.demo.core.dal.models.Catalog;
import com.example.demo.core.dal.models.CatalogItemRelation;
import com.example.demo.core.dal.models.Item;
import com.example.demo.core.dal.repositories.ICatalogRepository;
import com.example.demo.core.dal.repositories.IItemRepository;
import com.example.demo.core.enums.EErrorType;
import com.example.demo.core.utils.RepositoryHelper;
import com.example.demo.domain.services.impls.ICatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class CatalogService implements ICatalogService {


    private final ICatalogRepository catalogRepository;
    private final IItemRepository itemRepository;

    public CatalogService(ICatalogRepository catalogRepository, IItemRepository itemRepository) {
        this.catalogRepository = catalogRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public EErrorType deleteCatalogById(Long catalogId) {
        Optional<Catalog> catalogToDelete = catalogRepository.findById(catalogId);

        if (catalogToDelete.isPresent()) {
            Catalog catalog = catalogToDelete.get();
            List<Item> collect = catalog.getCatalogItemRelations()
                    .stream()
                    .map(CatalogItemRelation::getItem)
                    .collect(Collectors.toList());

            itemRepository.deleteAll(collect);

            catalogRepository.delete(catalog);

            String msg = String.format("CATALOG %s WAS DELETED", catalogId);
            log.info(msg);

            return EErrorType.SUCCESS;
        } else {
            String msg = String.format("CATALOG %s NOT FOUND", catalogId);
            log.info(msg);
            return EErrorType.CATALOG_ID_NOT_FOUND;
        }

    }

    @Override
    public EErrorType addCatalog(String catalogName) {
        int code = catalogRepository.addCatalog(catalogName);

        if (RepositoryHelper.isSuccessAction(code)) {
            return EErrorType.SUCCESS;
        }
        return EErrorType.UNKNOWN_ERROR;
    }
}
