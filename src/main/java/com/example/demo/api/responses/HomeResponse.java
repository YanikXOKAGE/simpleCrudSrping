package com.example.demo.api.responses;


import com.example.demo.api.models.CatalogModel;
import lombok.Data;

import java.util.List;

@Data
public class HomeResponse {

    private List<CatalogModel> catalogs;

    public HomeResponse(List<CatalogModel> catalogs) {
        this.catalogs = catalogs;
    }
}
