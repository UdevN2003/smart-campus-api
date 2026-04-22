package com.mycompany.smartcampusapi.exceptions;

import com.mycompany.smartcampusapi.models.ErrorMessage;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex) {
        // We hide the real Java error from the user to prevent hacking!
        ErrorMessage errorMessage = new ErrorMessage("An unexpected internal server error occurred.");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}