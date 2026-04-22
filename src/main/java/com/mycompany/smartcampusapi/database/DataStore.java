package com.mycompany.smartcampusapi.database;

import com.mycompany.smartcampusapi.models.Room;
import com.mycompany.smartcampusapi.models.Sensor;
import com.mycompany.smartcampusapi.models.SensorReading; // <-- NEW
import java.util.List; // <-- NEW
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    private static Map<String, Room> rooms = new ConcurrentHashMap<>();
    private static Map<String, Sensor> sensors = new ConcurrentHashMap<>();
    
    // NEW: This links a Sensor's ID to a whole List of historical readings
    private static Map<String, List<SensorReading>> readings = new ConcurrentHashMap<>();

    public static Map<String, Room> getRooms() { return rooms; }
    public static Map<String, Sensor> getSensors() { return sensors; }
    
    // NEW Getter
    public static Map<String, List<SensorReading>> getReadings() { return readings; }
}