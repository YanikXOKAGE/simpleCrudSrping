package com.example.demo.api.responses;

import lombok.Getter;

@Getter
public class Response {

    private final String msg;

    public Response(String msg) {
        this.msg = msg;
    }


}

