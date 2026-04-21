package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.database.DataStore;
import com.mycompany.smartcampusapi.models.Room;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

@Path("/rooms") // This handles all requests to /api/v1/rooms
@Produces(MediaType.APPLICATION_JSON) // Every response will be JSON
@Consumes(MediaType.APPLICATION_JSON) // Every request body must be JSON
public class SensorRoomResource {

    // Connect to our fake database
    private Map<String, Room> rooms = DataStore.getRooms();

    // 1. GET / - Provide a list of all rooms
    @GET
    public Response getAllRooms() {
        return Response.ok(rooms.values()).build();
    }

    // 2. POST / - Create a new room
    @POST
    public Response addRoom(Room room) {
        if (room.getId() == null || room.getId().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Room ID cannot be empty").build();
        }
        // Add the room to our map
        rooms.put(room.getId(), room);
        return Response.status(Response.Status.CREATED).entity(room).build();
    }

    // 3. GET /{roomId} - Fetch metadata for a specific room
    @GET
    @Path("/{roomId}")
    public Response getRoom(@PathParam("roomId") String roomId) {
        Room room = rooms.get(roomId);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Room not found").build();
        }
        return Response.ok(room).build();
    }

    // 4. DELETE /{roomId} - Safety logic included!
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        Room room = rooms.get(roomId);
        
        // If room doesn't exist, return 404
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Room not found").build();
        }

        // BUSINESS LOGIC CONSTRAINT: Prevent data orphans
        if (!room.getSensorIds().isEmpty()) {
            // In Part 5, you will map this to a custom exception.
            // For now, we manually return the 409 Conflict.
            return Response.status(Response.Status.CONFLICT)
                    .entity("Cannot delete: Room still has active sensors assigned to it.")
                    .build();
        }

        // If it's empty, it is safe to delete
        rooms.remove(roomId);
        return Response.noContent().build(); // 204 No Content is standard for successful DELETE
    }
}