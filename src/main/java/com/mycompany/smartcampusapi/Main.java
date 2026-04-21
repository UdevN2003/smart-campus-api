package com.mycompany.smartcampusapi;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

public class Main {
    // We include the /api/v1 path here so the server hosts it correctly
    public static final String BASE_URI = "http://localhost:8080/api/v1/";

    public static void main(String[] args) {
        // This tells Jersey to scan your packages for the classes we just made
        final ResourceConfig rc = new ResourceConfig().packages("com.mycompany.smartcampusapi");

        // Starts the server
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        
        System.out.println("✅ Server started successfully!");
        System.out.println("✅ Test your endpoint by opening this link in your browser: " + BASE_URI);
        System.out.println("🛑 To stop the server, press the red stop button in NetBeans.");
    }
}