package city.web;

import city.dao.PersonCheckDao;
import city.dao.PoolConnectionBuilder;
import city.domain.PersonRequest;
import city.domain.PersonResponse;
import city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.time.LocalDate;

@Path("/check")
@Singleton
public class CheckPersonService {

    private static final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);

    private PersonCheckDao dao = new PersonCheckDao();


    @PostConstruct
    public void init (){
        logger.info("SERVLET is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
       // System.out.println(request.toString());
        logger.info(request.toString());
        return dao.checkPerson(request);
       // return new PersonResponse();
    }
}
