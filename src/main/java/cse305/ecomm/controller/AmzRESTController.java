package cse305.ecomm.controller;


import cse305.ecomm.dao.*;
import cse305.ecomm.representations.*;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.text.ParseException;
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
    @Path("/person_name/{name}")
    public Response getPersonFromName(@PathParam("name") String person_name) throws Exception {
        PersonDao personDao = new PersonDao();
        List<Person> person = personDao.getPersonInfoByName(person_name);
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
        ShoppingCartDAO dao = new ShoppingCartDAO();
        List<ShoppingCartResponse> resp = dao.getCustomerCartByPersonId(person_id);
        System.out.println(resp);
        return Response.ok(resp).build();
    }

    @PermitAll
    @GET
    @Path("/itemReview/{item_id}")
    public Response listReviews(@PathParam("item_id") Integer item_id) throws Exception {

        ItemDAO dao = new ItemDAO();
        List<ItemReviewResponse> resp = dao.getItemReviewByID(item_id);
        System.out.println(resp);
        return Response.ok(resp).build();
    }

    @PermitAll
    @GET
    @Path("/customerOrders/{customer_id}")
    public Response ordersByCustomer(@PathParam("customer_id") Integer customer_id) throws Exception {
        OrderDAO dao = new OrderDAO();
        List<Order> orders = dao.getOrderInfoByCustId(customer_id);
        System.out.println(orders);
        return Response.ok(orders).build();
    }

    @PermitAll
    @GET
    @Path("/itemsOrderedByCustomer/{customer_id}_{item_id}")
    public Response itemsOrderedByCustomer(@PathParam("customer_id") Integer customer_id, @PathParam("item_id") Integer item_id) throws Exception {
        OrderDAO dao = new OrderDAO();
        List<Order> orders = dao.getOrderInfoByCustItemId(customer_id, item_id);
        System.out.println(orders);
        return Response.ok(orders).build();
    }
    @PermitAll
    @POST
    @Path("/addToCart")
    public Response addToCart(ShoppingCart cart) throws SQLException, ClassNotFoundException {
        ShoppingCartDAO dao = new ShoppingCartDAO();
        boolean out = dao.addToCart(cart);
        return Response.ok(out).build();
    }

    @PermitAll
    @POST
    @Path("/placeOrder1")
    public Response placeOrder(OrderPageEntity orderPageEntity) throws SQLException, ClassNotFoundException, ParseException {
        ShoppingCartDAO dao = new ShoppingCartDAO();
        boolean out = dao.pushOrderSwitch(orderPageEntity);
        return Response.ok(out).build();
    }


}
