package com.example.resource;

import javax.ws.rs.GET;

public class BookRes {

    @GET
    public String test() {
        return "Ik ben in SUBRECROUSE";
    }
}
