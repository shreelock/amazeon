package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class InventoryQtyResponse {
    @NotNull
    private Item item;
    @NotNull
    private Integer quantity=0;

    public InventoryQtyResponse(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() { return this.item; }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }
}
