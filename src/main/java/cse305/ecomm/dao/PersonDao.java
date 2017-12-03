package cse305.ecomm.dao;

import cse305.ecomm.representations.Address;
import cse305.ecomm.representations.Order;
import cse305.ecomm.representations.OrderDetails;
import cse305.ecomm.representations.Person;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Person getPersonById(int personId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        String query = "SELECT * FROM amazeon.person where personid = " + personId + ";";
        ResultSet res = statement.executeQuery(query);
        if(res.next()) {
            System.out.println(res);
            return new Person(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5), res.getString(6));
        }
        return new Person(0,"Ayush", "63142886671", "ayush2913", 25, "password");

    }

    public List<Person> getPersonInfoByName(String person_name) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");
        statement = connect.createStatement();
        String query = "SELECT * FROM amazeon.person WHERE person_name LIKE '" + person_name + "%';";
        List<Person> personList = new ArrayList<>();

        ResultSet res = statement.executeQuery(query);
        while(res.next()) {
            System.out.println(res);
            personList.add(new Person(res.getInt(1),res.getString(2),res.getString(3),
                    res.getString(4),res.getInt(5),res.getString(6)));
        }
        return personList;
    }

}
