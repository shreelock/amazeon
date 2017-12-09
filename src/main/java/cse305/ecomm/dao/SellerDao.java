package cse305.ecomm.dao;

import cse305.ecomm.representations.InventoryQtyResponse;
import cse305.ecomm.representations.Seller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SellerDao {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public List<Seller> getSellerInfo() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        String query = "SELECT * " +
                "FROM amazeon.seller JOIN amazeon.person" +
                "WHERE seller.seller_id = person.personid;";
        ResultSet rs = statement.executeQuery(query);

        List<Seller> sellerList = new ArrayList<>();
        while(rs.next()) {
            sellerList.add(new Seller(rs.getInt(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getInt(7),
                    rs.getString(8), rs.getInt(1), rs.getInt(2),
                    rs.getString(9)));
        }
        return sellerList;
    }

}
