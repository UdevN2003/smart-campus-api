package com.mycompany.smartcampusapi.exceptions;

import com.mycompany.smartcampusapi.models.ErrorMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableMapper implements ExceptionMapper<SensorUnavailableException> {
    @Override
    public Response toResponse(SensorUnavailableException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return Response.status(Response.Status.FORBIDDEN) // 403 Forbidden
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}