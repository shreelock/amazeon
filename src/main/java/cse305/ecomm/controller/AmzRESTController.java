package cse305.ecomm.controller;

import cse305.ecomm.dao.AmzDBSetupDAO;
import cse305.ecomm.dao.InventoryDAO;
import cse305.ecomm.representations.Address;
import cse305.ecomm.representations.InventoryQtyResponse;

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
        AmzDBSetupDAO dao = new AmzDBSetupDAO();
        dao.initDBSchema();

        return Response.ok(OK_OP).build();
    }

    @PermitAll
    @GET
    @Path("/setup/data")
    public Response setupDummyData() throws Exception {
        AmzDBSetupDAO dao = new AmzDBSetupDAO();
        dao.initDBData();
        return Response.ok(OK_OP).build();
    }

    @PermitAll
    @GET
    @Path("/getaddr/{person_id}")
    public Response getUserAddrFromPersonId(@PathParam("person_id") Integer person_id) throws Exception {
        AmzDBSetupDAO dao = new AmzDBSetupDAO();
        Address address = dao.getAddrFromPersonId(person_id);
       return Response.ok(address).build();
    }

    @PermitAll
    @GET
    @Path("/listItems")
    public Response listItems() throws Exception {
        InventoryDAO dao = new InventoryDAO();
        List<InventoryQtyResponse> resp = dao.getActiveInventory();
        System.out.println(resp);
        return Response.ok(resp).build();
    }
}