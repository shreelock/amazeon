package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderPageEntity {
    @NotNull
    private Integer customer_id;
    @NotNull
    private Integer amount;
    @NotNull
    private String gateway;
    @NotNull
    private String orderDate;
    @NotNull
    private String shipper_name;
    @NotNull
    private String address_type;
    @NotNull
    private List<ShoppingCart> cartItems;

    public OrderPageEntity() {

    }

    public OrderPageEntity(Integer customer_id, Integer amount, String gateway, String orderDate, String shipper_name, String address_type, List<ShoppingCart> cartItems) {
        this.customer_id = customer_id;
        this.amount = amount;
        this.gateway = gateway;
        this.orderDate = orderDate;
        this.shipper_name = shipper_name;
        this.address_type = address_type;
        this.cartItems = cartItems;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipper_name() {
        return shipper_name;
    }

    public void setShipper_name(String shipper_name) {
        this.shipper_name = shipper_name;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public List<ShoppingCart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCart> cartItems) {
        this.cartItems = cartItems;
    }

}
