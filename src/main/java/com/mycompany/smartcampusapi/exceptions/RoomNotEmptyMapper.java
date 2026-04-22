package com.mycompany.smartcampusapi.exceptions;

import com.mycompany.smartcampusapi.models.ErrorMessage;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyMapper implements ExceptionMapper<RoomNotEmptyException> {
    @Override
    public Response toResponse(RoomNotEmptyException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return Response.status(Response.Status.CONFLICT) // 409 Conflict
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}