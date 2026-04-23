package com.mycompany.smartcampusapi;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/v1")
public class SmartCampusApplication extends ResourceConfig {
    public SmartCampusApplication() {
        // This tells Tomcat to scan your entire project for endpoints and error mappers
        packages("com.mycompany.smartcampusapi");
    }
}