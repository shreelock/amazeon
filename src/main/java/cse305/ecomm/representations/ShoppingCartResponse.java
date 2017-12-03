package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class ShoppingCartResponse {
    @NotNull
    private int customerId;
    @NotNull
    private int itemId;
    @NotNull
    private int sellerId;
    @NotNull
    private int quantity;
    private String itemName;

    public ShoppingCartResponse(int customerId, int itemId, int selleId, int quantity, String itemName) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCustomerId() {
        return customerId;
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

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
