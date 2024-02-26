package com.example.demo.api.responses;

import lombok.*;



@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class GetIemByIdResponse extends Response {

    private Long itemId;
    private String title;
    private String link;
    private Long date;


    public GetIemByIdResponse(Long itemId, String title, String link, Long date) {
        super("ALL GOOD");
        this.itemId = itemId;
        this.title = title;
        this.link = link;
        this.date = date;
    }

}
