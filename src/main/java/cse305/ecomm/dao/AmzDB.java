package cse305.ecomm.dao;

import cse305.ecomm.representations.Address;

import java.sql.*;

public class AmzDB {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void initDBSchema() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://a.vshukla.in:3306/?user=admin&password=password");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            statement.executeUpdate("create database if not exists amazeon");
            statement.executeUpdate("use amazeon");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Item (" +
                    "  item_id int(10) unsigned NOT NULL AUTO_INCREMENT," +
                    "  item_type varchar(45) DEFAULT NULL," +
                    "  item_name varchar(45) NOT NULL," +
                    "  price decimal(10,2) unsigned NOT NULL," +
                    "  category varchar(45) DEFAULT NULL," +
                    "  description varchar(1024) DEFAULT NULL," +
                    "  PRIMARY KEY (item_id)" +
                    ")");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS person (" +
                    "  personid int(10) unsigned NOT NULL AUTO_INCREMENT," +
                    "  person_name varchar(45) NOT NULL," +
                    "  contact_number varchar(45) NOT NULL," +
                    "  email_address varchar(45) NOT NULL," +
                    "  age int(3) DEFAULT 0," +
                    "  secure_password varchar(45) NOT NULL," +
                    "  PRIMARY KEY (personid)," +
                    "  CHECK (age >= 18 AND age<=200)" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS address (" +
                    "  person_id int(10) unsigned NOT NULL," +
                    "  addr_type varchar(45) DEFAULT \"HOME\"," +
                    "  address varchar(100) NOT NULL," +
                    "  PRIMARY KEY (person_id, addr_type)," +
                    "  CONSTRAINT fk_address_personid FOREIGN KEY (person_id) REFERENCES person (personid) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS seller (" +
                    "  seller_id int(10) unsigned NOT NULL," +
                    "  seller_quality int(2) unsigned DEFAULT NULL," +
                    "  PRIMARY KEY (seller_id)," +
                    "  CHECK (seller_quality <= 10)," +
                    "  CONSTRAINT fk_seller_personid FOREIGN KEY (seller_id) REFERENCES person (personid) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS cities_of_operation (" +
                    "  seller_id int(10) unsigned NOT NULL," +
                    "  city_name varchar(45) NOT NULL," +
                    "  zip_code int(10) unsigned NOT NULL," +
                    "  PRIMARY KEY (seller_id,zip_code)," +
                    "  CONSTRAINT fk_cities_of_operation_seller FOREIGN KEY (seller_id) REFERENCES seller (seller_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS customer (" +
                    "  customer_id int(10) unsigned NOT NULL," +
                    "  PRIMARY KEY (customer_id)," +
                    "  CONSTRAINT fk_customer_person FOREIGN KEY (customer_id) REFERENCES person (personid) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS customer_interests (" +
                    "  customer_id int(10) unsigned NOT NULL," +
                    "  interest varchar(45) NOT NULL," +
                    "  PRIMARY KEY (customer_id,interest)," +
                    "  CONSTRAINT fk_customer_interests_cus FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS employee (" +
                    "  employee_id int(10) unsigned NOT NULL," +
                    "  salary int(11) unsigned NOT NULL," +
                    "  role varchar(45) NOT NULL," +
                    "  PRIMARY KEY (employee_id)," +
                    "  CONSTRAINT fk_employee_person FOREIGN KEY (employee_id) REFERENCES person (personid) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS supervised_by (" +
                    "  employee_id int(10) unsigned NOT NULL," +
                    "  manager_id int(10) unsigned NOT NULL," +
                    "  since date DEFAULT NULL," +
                    "  PRIMARY KEY (employee_id)," +
                    "  CONSTRAINT fk_supervised_by_mngrid FOREIGN KEY (manager_id) REFERENCES employee (employee_id) ON DELETE NO ACTION ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_supervised_by_empid FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS db_Admin (" +
                    "  emp_id int(10) unsigned NOT NULL," +
                    "  privilege_level varchar(45) DEFAULT NULL," +
                    "  section_overseen varchar(45) DEFAULT NULL," +
                    "  PRIMARY KEY (emp_id)," +
                    "  CONSTRAINT fk_db_Admin_empid FOREIGN KEY (emp_id) REFERENCES employee (employee_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS department (" +
                    "  department_id int(10) unsigned NOT NULL AUTO_INCREMENT," +
                    "  department_name varchar(45) NOT NULL," +
                    "  PRIMARY KEY (department_id)" +
                    ");");
                    
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS dept_managed_by (" +
                    "  dept_id int(10) unsigned NOT NULL," +
                    "  emp_id int(10) unsigned NOT NULL," +
                    "  since date DEFAULT NULL," +
                    "  PRIMARY KEY (dept_id)," +
                    "  CONSTRAINT fk_dept_managed_by_deptid FOREIGN KEY (dept_id) REFERENCES department (department_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_dept_managed_by_empid FOREIGN KEY (emp_id) REFERENCES employee (employee_id) ON DELETE NO ACTION ON UPDATE CASCADE" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS works_in_dept (" +
                    "  employee_id int(10) unsigned NOT NULL," +
                    "  since date DEFAULT NULL," +
                    "  department_id int(10) unsigned DEFAULT NULL," +
                    "  PRIMARY KEY (employee_id)," +
                    "  CONSTRAINT fk_works_in_dept_deptid FOREIGN KEY (department_id) REFERENCES department (department_id) ON DELETE SET NULL ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_works_in_dept_empid FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS inventory (" +
                    "  item_id int(11) unsigned NOT NULL," +
                    "  seller_id int(10) unsigned NOT NULL," +
                    "  quantity int(11) unsigned DEFAULT 0," +
                    "  PRIMARY KEY (item_id,seller_id)," +
                    "  CONSTRAINT fk_inventory_item FOREIGN KEY (item_id) REFERENCES Item (item_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_inventory_seller FOREIGN KEY (seller_id) REFERENCES seller (seller_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
                    
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS item_review (" +
                    "  customer_id int(10) unsigned NOT NULL," +
                    "  item_id int(10) unsigned NOT NULL," +
                    "  review_text varchar(1024) DEFAULT NULL," +
                    "  rating int(2) unsigned NOT NULL," +
                    "  PRIMARY KEY (customer_id,item_id)," +
                    "  CHECK (rating <= 10)," +
                    "  CONSTRAINT fk_item_review_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_item_review_item FOREIGN KEY (item_id) REFERENCES Item (item_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
                    
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS payment (" +
                    "  payment_id int(10) unsigned NOT NULL AUTO_INCREMENT," +
                    "  amount decimal(10,2) unsigned NOT NULL," +
                    "  gateway varchar(20) NOT NULL," +
                    "  pay_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                    "  PRIMARY KEY (payment_id)" +
                    ");");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Shipment (" +
                    "  shipment_id int(10) unsigned NOT NULL AUTO_INCREMENT," +
                    "  delivery_date date NOT NULL," +
                    "  shipper_name varchar(45) NOT NULL," +
                    "  PRIMARY KEY (shipment_id)" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS order_table (" +
                    "  order_id int(10) unsigned NOT NULL AUTO_INCREMENT," +
                    "  customer_id int(10) unsigned DEFAULT NULL," +
                    "  payment_id int(10) unsigned NOT NULL," +
                    "  PRIMARY KEY (order_id)," +
                    "  CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE SET NULL ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_order_payment FOREIGN KEY (payment_id) REFERENCES payment (payment_id) ON DELETE NO ACTION ON UPDATE CASCADE" +
                    ") ;");
                    
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS order_details (" +
                    "  order_id int(10) unsigned NOT NULL," +
                    "  shipment_id int(10) unsigned NOT NULL," +
                    "  item_id int(10) unsigned NOT NULL," +
                    "  seller_id int(10) unsigned DEFAULT NULL," +
                    "  quantity int(11) unsigned NOT NULL," +
                    "  PRIMARY KEY (order_id,item_id)," +
                    "  CONSTRAINT fk_order_details_orderid FOREIGN KEY (order_id) REFERENCES order_table (order_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_order_details_sellerid FOREIGN KEY (seller_id) REFERENCES seller (seller_id) ON DELETE SET NULL ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_order_details_itemid FOREIGN KEY (item_id) REFERENCES Item (item_id) ON DELETE NO ACTION ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_order_details_shipmentid FOREIGN KEY (shipment_id) REFERENCES Shipment (shipment_id) ON DELETE NO ACTION ON UPDATE CASCADE" +
                    ");");
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS shopping_cart (" +
                    "  customer_id int(10) unsigned NOT NULL," +
                    "  item_id int(10) unsigned NOT NULL," +
                    "  seller_id int(10) unsigned NOT NULL," +
                    "  quantity int(5) unsigned NOT NULL," +
                    "  PRIMARY KEY (customer_id,item_id,seller_id)," +
                    "  CONSTRAINT fk_shopping_cart_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_shopping_cart_item FOREIGN KEY (item_id) REFERENCES Item (item_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "  CONSTRAINT fk_shopping_cart_seller FOREIGN KEY (seller_id) REFERENCES seller (seller_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
        } catch (Exception e) {
            System.out.println("error : "+e);
            throw e;
        }
    }

    public void initDBData() throws Exception {
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://localhost/?user=root&password=root");

        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();

        String insertToPerson1 = "INSERT INTO `amazeon`.`person`" +
                "(`personid`, `person_name`, `contact_number`, `email_address`, `age`,  `secure_password`)" +
                " VALUES (1,\"Shree\",9123424321,\"ssh@mail.com\",12,\"pass\");";

        String insertToPerson2 = "INSERT INTO `amazeon`.`person`" +
                "(`personid`, `person_name`, `contact_number`, `email_address`, `age`,  `secure_password`)" +
                "VALUES (2,\"Sachin\",9124124414,\"sscin@mail.com\",42,\"passq\");";

        String insertToPerson3 = "INSERT INTO `amazeon`.`person`" +
                "(`personid`, `person_name`, `contact_number`, `email_address`, `age`,  `secure_password`)" +
                "VALUES (3,\"Sehwag\",9123562341,\"ssdfsh@mail.com\",12,\"pass\");";

        String insertToCustomer1 = "INSERT INTO amazeon.customer (customer_id) VALUES (1);";
        String insertToCustomer2 = "INSERT INTO amazeon.customer (customer_id) VALUES (2);";
        String insertToCustomer3 = "INSERT INTO amazeon.customer (customer_id) VALUES (3);";

        String insertIntoAddr1 = " INSERT INTO amazeon.address " +
                "( person_id, addr_type, address)" +
                "VALUES ( 1, \"HOME\", \"123, Dalal Street India\");";

        String insertIntoAddr2 = " INSERT INTO amazeon.address " +
                "( person_id, addr_type, address)" +
                "VALUES ( 2, \"OFFICE\", \"FLipkart Office, Pune, India\");";

        statement.executeUpdate(insertToPerson1);
        statement.executeUpdate(insertToPerson2);
        statement.executeUpdate(insertToPerson3);
        statement.executeUpdate(insertToCustomer1);
        statement.executeUpdate(insertToCustomer2);
        statement.executeUpdate(insertToCustomer3);
        statement.executeUpdate(insertIntoAddr1);
        statement.executeUpdate(insertIntoAddr2);
    }

    public Address getAddrFromPersonId(int personId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://localhost/?user=root&password=root");
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