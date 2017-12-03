package cse305.ecomm.controller;

import cse305.ecomm.dao.AmzDB;
import cse305.ecomm.dao.PersonDao;
import cse305.ecomm.representations.Address;
import cse305.ecomm.representations.Person;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AmzRESTController {

    private final Validator validator;
    List<String> OK_OP = new ArrayList<>(Collections.singletonList("Done!"));
    public AmzRESTController(Validator validator) {
        this.validator = validator;
    }

    @PermitAll
    @GET
    @Path("/setup/db")
    public Response setupDatabaseSchema() throws Exception {
        AmzDB dao = new AmzDB();
        dao.initDBSchema();

        return Response.ok(OK_OP).build();
    }

    @PermitAll
    @GET
    @Path("/setup/data")
    public Response setupDummyData() throws Exception {
        AmzDB dao = new AmzDB();
        dao.initDBData();
        return Response.ok(OK_OP).build();
    }

    @PermitAll
    @GET
    @Path("/getaddr/{person_id}")
    public Response getUserAddrFromPersonId(@PathParam("person_id") Integer person_id) throws Exception {
        AmzDB dao = new AmzDB();
        Address address = dao.getAddrFromPersonId(person_id);
       return Response.ok(address).build();
    }
    @PermitAll
    @GET
    @Path("/person/{person_id}")
    public Response getPersonFromPersonId(@PathParam("person_id") Integer person_id) throws Exception {
        PersonDao personDao = new PersonDao();
        Person person = personDao.getPersonById(person_id);
        return Response.ok(person).build();
    }

}