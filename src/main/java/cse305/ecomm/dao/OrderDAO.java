package cse305.ecomm.dao;

import cse305.ecomm.representations.Order;
import cse305.ecomm.representations.OrderDetails;

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public List<Order> getOrderInfoByCustId(int custId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        statement.executeUpdate("use amazeon");
        String query = "CREATE VIEW partial_order_table AS " +
                "SELECT * FROM order_table WHERE order_table.customer_id = " + custId + ";";
        statement.executeUpdate(query);

        query = "SELECT order_details.order_id,customer_id,payment_id,shipment_id,item_id,seller_id,quantity " +
                "FROM amazeon.partial_order_table JOIN amazeon.order_details " +
                "WHERE partial_order_table.order_id = order_details.order_id;";
        List<Order> orderList = new ArrayList<>();
        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            System.out.println(res);
            orderList.add(new Order(res.getInt(1), res.getInt(2), res.getInt(3),
                    new OrderDetails(res.getInt(1), res.getInt(4), res.getInt(5),
                            res.getInt(6), res.getInt(7))));
        }

        query = "DROP VIEW partial_order_table;";
        statement.executeUpdate(query);
        return orderList;
    }

    public List<Order> getOrderInfoByCustItemId(int custId, int itemId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        statement.executeUpdate("use amazeon");
        String query = "CREATE VIEW partial_order_table AS " +
                "SELECT * FROM order_table WHERE order_table.customer_id = " + custId + ";";
        statement.executeUpdate(query);

        query = "SELECT order_details.order_id,customer_id,payment_id,shipment_id,item_id,seller_id,quantity " +
                "FROM amazeon.partial_order_table JOIN amazeon.order_details " +
                "WHERE partial_order_table.order_id = order_details.order_id and order_details.item_id =" + itemId + ";";
        List<Order> orderList = new ArrayList<>();
        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            System.out.println(res);
            orderList.add(new Order(res.getInt(1), res.getInt(2), res.getInt(3),
                    new OrderDetails(res.getInt(1), res.getInt(4), res.getInt(5),
                            res.getInt(6), res.getInt(7))));
        }

        query = "DROP VIEW partial_order_table;";
        statement.executeUpdate(query);
        return orderList;
    }

}
