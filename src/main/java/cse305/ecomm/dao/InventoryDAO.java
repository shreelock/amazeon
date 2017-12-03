package cse305.ecomm.dao;

import cse305.ecomm.representations.InventoryQtyResponse;

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
        String query = "SELECT item.item_id, item.item_name , sum(quantity) FROM amazeon.inventory JOIN amazeon.item where inventory.item_id = item.item_id AND quantity>0 group by item.item_id;";
        ResultSet rs = statement.executeQuery(query);

        List<InventoryQtyResponse> itemQtyRespList = new ArrayList<>();
        while(rs.next()) {
            itemQtyRespList.add(new InventoryQtyResponse(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return itemQtyRespList;
    }

}