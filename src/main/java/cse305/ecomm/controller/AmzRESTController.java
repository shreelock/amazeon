package cse305.ecomm.controller;


import cse305.ecomm.dao.AmzDBSetupDAO;
import cse305.ecomm.dao.InventoryDAO;
import cse305.ecomm.dao.PersonDao;
import cse305.ecomm.dao.ShoppingCartDAO;
import cse305.ecomm.representations.*;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
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
    @Path("/person/{person_id}")
    public Response getPersonFromPersonId(@PathParam("person_id") Integer person_id) throws Exception {
        PersonDao personDao = new PersonDao();
        Person person = personDao.getPersonById(person_id);
        return Response.ok(person).build();
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

    @PermitAll
    @GET
    @Path("/getCart/{person_id}")
    public Response getCartByPersonId(@PathParam("person_id") Integer person_id) throws Exception {
        ShoppingCartDAO dao = new ShoppingCartDAO();
        List<ShoppingCartResponse> resp = dao.getCustomerCartByPersonId(person_id);
        System.out.println(resp);
        return Response.ok(resp).build();
    }

    @PermitAll
    @POST
    @Path("/addToCart")
    public Response addToCart(ShoppingCart cart) throws SQLException, ClassNotFoundException {
        ShoppingCartDAO dao = new ShoppingCartDAO();
        boolean out = dao.addToCart(cart);
        return Response.ok(out).build();
    }


}