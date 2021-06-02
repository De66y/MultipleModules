package com.example.resource;

import javax.ws.rs.GET;

public class BookRes {

    @GET
    public String getBookById() {
        return "Ik ben in SUBRECROUSE";
    }
}
