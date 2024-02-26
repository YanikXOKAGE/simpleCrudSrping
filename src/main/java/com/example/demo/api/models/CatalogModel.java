package com.example.demo.api.models;

import lombok.Data;

import java.util.List;

@Data
public class CatalogModel {
    private String nameCatalog;
    private List<ItemModel> items;



}
