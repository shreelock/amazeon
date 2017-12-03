package cse305.ecomm.dao;

import cse305.ecomm.representations.ShoppingCart;
import cse305.ecomm.representations.ShoppingCartResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAO {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public List<ShoppingCartResponse> getCustomerCartByPersonId(int personId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        String query = "SELECT shopping_cart.customer_id, shopping_cart.item_id, shopping_cart.seller_id, shopping_cart.quantity, item.item_name FROM amazeon.shopping_cart JOIN amazeon.item WHERE shopping_cart.customer_id = " + personId + "AND shopping_cart.item_id = item.item_id;";
        ResultSet res = statement.executeQuery(query);
        List<ShoppingCartResponse> ShoppingCartRespList = new ArrayList<>();
        if(res.next()) {
            System.out.println(res);
            ShoppingCartRespList.add(new ShoppingCartResponse(res.getInt(1), res.getInt(2),res.getInt(3),res.getInt(4), res.getString(5)));
        }
        return ShoppingCartRespList;
    }


    public boolean addToCart (ShoppingCart cart) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();

        String query = "INSERT INTO amazeon.shopping_cart (`customer_id`,`item_id`,`seller_id`,`quantity`) VALUES( " + cart.getCustomerId() + " , " + cart.getItemId() +" , " + cart.getSellerId() +" , " + cart.getQuantity()+ " );";
        int out = 0;
        try {
            out = statement.executeUpdate(query);
        } catch (Exception e) {
            //pass
        }
        return out > 0;
    }




}
