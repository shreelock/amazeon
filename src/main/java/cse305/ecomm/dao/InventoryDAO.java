package cse305.ecomm.dao;

import cse305.ecomm.representations.Address;
import cse305.ecomm.representations.InventoryQtyResponse;
import cse305.ecomm.representations.Item;
import cse305.ecomm.representations.ItemReview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public List<InventoryQtyResponse> getActiveInventory() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        String query = "SELECT item.* , sum(quantity) FROM amazeon.inventory JOIN amazeon.item where inventory.item_id = item.item_id AND quantity>0 group by item.item_id;";
        ResultSet rs = statement.executeQuery(query);

        List<InventoryQtyResponse> itemQtyRespList = new ArrayList<>();
        while(rs.next()) {
            Item item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getString(6));
            itemQtyRespList.add(new InventoryQtyResponse(item, rs.getInt(7)));
        }
        return itemQtyRespList;
    }

    public Address getAddrFromPersonId(int personId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();

        String query = "SELECT * FROM amazeon.address where person_id = " + personId + ";";

        ResultSet res = statement.executeQuery(query);
        if(res.next()) {
            System.out.println(res);
            return new Address(res.getInt(1), res.getString(3), res.getString(2));
        }
        return new Address(0,"NULL", "NULLTyPE");
    }
}