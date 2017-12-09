package cse305.ecomm.dao;

import cse305.ecomm.representations.OrderPageEntity;
import cse305.ecomm.representations.ShoppingCart;
import cse305.ecomm.representations.ShoppingCartResponse;

import java.sql.*;
import java.text.ParseException;
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
        String query = "SELECT shopping_cart.customer_id, shopping_cart.item_id, shopping_cart.seller_id," +
                " shopping_cart.quantity, item.item_name FROM amazeon.shopping_cart JOIN amazeon.item" +
                " WHERE shopping_cart.customer_id = " + personId + " AND " +
                "shopping_cart.item_id = item.item_id;";
        ResultSet res = statement.executeQuery(query);
        List<ShoppingCartResponse> ShoppingCartRespList = new ArrayList<>();
        while(res.next()) {
            System.out.println(res);
            ShoppingCartRespList.add(new ShoppingCartResponse(res.getInt(1), res.getInt(2),res.getInt(3),res.getInt(4), res.getString(5)));
        }
        return ShoppingCartRespList;
    }


    public boolean addToCart (ShoppingCart cart) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();

        String query = "INSERT INTO amazeon.shopping_cart " +
                "(`customer_id`,`item_id`,`seller_id`,`quantity`) " +
                "VALUES( " + cart.getCustomerId() + " , " + cart.getItemId() +" , " + cart.getSellerId() +" , " + cart.getQuantity()+ " )"+
                "ON DUPLICATE KEY UPDATE quantity="+cart.getQuantity()+";";
        int out = 0;
        try {
            out = statement.executeUpdate(query);
        } catch (Exception e) {
            //pass
        }
        return out > 0;
    }

    public boolean pushOrderSwitch(OrderPageEntity orderPageEntity) throws ClassNotFoundException, SQLException, ParseException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        long generated_payment_id = 0, generated_shipemnt_id = 0, generated_order_id = 0;

        String payment_push_query = "INSERT INTO amazeon.payment (amount, gateway) VALUES (" + orderPageEntity.getAmount() +",'"+ orderPageEntity.getGateway() +"');";
        String shipment_push_query = "INSERT INTO amazeon.Shipment (delivery_date, shipper_name) VALUES ('" + orderPageEntity.getOrderDate() +"','"+ orderPageEntity.getShipper_name() +"');";

        statement.executeUpdate(payment_push_query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.getGeneratedKeys();
        if(rs!=null && rs.next()){
            generated_payment_id =  rs.getLong(1);
        }

        statement.executeUpdate(shipment_push_query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs1 = statement.getGeneratedKeys();
        if(rs1!=null && rs1.next()){
            generated_shipemnt_id =  rs1.getLong(1);
        }

        String order_table_insert_query = "INSERT INTO amazeon.order_table (customer_id, payment_id, address_type) VALUES ("+ orderPageEntity.getCustomer_id()+","+generated_payment_id+",'"+orderPageEntity.getAddress_type()+"');";
        statement.executeUpdate(order_table_insert_query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs2 = statement.getGeneratedKeys();
        if(rs2!=null && rs2.next()) {
            generated_order_id = rs2.getLong(1);
        }


        String order_details_insert_query;
        for (ShoppingCart sc : orderPageEntity.getCartItems()){
            order_details_insert_query = "INSERT INTO amazeon.order_details (order_id, shipment_id, item_id, seller_id, quantity) VALUES (" +generated_order_id+","+generated_shipemnt_id+","+sc.getItemId()+","+sc.getSellerId()+","+sc.getQuantity()+");";
            statement.executeUpdate(order_details_insert_query);
        }

        String update_inventory_query;
        String delete_shopping_cart_query;
        for (ShoppingCart sc : orderPageEntity.getCartItems()){
            update_inventory_query = "UPDATE amazeon.inventory SET quantity = quantity - "+sc.getQuantity()+" WHERE seller_id = "+sc.getSellerId()+" AND item_id = "+sc.getItemId()+";";
            delete_shopping_cart_query = "DELETE FROM amazeon.shopping_cart WHERE customer_id = "+sc.getCustomerId()+" AND  seller_id = "+sc.getSellerId()+" AND item_id = "+sc.getItemId()+";";
            statement.executeUpdate(update_inventory_query);
            statement.executeUpdate(delete_shopping_cart_query);
        }


        return true;
    }




}
