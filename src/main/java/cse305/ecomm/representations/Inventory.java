package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class Inventory {
    @NotNull
    private Integer itemId;
    @NotNull
    private Integer sellerId;
    @NotNull
    private Integer quantity=0;

    public Inventory(int itemId, int sellerId, int quantity) {
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.quantity = quantity;
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

    public int getItemId() {
        return itemId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getQuantity() {
        return quantity;
    }
}
