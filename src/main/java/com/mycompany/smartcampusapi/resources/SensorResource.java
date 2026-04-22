package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.database.DataStore;
import com.mycompany.smartcampusapi.models.Room;
import com.mycompany.smartcampusapi.models.Sensor;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    // Access both tables in our fake database
    private Map<String, Sensor> sensors = DataStore.getSensors();
    private Map<String, Room> rooms = DataStore.getRooms();

    // 1. POST / - Register a new sensor with INTEGRITY CHECK
    @POST
    public Response addSensor(Sensor sensor) {
        
        // INTEGRITY CHECK: Does the roomId provided actually exist in our rooms database?
        if (!rooms.containsKey(sensor.getRoomId())) {
            // If the room doesn't exist, block the creation.
            // Note: In Part 5, we will change this to throw a custom Exception (HTTP 422).
            // For now, returning a basic 400 Bad Request works perfectly.
            throw new com.mycompany.smartcampusapi.exceptions.LinkedResourceNotFoundException("Validation Error: The specified roomId does not exist.");
        }

        // If the room exists, save the sensor
        sensors.put(sensor.getId(), sensor);

        // CRITICAL STEP: Link the sensor to the room so our Part 2 Delete safety logic works!
        Room existingRoom = rooms.get(sensor.getRoomId());
        existingRoom.getSensorIds().add(sensor.getId());

        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }

    // 2. GET / - Retrieve sensors with Optional Filtering
    @GET
    public Response getSensors(@QueryParam("type") String type) {
        
        // If the user didn't provide a '?type=' parameter, return all sensors
        if (type == null || type.trim().isEmpty()) {
            return Response.ok(sensors.values()).build();
        }

        // If a type WAS provided, create a filtered list
        List<Sensor> filteredSensors = new ArrayList<>();
        
        // Loop through all sensors and check their type
        for (Sensor s : sensors.values()) {
            if (s.getType().equalsIgnoreCase(type)) {
                filteredSensors.add(s);
            }
        }

        return Response.ok(filteredSensors).build();
    }
    
    // 3. SUB-RESOURCE LOCATOR
    // Notice there is NO HTTP method annotation (@GET, @POST) here!
    // It just hands traffic off to the SensorReadingResource class.
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }

}
