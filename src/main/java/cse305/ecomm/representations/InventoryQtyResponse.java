package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class InventoryQtyResponse {
    @NotNull
    private Integer itemId;
    @NotNull
    private Integer sellerId;
    @NotNull
    private String itemName;
    @NotNull
    private String sellerName;
    @NotNull
    private Integer quantity=0;

    public InventoryQtyResponse(int itemId, Integer sellerId, String itemName, String sellerName, int quantity) {
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.itemName = itemName;
        this.sellerName = sellerName;
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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
