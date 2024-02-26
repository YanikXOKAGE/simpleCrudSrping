package com.example.demo.domain.services.impls;


import com.example.demo.core.enums.EErrorType;

public interface ICatalogService {

    EErrorType deleteCatalogById(Long catalogId);

    EErrorType addCatalog(String CatalogName);
}
