package cse305.ecomm.dao;

import cse305.ecomm.representations.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Item getItemInfoByID(int itemID) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        String query = "SELECT * FROM amazeon.item WHERE item_id = " + itemID + ";";

        ResultSet res = statement.executeQuery(query);
        if (res.next()) {
            return new Item (res.getInt(1),res.getString(2),res.getString(3),
                    res.getFloat(4),res.getString(5),res.getString(6));
        }

        return new Item(-1,"NULL","NULL",-1,"NULL","NULL");
    }

    public List<ItemReviewResponse> getItemReviewByID(int itemID) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        String query = "SELECT person_name,customer_id,item_id,review_text,rating FROM amazeon.person JOIN amazeon.item_review " +
                        "WHERE person.personid = item_review.customer_id AND item_review.item_id = " + itemID;
        List<ItemReviewResponse> reviewList = new ArrayList<>();

        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            reviewList.add(new ItemReviewResponse (res.getString(1), new ItemReview(res.getInt(2),res.getInt(3),
                    res.getString(4),res.getInt(5))));
        }
        return reviewList;
    }
}