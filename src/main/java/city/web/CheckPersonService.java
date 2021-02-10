package city.web;

import city.domain.PersonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/check")
@Produces(MediaType.APPLICATION_JSON)
public class CheckPersonService {
    @GET
    @Path("/{id}")
    public PersonResponse checkPerson(@PathParam ("id")int simpleId,
                                      @QueryParam("name") String simpleName){
        return new PersonResponse();
    }
}
