package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class ShoppingCart {
    @NotNull
    private int customerId;
    @NotNull
    private int itemId;
    @NotNull
    private int selleId;
    @NotNull
    private int quantity;

    public ShoppingCart(int customerId, int itemId, int selleId, int quantity) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.selleId = selleId;
        this.quantity = quantity;
    }


    public int getCustomerId() {
        return customerId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getSelleId() {
        return selleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setSelleId(int selleId) {
        this.selleId = selleId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
