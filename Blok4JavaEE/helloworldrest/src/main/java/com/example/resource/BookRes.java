package com.example.resource;

import com.example.util.JsonRes;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

public class BookRes implements JsonRes {

    @GET
    public String getBookById(@PathParam("id") int id) {
        return "Ik ben in SUBRECROUSE met id" + id;
    }
}
