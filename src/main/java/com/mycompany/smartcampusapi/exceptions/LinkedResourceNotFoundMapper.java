package com.mycompany.smartcampusapi.exceptions;

import com.mycompany.smartcampusapi.models.ErrorMessage;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LinkedResourceNotFoundMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    @Override
    public Response toResponse(LinkedResourceNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return Response.status(422) // 422 Unprocessable Entity
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}