package com.example.demo.api.models;

import lombok.Data;

@Data
public class ItemModel {


    public Long item_id;
    public String title;
    public String link;
    public Long date;

    public ItemModel(Long item_id, String title, String link, Long date) {
        this.item_id = item_id;
        this.title = title;
        this.link = link;
        this.date = date;
    }

    public ItemModel(String title, String link, Long date) {
        this.title = title;
        this.link = link;
        this.date = date;
    }
}
