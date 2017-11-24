package cse305.ecomm.controller;

import cse305.ecomm.dao.AmzDB;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/setup")
@Produces(MediaType.APPLICATION_JSON)
public class AmzRESTController {

    private final Validator validator;

    public AmzRESTController(Validator validator) {
        this.validator = validator;
    }

    @PermitAll
    @GET
    @Path("/db")
    public Response setupDatabaseSchema() throws Exception {
        AmzDB dao = new AmzDB();
        dao.initDBSchema();
        List<String> OK_OP = new ArrayList<>(); OK_OP.add("Done!");
        return Response.ok(OK_OP).build();
    }

    @PermitAll
    @GET
    @Path("/data")
    public Response setupDummyData() throws Exception{
        AmzDB dao = new AmzDB();
        dao.initDBData();
        List<String> OK_OP = new ArrayList<>(); OK_OP.add("Done!");
        return Response.ok(OK_OP).build();
    }
}