package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.database.DataStore;
import com.mycompany.smartcampusapi.models.Sensor;
import com.mycompany.smartcampusapi.models.SensorReading;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private String sensorId;

    // CONSTRUCTOR: The parent resource will pass the sensorId into here
    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    // 1. GET / - Fetch historical data for this specific sensor
    @GET
    public Response getReadings() {
        // Find the history list. If it doesn't exist yet, return an empty list.
        List<SensorReading> history = DataStore.getReadings().getOrDefault(sensorId, new ArrayList<>());
        return Response.ok(history).build();
    }

    // 2. POST / - Append a new reading
    @POST
    public Response addReading(SensorReading reading) {
        // Check if the sensor actually exists
        Sensor sensor = DataStore.getSensors().get(sensorId);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Sensor not found").build();
        }

        // Auto-generate an ID and timestamp if the user didn't provide them
        if (reading.getId() == null) {
            reading.setId(UUID.randomUUID().toString());
        }
        if (reading.getTimestamp() == 0) {
            reading.setTimestamp(System.currentTimeMillis());
        }
        
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new com.mycompany.smartcampusapi.exceptions.SensorUnavailableException("Sensor is physically disconnected for maintenance.");
        }

        // Add the reading to the history list in the database
        List<SensorReading> history = DataStore.getReadings().computeIfAbsent(sensorId, k -> new ArrayList<>());
        history.add(reading);

        // SIDE EFFECT: Update the parent Sensor's current value!
        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED).entity(reading).build();
    }
}