package com.pluralsight.bookstore.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * The Jaxrs configuration class. This class is to define that everything under 'api' path is a JAX end point.
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application{
}
