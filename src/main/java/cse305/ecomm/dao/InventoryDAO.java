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
        String query = "SELECT item.item_id, inventory.seller_id, item.item_name, person.person_name, quantity " +
                "FROM amazeon.inventory JOIN amazeon.item JOIN amazeon.person " +
                "WHERE inventory.item_id = item.item_id AND inventory.seller_id = person.personid AND quantity>0;";
        ResultSet rs = statement.executeQuery(query);

        List<InventoryQtyResponse> itemQtyRespList = new ArrayList<>();
        while(rs.next()) {
            itemQtyRespList.add(new InventoryQtyResponse(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
        }
        return itemQtyRespList;
    }

}