package cse305.ecomm.controller;


import cse305.ecomm.dao.AmzDBSetupDAO;
import cse305.ecomm.dao.InventoryDAO;
import cse305.ecomm.dao.ItemDAO;
import cse305.ecomm.representations.Item;
import cse305.ecomm.representations.ItemReviewFrontEnd;
import cse305.ecomm.dao.PersonDao;
import cse305.ecomm.dao.ShoppingCartDao;
import cse305.ecomm.representations.Address;
import cse305.ecomm.representations.InventoryQtyResponse;
import cse305.ecomm.representations.Person;
import cse305.ecomm.representations.ShoppingCartResponse;

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
    @Path("/itemInfo/{item_id}")
    public Response getItemInfo(@PathParam("item_id") Integer item_id) throws Exception {

        ItemDAO dao = new ItemDAO();
        Item resp = dao.getItemInfoByID(item_id);
	System.out.println(resp);
	return Response.ok(resp).build();
    }

    @PermitAll
    @GET
    @Path("/getCart/{person_id}")
    public Response getCartByPersonId(@PathParam("person_id") Integer person_id) throws Exception {
        ShoppingCartDao dao = new ShoppingCartDao();
        List<ShoppingCartResponse> resp = dao.getCustomerCartByPersonId(person_id);
        System.out.println(resp);
        return Response.ok(resp).build();
    }

    @PermitAll
    @GET
    @Path("/itemReview/{item_id}")
    public Response listReviews(@PathParam("item_id") Integer item_id) throws Exception {

        ItemDAO dao = new ItemDAO();
        List<ItemReviewFrontEnd> resp = dao.getItemReviewByID(item_id);
        System.out.println(resp);
        return Response.ok(resp).build();
    }
}
