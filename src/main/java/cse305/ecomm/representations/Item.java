package cse305.ecomm.representations;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Item {

    @NotNull
    private Integer id;
    private String itemType;
    @NotNull
    private String itemName;
    @NotNull
    private float price;
    @NotNull
    private String category;
    private String description;

    public Item(Integer id, String itemType, String itemName, float price, String category, String description) {
        this.id = id;
        this.itemType = itemType;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {

        return id;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }


}
