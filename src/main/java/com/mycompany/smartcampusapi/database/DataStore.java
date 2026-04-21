package com.mycompany.smartcampusapi.database;

import com.mycompany.smartcampusapi.models.Room;
import com.mycompany.smartcampusapi.models.Sensor; // <-- NEW: Import the Sensor model
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    // We use ConcurrentHashMap because it is thread-safe!
    // This prevents errors if two users try to add a room at the exact same millisecond.
    private static Map<String, Room> rooms = new ConcurrentHashMap<>();
    
    // <-- NEW: This map stores all of our sensors
    private static Map<String, Sensor> sensors = new ConcurrentHashMap<>();

    public static Map<String, Room> getRooms() {
        return rooms;
    }

    // <-- NEW: This allows our API endpoints to get the list of sensors
    public static Map<String, Sensor> getSensors() {
        return sensors;
    }
}