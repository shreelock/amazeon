package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class OrderDetails {
    @NotNull
    private int orderId;
    @NotNull
    private int shipmentId;
    @NotNull
    private int itemId;
    private int sellerId;
    @NotNull
    private int quantity;

    public OrderDetails(int orderId, int shipmentId, int itemId, int sellerId, int quantity) {
        this.orderId = orderId;
        this.shipmentId = shipmentId;
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
