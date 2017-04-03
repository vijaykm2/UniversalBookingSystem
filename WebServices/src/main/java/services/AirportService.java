package services;

import xmlRootElements.Airports;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by vijay on 3/31/17.
 */

@Path("/airportService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirportService {

    @Path("/addAirports")
    public void addAirports(Airports airports){

    }
}
