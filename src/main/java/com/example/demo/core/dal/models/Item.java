package com.example.demo.core.dal.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = "catalogItemRelations")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_Id;

    private String title;
    private String link;
    private Long date;

    @OneToMany(mappedBy = "item")
    private List<CatalogItemRelation> catalogItemRelations;


}
