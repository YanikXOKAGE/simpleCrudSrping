package com.example.demo.core.dal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude ="catalogItemRelations")
@Table(name = "catalogs")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogId;

    private String catalogName;


    @OneToMany(mappedBy = "catalog",  fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CatalogItemRelation> catalogItemRelations;




}
