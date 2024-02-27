package com.example.demo.api.responses;

import com.example.demo.api.models.ItemModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GeItemByTittleResponse extends Response {

    List<ItemModel> items;

    public GeItemByTittleResponse(List<ItemModel> items) {
        super("ALL GOOD");
        this.items = items;
    }
}
