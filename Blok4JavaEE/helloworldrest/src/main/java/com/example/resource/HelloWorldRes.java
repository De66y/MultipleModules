package com.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("helloworld") //Dit komt na de url en het 'tussenpad' uit de AppMain -> je ENDpoint
public class HelloWorldRes {

    @GET
    public Response get() {
        return Response.ok()
                .type(MediaType.TEXT_PLAIN_TYPE)
                .entity("Hello Web World!") //Hier kan je ook een object in teruggeven
                .build();
    }

}
