package com.example.demo.core.dal.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "catalogitemrelation")
@Data
public class CatalogItemRelation implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


}
