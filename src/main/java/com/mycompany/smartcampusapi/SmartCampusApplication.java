package com.mycompany.smartcampusapi;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// This fulfills the requirement to set the versioned entry point
@ApplicationPath("/api/v1")
public class SmartCampusApplication extends Application {
    // Leave this empty
}