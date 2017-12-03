package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class InventoryQtyResponse {
    @NotNull
    private Integer itemId;
    @NotNull
    private String itemName;
    @NotNull
    private Integer quantity=0;

    public InventoryQtyResponse(int itemId, String itemName, int quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

}
