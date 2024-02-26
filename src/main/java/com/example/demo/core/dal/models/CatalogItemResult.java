package com.example.demo.core.dal.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CatalogItemResult {

    @Id
    private Long catalogId;

    private String catalog_name;
    private String title;
    private String link;
    private Long date;
}
