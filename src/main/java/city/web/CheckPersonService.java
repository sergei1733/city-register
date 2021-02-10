package city.web;

import city.domain.PersonRequest;
import city.domain.PersonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.time.LocalDate;

@Path("/check")
public class CheckPersonService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request){
        System.out.println(request.toString());
        return new PersonResponse();
    }
}
