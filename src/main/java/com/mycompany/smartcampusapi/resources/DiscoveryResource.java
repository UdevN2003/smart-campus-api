package com.mycompany.smartcampusapi.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

// This sets the URL path for this specific file
@Path("/")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApiMetadata() {
        
        Map<String, Object> discoveryData = new HashMap<>();
        discoveryData.put("version", "1.0");
        discoveryData.put("admin_contact", "admin@smartcampus.edu");
        discoveryData.put("description", "Smart Campus API");

        Map<String, String> collections = new HashMap<>();
        collections.put("rooms", "/api/v1/rooms");
        collections.put("sensors", "/api/v1/sensors");

        discoveryData.put("collections", collections);

        // Sends the data back as a successful 200 response
        return Response.ok(discoveryData).build();
    }
}