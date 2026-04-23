package com.mycompany.smartcampusapi.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ApiLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger logger = Logger.getLogger(ApiLoggingFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.log(Level.INFO, ">>> INCOMING REQUEST: {0} {1}", new Object[]{requestContext.getMethod(), requestContext.getUriInfo().getAbsolutePath()});
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        logger.log(Level.INFO, "<<< OUTGOING RESPONSE: Status Code {0}", responseContext.getStatus());
    }
}